package com.ai.slp.product.service.business.impl;

import java.sql.Timestamp;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.api.productcat.param.*;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.ProductCatConstants;
import com.ai.slp.product.dao.mapper.attach.CatAttrValAttach;
import com.ai.slp.product.dao.mapper.attach.ProdAttrAndValIdAttrch;
import com.ai.slp.product.dao.mapper.attach.ProdCatAttrAttch;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttr;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttrValue;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.service.atom.interfaces.*;
import com.ai.slp.product.service.atom.interfaces.product.IProdAttrAtomSV;
import com.ai.slp.product.service.business.interfaces.IProductCatBusiSV;
import com.ai.slp.product.util.DateUtils;

/**
 * Created by jackieliu on 16/4/29.
 */
@Service
@Transactional
public class ProductCatBusiSVImpl implements IProductCatBusiSV {
    private static final Logger logger = LoggerFactory.getLogger(ProductCatBusiSVImpl.class);
    @Autowired
    IProdCatDefAtomSV prodCatDefAtomSV;
    @Autowired
    IProdCatAttrAtomSV prodCatAttrAtomSV;
    @Autowired
    IStandedProductAtomSV standedProductAtomSV;
    @Autowired
    IProdAttrAtomSV prodAttrAtomSV;
    @Autowired
    IProdCatAttrValAtomSV prodCatAttrValAtomSV;
    @Autowired
    IProdCatAttrAttachAtomSV catAttrAttachAtomSV;
    @Autowired
    IStandedProdAttrAtomSV standedProdAttrAtomSV;

    @Override
    public PageInfo<ProductCat> queryProductCat(ProductCatPageQuery pageQuery) {
        PageInfo<ProductCat> catInfoPageInfo = prodCatDefAtomSV.queryForPage(
                pageQuery.getPageNo(),pageQuery.getPageSize(),pageQuery.getParentProductCatId(),
                pageQuery.getTenantId(),pageQuery.getProductCatId(),
                pageQuery.getProductCatName(),pageQuery.getIsChild()
        );
       
        return catInfoPageInfo;
    }

    @Override
    public void addCatList(List<ProductCatParam> pcpList) {
        if (CollectionUtil.isEmpty(pcpList)){
        	return;
        }
        for(ProductCatParam catParam:pcpList){
            ProductCat productCat = new ProductCat();
            BeanUtils.copyProperties(productCat,catParam);
            Short catLeve = new Short("1");
            //上级类目不为空,且不为0
            if (StringUtils.isNotBlank(catParam.getParentProductCatId())
                    && !ProductCatConstants.ProductCat.ParentProductCatId.ROOT_CAT.equals(
                    catParam.getParentProductCatId())){
                ProductCat parentCat = prodCatDefAtomSV.selectById(
                        catParam.getTenantId(),catParam.getParentProductCatId());
                if (parentCat!=null){
                	catLeve = (short) (parentCat.getCatLevel()+1);
                }
            }
            productCat.setCatLevel(catLeve);
            prodCatDefAtomSV.insertProductCat(productCat);
        }
    }

    @Override
    public ProductCatInfo queryByCatId(String tenantId, String productCatId) {
        ProductCatInfo productCatInfo = null;
        ProductCat productCat = prodCatDefAtomSV.selectById(tenantId,productCatId);
        if (productCat!=null){
            productCatInfo = new ProductCatInfo();
            BeanUtils.copyProperties(productCatInfo,productCat);
        }
        return productCatInfo;
    }

    @Override
    public void updateByCatId(ProductCatParam catParam) {
        if (catParam==null){
        	throw new BusinessException("","请求信息为空,无法更新");
        }
        //查询分类
        ProductCat productCat = prodCatDefAtomSV.selectById(catParam.getTenantId(),catParam.getProductCatId());
        if (productCat==null){
            throw new BusinessException("","没有找到对应的类目信息,租户id:"+catParam.getTenantId()
                    +",类目标识:"+catParam.getProductCatId());
        }
        //判断是否变更了"是否有子分类"选项
        if (!productCat.getIsChild().equals(catParam.getIsChild())){
            //若把有改成无
            if (ProductCatConstants.ProductCat.IsChild.HAS_CHILD.equals(productCat.getIsChild())
                    && ProductCatConstants.ProductCat.IsChild.NO_CHILD.equals(catParam.getIsChild())){
                //判断是否有子类目
                if (prodCatDefAtomSV.queryActiveOfParent(productCat.getParentProductCatId())>0){
                	throw new BusinessException("","此类目下存在子类目,无法变更为[无子分类]");
                }
            }else{
                //判断是否有关联属性
                List<ProdCatAttr> catAttrList =
                        prodCatAttrAtomSV.queryAttrsByCatId(productCat.getTenantId(),productCat.getProductCatId());
                if (catAttrList!=null && catAttrList.size()>0){
                    throw new BusinessException("","此类目已关联属性,无法变更为[有子分类]");
                }
            }
        }
        BeanUtils.copyProperties(productCat,catParam);
        prodCatDefAtomSV.updateProductCat(productCat);
    }

