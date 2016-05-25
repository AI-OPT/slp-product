package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.product.param.*;
import com.ai.slp.product.constants.ProductCatConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.attach.ProdCatAttrAttch;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttr;
import com.ai.slp.product.dao.mapper.bo.StandedProdAttr;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.dao.mapper.bo.product.ProdSkuAttr;
import com.ai.slp.product.dao.mapper.bo.product.ProdSkuLog;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.dao.mapper.bo.storage.StorageLog;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAttachAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageLogAtomSV;
import com.ai.slp.product.service.business.interfaces.IProdSkuBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageBusiSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 商品SKU操作
 * Created by jackieliu on 16/5/12.
 */
@Service
@Transactional
public class ProdSkuBusiSVImpl implements IProdSkuBusiSV {
    private static Logger logger = LoggerFactory.getLogger(ProdSkuBusiSVImpl.class);
    @Autowired
    IProductAtomSV productAtomSV;
    @Autowired
    IProdCatAttrAtomSV prodCatAttrAtomSV;
    @Autowired
    IProdSkuAtomSV prodSkuAtomSV;
    @Autowired
    IProdSkuLogAtomSV prodSkuLogAtomSV;
    @Autowired
    IProdCatAttrAttachAtomSV catAttrAttachAtomSV;
    @Autowired
    IStandedProdAttrAtomSV standedProdAttrAtomSV;
    @Autowired
    IProdSkuAttrAtomSV prodSkuAttrAtomSV;
    @Autowired
    ISkuStorageAtomSV skuStorageAtomSV;
    @Autowired
    IStorageAtomSV storageAtomSV;
    @Autowired
    IStorageGroupAtomSV storageGroupAtomSV;
    @Autowired
    IStorageLogAtomSV storageLogAtomSV;
    @Autowired
    IStorageBusiSV storageBusiSV;
    /**
     * 更新商品SKU信息
     *
     * @param saveInfo
     */
    @Override
    public void updateSkuOfProduct(SkuInfoMultSave saveInfo) {
        String tenantId = saveInfo.getTenantId(),productId = saveInfo.getProdId();
        Product product = productAtomSV.selectByProductId(tenantId,productId);
        if (product==null){
        	logger.warn("未找到指定商品,租户ID{},商品标识{}:"+tenantId+","+productId);
        	throw new BusinessException("","未找到指定商品,租户ID:"+tenantId+",商品标识:"+productId);
        }
        StorageGroup group = storageGroupAtomSV.queryByGroupId(tenantId,product.getStorageGroupId());
        if (group==null){
        	logger.warn("未找到指定商品,租户ID{},库存组标识{}:"+tenantId+","+product.getStandedProdId());
            throw new BusinessException("","未找到指定库存组,租户ID:"+tenantId+",库存组标识:"+product.getStandedProdId());
        }
        //只有在库存组停用时,才允许变更SKU.
        //TODO...

        //查询商品的销售属性集合,序号正序
        Map<Long, List<String>> attrAndValMap = saveInfo.getAttrAndValIdMap();
        List<ProdCatAttr> catAttrList = prodCatAttrAtomSV.queryAttrOfCatByIdAndType(
                tenantId,product.getProductCatId(), ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE);
        if (attrAndValMap.size()!=catAttrList.size())
            throw new BusinessException("","已选择销售属性数量与实际数量不符,已选择属性数量:"+attrAndValMap.size()
                    +",实际属性数量:"+catAttrList.size());
        //参数属性值的所有SKU组合
        Set<String> skuSaleAttrs = new HashSet<>();//新SKU属性串集合
        genSkuSalAttr(attrAndValMap,"",0,skuSaleAttrs,catAttrList);
        //查询商品的所有SKU信息
        List<ProdSku> prodSkuList = prodSkuAtomSV.querySkuOfProd(tenantId,productId);
        //遍历商品已有SKU信息,确认是否在新的SKU组合中
        for (ProdSku prodSku:prodSkuList){
            //若不包含,则进行废弃
            if (!skuSaleAttrs.contains(prodSku.getSaleAttrs())){
                discardSku(prodSku,saveInfo.getOperId());
                continue;
            }
            skuSaleAttrs.remove(prodSku.getSaleAttrs());
        }
        //若新添加SKU,则需要废除之前所有库存.
        if (skuSaleAttrs!=null && skuSaleAttrs.size()>0) {
            //查询库存组下库存
            List<Storage> storageList = storageAtomSV.queryOfGroup(tenantId,group.getStorageGroupId());
            for (Storage storage : storageList) {
                storageBusiSV.discardStorage(storage, saveInfo.getOperId());
            }
        }
    }