    @Override
    public void deleteByCatId(String tenantId, String productCatId,Long operId) {
        //查询是否存在类目
        ProductCat productCat = prodCatDefAtomSV.selectById(tenantId,productCatId);
        if (productCat==null){
        	throw new BusinessException("","未找到要删除类目,租户id:"+tenantId+",类目标识:"+productCatId);
        }
        //判断是否关联了标准品
        if (standedProductAtomSV.queryByCatId(tenantId,productCatId,false)>0){
        	throw new BusinessException("","已关联了标准品，不可删除");
        }
        //判断是否有子类目
        if (prodCatDefAtomSV.queryActiveOfParent(productCatId)>0){
        	throw new BusinessException("","此类目下存在子类目,不可删除");
        }
        List<ProdCatAttr> catAttrList =
                prodCatAttrAtomSV.queryAttrsByCatId(tenantId,productCatId);
        //删除属性对应关系
        if (catAttrList!=null && catAttrList.size()>0){
            for (ProdCatAttr catAttr:catAttrList){
                //删除类目下属性与属性值的对应关系
                prodCatAttrValAtomSV.deleteByCat(tenantId,catAttr.getCatAttrId(),operId);
                //删除类目与属性对应关系
                prodCatAttrAtomSV.deleteByCatAttrId(tenantId,catAttr.getCatAttrId(),operId);
            }
        }
        //删除类目
        prodCatDefAtomSV.deleteProductCat(tenantId,productCatId,operId);
    }

    /**
     * 查询类目的类目链
     *
     * @param tenantId
     * @param productCatId
     * @return
     */
    @Override
    public List<ProductCatInfo> queryLinkOfCatById(String tenantId, String productCatId) {
    	List<ProductCatInfo> catInfoList = new ArrayList<ProductCatInfo>();
    	queryCatFoLinkById(catInfoList,tenantId,productCatId);
    	return catInfoList;
    }

    /**
     * @param tenantId
     * @param productCatId
     * @param attrType
     * @return
     */
    @Override
    public List<ProdCatAttrDef> queryAttrOfCatByIdAndType(
            String tenantId, String productCatId, String attrType) {
        List<ProdCatAttrDef> catAttrDefList = new ArrayList<>();
        //查询类目属性集合
        List<ProdCatAttrAttch> attrAttchList = catAttrAttachAtomSV.queryAttrOfByIdAndType(
                tenantId,productCatId,attrType);
        //查询属性对应的属性值
        for (ProdCatAttrAttch attrAttch:attrAttchList){
            ProdCatAttrDef catAttrDef = new ProdCatAttrDef();
            BeanUtils.copyProperties(catAttrDef,attrAttch);
            //查询此属性是否关联标准品
            int prodNum = standedProdAttrAtomSV.queryProdNumOfAttr(tenantId,attrAttch.getAttrId());
            catAttrDef.setHasProduct(prodNum>0?true:false);
            //查询属性对应的属性值
            List<CatAttrValAttach> catAttrValList =
                    catAttrAttachAtomSV.queryValListByCatAttr(tenantId,attrAttch.getCatAttrId());
            List<AttrValInfo> valInfoList = new ArrayList<>();
            for (CatAttrValAttach attrvalueDef:catAttrValList){
                AttrValInfo attrValInfo = new AttrValInfo();
                BeanUtils.copyProperties(attrValInfo,attrvalueDef);
                attrValInfo.setCatAttrValId(attrvalueDef.getCatAttrValueId());
                valInfoList.add(attrValInfo);
            }
            catAttrDef.setAttrValList(valInfoList);
            catAttrDefList.add(catAttrDef);
        }
        return catAttrDefList;
    }

    /**
     * 查询类目下某个类型的属性标识和属性值标识集合
     *
     * @param tenantId
     * @param productCatId
     * @param attrType
     * @return
     */
    @Override
    public List<ProdAttrAndValIdAttrch> queryAttrAndAttrvalueDefId(String tenantId, String productCatId, String attrType) {
    	List<ProdAttrAndValIdAttrch> list = prodCatAttrAtomSV.queryAttrAndValIdByCatIdAndType(tenantId, productCatId, attrType);
    	
    	/*Map<Long,Set<String>> idMap = new HashMap<>();
        //查询类目和属性的关联关系
        List<ProdCatAttr> catAttrList = prodCatAttrAtomSV.queryAttrOfCatByIdAndType(tenantId,productCatId,attrType);
        for (ProdCatAttr catAttr:catAttrList){
            Set<String> attrValIds = idMap.get(catAttr.getAttrId());
            if (attrValIds==null){
                attrValIds = new HashSet<>();
                idMap.put(catAttr.getAttrId(),attrValIds);
            }
            //查询关联关系对应属性值集合
            List<ProdCatAttrValue> attrValueList = prodCatAttrValAtomSV.queryByCatAttrId(tenantId,catAttr.getCatAttrId());
            for (ProdCatAttrValue attrValue:attrValueList){
                attrValIds.add(attrValue.getAttrvalueDefId());
            }
        }*/
        return list;
    }
    /**
     * 查询类目下某个类型的属性标识和属性值标识集合
     *
     * @param tenantId
     * @param productCatId
     * @param attrType
     * @return
     */
    @Override
    public Map<Long, Set<String>> queryAttrAndValIdByCatIdAndType(String tenantId, String productCatId, String attrType) {
    	Map<Long,Set<String>> idMap = new HashMap<>();
    	//查询类目和属性的关联关系
    	List<ProdCatAttr> catAttrList = prodCatAttrAtomSV.queryAttrOfCatByIdAndType(tenantId,productCatId,attrType);
    	for (ProdCatAttr catAttr:catAttrList){
    		Set<String> attrValIds = idMap.get(catAttr.getAttrId());
    		if (attrValIds==null){
    			attrValIds = new HashSet<>();
    			idMap.put(catAttr.getAttrId(),attrValIds);
    		}
    		//查询关联关系对应属性值集合
    		List<ProdCatAttrValue> attrValueList = prodCatAttrValAtomSV.queryByCatAttrId(tenantId,catAttr.getCatAttrId());
    		for (ProdCatAttrValue attrValue:attrValueList){
    			attrValIds.add(attrValue.getAttrvalueDefId());
    		}
    	}
    	return idMap;
    }

    /**
     * 删除类目的属性关联
     *
     * @param catAttrVal
     */
    @Override
    public void deleteAttr(ProdCatAttrVal catAttrVal) {
        String tenantId = catAttrVal.getTenantId(),
                catId = catAttrVal.getProductCatId();
        //查询属性值
        ProdCatAttr catAttr = prodCatAttrAtomSV.selectById(tenantId,catAttrVal.getId());
        if (catAttr==null || CommonConstants.STATE_INACTIVE.equals(catAttr.getState())){
        	return;
        }
        int count = standedProductAtomSV.queryByCatId(tenantId,catId,false);
        //若删除关键属性或销售属性,需要检查是否关联标准品
        if (count>0 && (ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_KEY.equals(catAttr.getAttrType())
                || ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE.equals(catAttr.getAttrType()))) {
            throw new BusinessException("", "此类目已关联标准品,不允许删除当前属性");
        }
        //删除关联属性
        prodCatAttrAtomSV.deleteByCatAttrId(tenantId,catAttrVal.getId(),catAttrVal.getOperId());
        //删除关联属性值
        prodCatAttrValAtomSV.deleteByCatAttrId(tenantId,catAttrVal.getId(),catAttrVal.getOperId());
    }

    /**
     * 删除类目的属性值关联
     *
     * @param catAttrVal
     */
    @Override
    public void deleteAttrVal(ProdCatAttrVal catAttrVal) {
        String tenantId = catAttrVal.getTenantId(),
                catId = catAttrVal.getProductCatId();
        //查询关联属性值是否存在
        ProdCatAttrValue catAttrValue = prodCatAttrValAtomSV.selectById(tenantId,catAttrVal.getId());
        if (catAttrValue==null || CommonConstants.STATE_INACTIVE.equals(catAttrValue.getState())){
        	return;
        }
        //查询属性值是否被标准品/销售商品使用
        if (standedProdAttrAtomSV.countOfAttrValOfCat(tenantId,catId,catAttrValue.getAttrvalueDefId())>0
                ||prodAttrAtomSV.countOfAttrValOfCat(tenantId,catId,catAttrValue.getAttrvalueDefId())>0){
            throw new BusinessException("","该属性值已被关联,不允许删除");
        }
        //进行删除
        prodCatAttrValAtomSV.deleteById(tenantId,catAttrVal.getId(),catAttrVal.getOperId());
    }