    /**
     * 查询指定商品下的SKU信息
     *
     * @param tenantId
     * @param productId
     * @return
     */
    @Override
    public SkuSetForProduct querySkuByProdId(String tenantId, String productId) {
        //查询商品信息
        Product product = productAtomSV.selectByProductId(tenantId,productId);
        if (product==null){
        	logger.warn("未找到指定商品,租户ID{},商品标识{}:"+tenantId+","+productId);
            throw new BusinessException("","查询商品信息不存在,租户ID:"+tenantId+",商品标识:"+productId);
        }
        //查询商品的SKU信息
        Map<String,SkuInfo> skuInfoMap = new HashMap<>();
        Map<String,String> saleAttrsMap = new HashMap<>();
        SkuSetForProduct skuSetForProduct = new SkuSetForProduct();
        skuSetForProduct.setSkuInfoMap(skuInfoMap);
        skuSetForProduct.setSaleAttrsMap(saleAttrsMap);
        List<ProdSku> skuList = prodSkuAtomSV.querySkuOfProd(tenantId,productId);
        //设置SKU单品信息集合
        for (ProdSku sku:skuList){
            //设置属性串和SKU标识
            saleAttrsMap.put(sku.getSaleAttrs(),sku.getSkuId());
            SkuInfo skuInfo = new SkuInfo();
            BeanUtils.copyProperties(skuInfo,sku);
            skuInfoMap.put(sku.getSkuId(),skuInfo);
        }

        //查询商品对应标准品的销售属性,已按照属性属性排序
        List<ProdCatAttrAttch> catAttrAttches = catAttrAttachAtomSV.queryAttrOfByIdAndType(
                tenantId,product.getProductCatId(),ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE);
        Map<Long,Set<String>> attrAndValIdMap = new LinkedHashMap<>();
        Map<SkuAttrInfo,List<SkuAttrValInfo>> attrAndValInfoMap
                = new LinkedHashMap<>();
        skuSetForProduct.setAttrAndValIdMap(attrAndValIdMap);
        skuSetForProduct.setAttrAndValInfoMap(attrAndValInfoMap);
        //查询已设置SKU的属性和属性值信息
        for (ProdCatAttrAttch attrAttch:catAttrAttches){
            //设置标识集合
            Set<String> attrValSet = new LinkedHashSet<>();
            attrAndValIdMap.put(attrAttch.getAttrId(),attrValSet);
            //设置对象集合
            SkuAttrInfo skuAttrInfo = new SkuAttrInfo();
            BeanUtils.copyProperties(skuAttrInfo,attrAttch);
            List<SkuAttrValInfo> valInfoList = new ArrayList<>();
            attrAndValInfoMap.put(skuAttrInfo,valInfoList);
            //查询属性对应属性值集合
            List<StandedProdAttr> prodAttrs = standedProdAttrAtomSV.queryAttrVal(
                    tenantId,productId,attrAttch.getAttrId());
            for (StandedProdAttr prodAttr:prodAttrs){
                //若SKU不包含当前属性值,则查询下一个属性值
                if (prodSkuAttrAtomSV.queryAttrValNumOfSku(tenantId,productId,prodAttr.getAttrvalueDefId())<=0)
                    continue;
                attrValSet.add(prodAttr.getAttrvalueDefId());
                SkuAttrValInfo valInfo = new SkuAttrValInfo();
                BeanUtils.copyProperties(valInfo,prodAttr);
                valInfoList.add(valInfo);
            }
        }
        //设置属性下每个属性值跨行数
        Iterator<Map.Entry<SkuAttrInfo,List<SkuAttrValInfo>>> entryIterator = attrAndValInfoMap.entrySet().iterator();
        skuSetForProduct.setSkuNum(entryIterator.hasNext()?getAttrRowspan(entryIterator):0);
        return skuSetForProduct;
    }

    /**
     * 添加单个sku
     *
     * @param prodSku
     * @return
     */
    @Override
    public int addSku(ProdSku prodSku) {
        int addNum = prodSkuAtomSV.createObj(prodSku);
        if (addNum>0){
            ProdSkuLog prodSkuLog = new ProdSkuLog();
            BeanUtils.copyProperties(prodSkuLog,prodSku);
            prodSkuLogAtomSV.install(prodSkuLog);
        }
        return addNum;
    }