    /**
     * 根据名称或首字母查询类目信息
     *
     * @param query
     * @return
     */
    @Override
    public List<ProdCatInfo> queryByNameOrFirst(ProductCatQuery query) {
        //判断是名称还是首字母
        String queryVal = query.getQueryVal();
        boolean isName = true;
        //查询内容长度为1,且为字母,则按字母进行查询
        if (StringUtils.isNotBlank(queryVal)
                && queryVal.length()==1
                && Character.isLetter(queryVal.charAt(0))){
            queryVal = queryVal.substring(0,1);
            isName = false;
        }
        List<ProdCatInfo> catInfoList = new ArrayList<>();
        List<ProductCat> catList = prodCatDefAtomSV.queryByNameOrFirst(
                query.getTenantId(),query.getParentProductCatId(),queryVal,isName);
        for (ProductCat cat:catList){
            ProdCatInfo catInfo = new ProdCatInfo();
            BeanUtils.copyProperties(catInfo,cat);
            catInfoList.add(catInfo);
        }
        return catInfoList;
    }

    /**
     * 类目添加指定属性类型的属性和属性值
     *
     * @param addCatAttrParam
     */
    @Override
    public void addAttrAndValOfAttrType(ProdCatAttrAddParam addCatAttrParam) {
        //查询类目是否存在
        String tenantId = addCatAttrParam.getTenantId();
        String catId = addCatAttrParam.getProductCatId();
        if (prodCatDefAtomSV.selectById(tenantId,catId)==null){
            throw new BusinessException("","未找到指定类目信息,租户ID:"+tenantId+",类目标识:"+catId);
        }
        String attrType = addCatAttrParam.getAttrType();
        //查询当前类目是否已有非废弃的标准品
        int count = standedProductAtomSV.queryByCatId(tenantId,catId,false);
        if(count>0&& ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_KEY.equals(attrType)){
            throw new BusinessException("","此类目下存在标准品,不允许更改关键属性");
        }else if(count>0 && ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE.equals(attrType)){
        	throw new BusinessException("","此类目下存在标准品,不允许更改销售属性");
        }
        Map<Long,Set<String>> attrAndVal = addCatAttrParam.getAttrAndVal();
        List<Long> attrIdList = new ArrayList<>(attrAndVal.keySet());
        //查询待删除属性关联关系ID
        List<String> catAttrIds = prodCatAttrAtomSV.queryIdsOfNoAttrId(tenantId,catId,attrType,attrIdList);
        //删除属性关联
        prodCatAttrAtomSV.deleteNoAttrId(tenantId,catId,attrType,attrIdList,addCatAttrParam.getOperId());
        //删除取消管理的属性值
        prodCatAttrValAtomSV.deleteByCatAttrId(tenantId,catAttrIds,addCatAttrParam.getOperId());
        //查询当前类目指定类型的属性信息
//        List<ProdCatAttr> oldAttr = prodCatAttrAtomSV.queryAttrOfCatByIdAndType(tenantId,catId,attrType);
        Timestamp operTime = DateUtils.currTimeStamp();
        for (Map.Entry<Long,Set<String>> entry:attrAndVal.entrySet()){
            //检查是否已经关联
            ProdCatAttr catAttr = prodCatAttrAtomSV.queryByCatIdAndTypeAndAttrId(tenantId,catId,entry.getKey(),attrType);
            Set<String> attrValSet = entry.getValue();
            if (catAttr==null){
                catAttr = new ProdCatAttr();
                catAttr.setTenantId(tenantId);
                catAttr.setProductCatId(catId);
                catAttr.setAttrId(entry.getKey());
                catAttr.setAttrType(attrType);
                catAttr.setSerialNumber((short)1);
                catAttr.setState(CommonConstants.STATE_ACTIVE);
                catAttr.setOperId(addCatAttrParam.getOperId());
                catAttr.setOperTime(operTime);
                //设置是否必填,关键属性和销售属性为必填,其他为非必填
                if (ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_KEY.equals(attrType)
                        || ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE.equals(attrType)){
                	catAttr.setIsNecessary("Y");
                }
                else{
                	catAttr.setIsNecessary("N");
                }
                prodCatAttrAtomSV.insertProdCatAttr(catAttr);
            }else {
                //删除已取消关联的属性值
                prodCatAttrValAtomSV.deleteNoValIds(tenantId,catAttr.getCatAttrId()
                        ,new ArrayList<String>(attrValSet),addCatAttrParam.getOperId());
            }

            for (String valId:attrValSet){
                //检查关联关系是否已经存在
                ProdCatAttrValue catAttrValue = prodCatAttrValAtomSV.queryByCatAndCatAttrId(
                            tenantId,catAttr.getCatAttrId(),valId);
                if (catAttrValue!=null){
                	continue;
                }
                //添加属性值
                catAttrValue = new ProdCatAttrValue();
                catAttrValue.setTenantId(tenantId);
                catAttrValue.setAttrvalueDefId(valId);
                catAttrValue.setCatAttrId(catAttr.getCatAttrId());
                catAttrValue.setSerialNumber((short)1);
                catAttrValue.setState(CommonConstants.STATE_ACTIVE);
                catAttrValue.setOperId(addCatAttrParam.getOperId());
                catAttrValue.setOperTime(operTime);
                prodCatAttrValAtomSV.installCatAttrVal(catAttrValue);
            }
        }
    }

    /**
     * 更新类目属性和属性值
     *
     * @param updateReq
     * @return 更新成功条目数
     */
    @Override
    public int updateCatAttrAndVal(ProdCatAttrUpdateReq updateReq) {
        //成功数量
        int successNum = 0;
        String tenantId = updateReq.getTenantId();
        for (ProdCatAttrUpdateParam updateParam:updateReq.getUpdateParamList()){
            switch (updateParam.getObjType()){
                //更新属性
                case "1":
                    //查询属性
                    ProdCatAttr prodCatAttr = prodCatAttrAtomSV.selectById(tenantId,updateParam.getUpdateObjId());
                    if (prodCatAttr==null) {
                        logger.warn("未找到指定的类目[属性]关联\r\n" + updateParam.toString());
                        continue;
                    }
                    prodCatAttr.setSerialNumber(updateParam.getSerialNumber());
                    prodCatAttr.setIsPicture(updateParam.getIsPicture());
                    prodCatAttr.setOperId(updateReq.getOperId());
                    prodCatAttrAtomSV.update(prodCatAttr);
                    successNum++;
                    break;
                //更新属性值
                case "2":
                    //查询属性值
                    ProdCatAttrValue attrVal = prodCatAttrValAtomSV.selectById(tenantId,updateParam.getUpdateObjId());
                    if (attrVal==null) {
                        logger.warn("未找到指定的类目[属性值]关系\r\n" + updateParam.toString());
                        continue;
                    }
                    attrVal.setSerialNumber(updateParam.getSerialNumber());
                    attrVal.setOperId(updateReq.getOperId());
                    prodCatAttrValAtomSV.update(attrVal);
                    successNum++;
                    break;
                //未知类型
                default:
                    logger.warn("未知更新类型:\r\n"+updateParam.toString());
                    break;
            }

        }
        return successNum;
    }

    /**
     * 查询类目下指定类型和指定属性的属性值
     *
     * @param tenantId
     * @param catId
     * @param attrId
     * @param attrType
     * @return
     */
    @Override
    public List<CatAttrValAttach> queryAttrValOfAttrAndType(String tenantId, String catId, long attrId, String attrType) {
        //查询对应关系
        ProdCatAttr catAttr = prodCatAttrAtomSV.queryByCatIdAndTypeAndAttrId(tenantId,catId,attrId,attrType);
        if (catAttr==null){
            logger.error("未查询到属性和类目的对应关系,租户ID:{},类目标识:{},属性标识:{},查询类型:{}",
                    tenantId,catId,attrId,attrType);
            throw new BusinessException("","未查询到属性和类目的对应关系");
        }
        //查询属性对应的属性值
        return catAttrAttachAtomSV.queryValListByCatAttr(tenantId,catAttr.getCatAttrId());
    }


    private void queryCatFoLinkById(List<ProductCatInfo> catInfoList,String tenantId, String productCatId){
        ProductCatInfo catInfo = queryByCatId(tenantId,productCatId);
        if (catInfo==null){
        	return;
        }
        //已经达到根目录
        if (catInfo.getParentProductCatId()==null
                || "0".equals(catInfo.getParentProductCatId())||catInfoList.size()>=10){
            catInfoList.add(catInfo);
            return;
        //若不是跟类目,则继续查询
        }else{
        	queryCatFoLinkById(catInfoList,tenantId,catInfo.getParentProductCatId());
        }
            catInfoList.add(catInfo);
    }
}