    /**
     * 返回下级属性的属性值的跨行数
     *
     * @param entryIterator
     * @return
     */
    private int getAttrRowspan(Iterator<Map.Entry<SkuAttrInfo,List<SkuAttrValInfo>>> entryIterator){
        Map.Entry<SkuAttrInfo,List<SkuAttrValInfo>> entry = entryIterator.next();
        SkuAttrInfo skuAttrInfo = entry.getKey();
        int valNum = entry.getValue().size();
        int rowspan = 1;
        if (entryIterator.hasNext())
            rowspan = getAttrRowspan(entryIterator);
        skuAttrInfo.setRowspan(rowspan);
        return rowspan*valNum;
    }

    /**
     * 根据属性值,完善SKU字符串
     *
     * @param attrAndValMap
     * @param skuInfo
     * @param attrIndex
     * @param skuSalInfo
     * @param catAttrList
     */
    private void genSkuSalAttr(Map<Long, List<String>> attrAndValMap,
                               String skuInfo,int attrIndex,Set<String> skuSalInfo,List<ProdCatAttr> catAttrList){

        if (attrIndex == catAttrList.size()){
            skuSalInfo.add(skuInfo);
            return;
        }
        ProdCatAttr catAttr = catAttrList.get(attrIndex);
        Long attrId = catAttr.getAttrId();
        List<String> valList = attrAndValMap.get(attrId);
        //拼装sku属性串
        String newSkuAttr = skuInfo + ProductConstants.ProdSku.SaleAttrs.ATTR_SPLIT
                +attrId+ProductConstants.ProdSku.SaleAttrs.ATTRVAL_SPLIT;
        for (String val:valList){
            String skuAttrVal = newSkuAttr+val;
            genSkuSalAttr(attrAndValMap,skuAttrVal,attrIndex+1,skuSalInfo,catAttrList);
        }
    }

    /**
     * 删除指定SKU
     *
     * @param prodSku
     */
    private void discardSku(ProdSku prodSku,Long operId){
        //将SKU单品进行废弃
        prodSku.setOperId(operId);
        prodSku.setState(ProductConstants.ProdSku.State.INACTIVE);
        if (prodSkuAtomSV.updateSkuById(prodSku)>0){
            ProdSkuLog prodSkuLog = new ProdSkuLog();
            BeanUtils.copyProperties(prodSkuLog,prodSku);
            prodSkuLogAtomSV.install(prodSkuLog);
        }
        //将SKU单品对应属性值进行废弃
        prodSkuAttrAtomSV.discardAttrOfSku(prodSku.getTenantId(),prodSku.getSkuId(),operId);
        //查询SKU对应的SKU库存信息
        List<SkuStorage> storageList = skuStorageAtomSV.queryOfSku(prodSku.getSkuId());
        //对SKU库存进行废弃,并减少对应库存的库存量
        for (SkuStorage skuStorage:storageList){
            Storage storage = storageAtomSV.queryById(skuStorage.getStorageId());
            if (storage==null){
                continue;
            }
            long totalNum = storage.getTotalNum();
            long usableNum = storage.getUsableNum();
            if (totalNum>0)
                storage.setTotalNum(totalNum-skuStorage.getTotalNum());
            if (usableNum>0)
                storage.setUsableNum(usableNum-skuStorage.getUsableNum());
            storage.setOperId(operId);
            if (storageAtomSV.updateById(storage)>0){
                StorageLog storageLog = new StorageLog();
                BeanUtils.copyProperties(storageLog,storage);
                storageLogAtomSV.installLog(storageLog);
            }
            //可用量为零,则需要自动停用
            if (storage.getUsableNum()<=0)
                storageBusiSV.autoStopStorage(storage);
            skuStorageAtomSV.discardById(skuStorage.getSkuStorageId(),operId);
        }

    }

    /**
     * 添加指定SKU属性值
     */
    private void addSkuAttrs(ProdSku prodSku){
        String saleAttrs = prodSku.getSaleAttrs();
        String[] attrs = saleAttrs.split(ProductConstants.ProdSku.SaleAttrs.ATTR_SPLIT);
        for (int i =0;i<attrs.length;i++){
            String[] attrVal = attrs[i].split(ProductConstants.ProdSku.SaleAttrs.ATTRVAL_SPLIT);
            ProdSkuAttr prodSkuAttr = new ProdSkuAttr();
            prodSkuAttr.setTenantId(prodSku.getTenantId());
            prodSkuAttr.setProdId(prodSku.getProdId());
            prodSkuAttr.setSkuId(prodSku.getSkuId());
            prodSkuAttr.setAttrId(Long.parseLong(attrVal[0]));
            prodSkuAttr.setAttrvalueDefId(attrVal[1]);
            prodSkuAttr.setState(ProductConstants.ProdSkuAttr.State.ACTIVE);
            prodSkuAttr.setOperId(prodSku.getOperId());
            prodSkuAttrAtomSV.createAttr(prodSkuAttr);
        }
    }
}
