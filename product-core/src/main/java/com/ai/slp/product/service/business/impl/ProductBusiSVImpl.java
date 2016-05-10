package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.product.param.SkuAttrInfo;
import com.ai.slp.product.api.product.param.SkuAttrValInfo;
import com.ai.slp.product.api.product.param.SkuInfo;
import com.ai.slp.product.api.product.param.SkuSetForProduct;
import com.ai.slp.product.constants.ProductCatConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.attach.ProdCatAttrAttch;
import com.ai.slp.product.dao.mapper.bo.StandedProdAttr;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.product.ProductLog;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAttachAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by jackieliu on 16/5/5.
 */
@Service
@Transactional
public class ProductBusiSVImpl implements IProductBusiSV {
    @Autowired
    IStandedProductAtomSV standedProductAtomSV;
    @Autowired
    IStandedProdAttrAtomSV standedProdAttrAtomSV;
    @Autowired
    IProductAtomSV productAtomSV;
    @Autowired
    IProductLogAtomSV productLogAtomSV;
    @Autowired
    IProdCatAttrAttachAtomSV catAttrAttachAtomSV;
    @Autowired
    IStorageAtomSV storageAtomSV;
    @Autowired
    IProdSkuAtomSV prodSkuAtomSV;
    @Autowired
    IProdSkuAttrAtomSV prodSkuAttrAtomSV;

    /**
     * 添加商城商品
     *
     * @param group
     * @return
     */
    @Override
    public int addProductWithStorageGroup(StorageGroup group, Long operId) {
        //查询库存组对应的标准品
        String tenantId = group.getTenantId();
        String standedProdId = group.getStandedProdId();
        StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId,standedProdId);
        if (standedProduct==null) {
            throw new BusinessException("","未找到对应标准品信息,租户id:"+tenantId+",标准品标识:"+standedProdId);
        }
        //查询类目是否有销售属性
        List<ProdCatAttrAttch> catAttrAttches = catAttrAttachAtomSV.queryAttrOfByIdAndType(
                tenantId,standedProduct.getProductCatId(), ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE);

        //添加商品,商品名称同标准品名称
        Product product = new Product();
        product.setTenantId(tenantId);
        product.setProductCatId(standedProduct.getProductCatId());
        product.setStandedProdId(standedProdId);
        product.setStorageGroupId(group.getStorageGroupId());
        product.setProductType(standedProduct.getProductType());
        product.setProdName(standedProduct.getStandedProductName());//使用标准品名称设置为商品名称
        product.setIsSaleAttr(catAttrAttches==null||catAttrAttches.isEmpty()?"N":"Y");
        product.setState(ProductConstants.STATE_ADD);//新增状态
        product.setOperId(group.getCreateId());
        int installNum = productAtomSV.installProduct(product);
        if (installNum > 0){
            ProductLog productLog = new ProductLog();
            BeanUtils.copyProperties(productLog,product);
            productLogAtomSV.install(productLog);
        }
        return installNum;
    }

    /**
     * 查询指定商品下的SKU信息
     *
     * @param tenantId
     * @param prodId
     * @return
     */
    @Override
    public SkuSetForProduct querySkuByProdId(String tenantId, String prodId) {
        //查询商品信息
        Product product = productAtomSV.selectByProductId(tenantId,prodId);
        if (product==null)
            throw new BusinessException("","查询商品信息不存在,租户ID:"+tenantId+",商品标识:"+prodId);

        //查询商品的SKU信息
        Map<String,SkuInfo> skuInfoMap = new HashMap<>();
        Map<String,String> saleAttrsMap = new HashMap<>();
        SkuSetForProduct skuSetForProduct = new SkuSetForProduct();
        skuSetForProduct.setSkuInfoMap(skuInfoMap);
        skuSetForProduct.setSaleAttrsMap(saleAttrsMap);
        List<ProdSku> skuList = prodSkuAtomSV.querySkuOfProd(tenantId,prodId);
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
                    tenantId,prodId,attrAttch.getAttrId());
            for (StandedProdAttr prodAttr:prodAttrs){
                //若SKU不包含当前属性值,则查询下一个属性值
                if (prodSkuAttrAtomSV.queryAttrValNumOfSku(tenantId,prodId,prodAttr.getAttrvalueDefId())<=0)
                    continue;
                attrValSet.add(prodAttr.getAttrvalueDefId());
                SkuAttrValInfo valInfo = new SkuAttrValInfo();
                BeanUtils.copyProperties(valInfo,prodAttr);
                valInfoList.add(valInfo);
            }
        }

        return skuSetForProduct;
    }

    /**
     * 对停用下架的商品进行上架处理
     *
     * @param tenantId
     * @param prodId
     */
    @Override
    public void changeToSaleForStop(String tenantId, String prodId,Long operId) {
        Product product = productAtomSV.selectByGroupId(tenantId,prodId);
        if (prodId == null){
            throw new BusinessException("","未找到相关的商品信息,租户ID:"+tenantId+",商品标识:"+prodId);
        }
        //若商品状态不是"停用下架",则不进行处理
        if(!ProductConstants.STATE_STOP.equals(product.getState())){
            return;
        }
        //检查商品是否有库存,若没有,则直接切换至"售罄下架"
        List<Storage> storageList = storageAtomSV.queryActive(tenantId,prodId,true);
        //直接切换至"售罄下架"
        if (storageList==null || storageList.isEmpty()){
            product.setState(ProductConstants.STATE_SALE_OUT);
        }else { //切换至上架
            product.setState(ProductConstants.STATE_IN_SALE);
        }
        product.setOperId(operId);
        //添加日志
        if (productAtomSV.updateById(product)>0){
            ProductLog productLog = new ProductLog();
            BeanUtils.copyProperties(productLog,product);
            productLogAtomSV.install(productLog);
        }
    }


    /**
     * 进行停用下架
     *
     * @param tenantId
     * @param prodId
     */
    @Override
    public void stopProduct(String tenantId, String prodId,Long operId) {
        Product product = productAtomSV.selectByGroupId(tenantId,prodId);
        if (prodId == null){
            throw new BusinessException("","未找到相关的商品信息,租户ID:"+tenantId+",商品标识:"+prodId);
        }
        //若商品状态不是"在售",则不进行处理
        if(!ProductConstants.STATE_IN_SALE.equals(product.getState())){
            return;
        }
        //设置为停用下架状态
        product.setState(ProductConstants.STATE_STOP);
        product.setOperId(operId);
        //添加日志
        if (productAtomSV.updateById(product)>0){
            ProductLog productLog = new ProductLog();
            BeanUtils.copyProperties(productLog,product);
            productLogAtomSV.install(productLog);
        }
    }

    /**
     * 废弃商品
     *
     * @param tenantId
     * @param prodId
     */
    @Override
    public void discardProduct(String tenantId, String prodId,Long operId) {
        Product product = productAtomSV.selectByGroupId(tenantId,prodId);
        if (prodId == null){
            throw new BusinessException("","未找到相关的商品信息,租户ID:"+tenantId+",商品标识:"+prodId);
        }
        //设置为停用下架状态
        product.setState(ProductConstants.STATE_DISCARD);
        product.setOperId(operId);
        //添加日志
        if (productAtomSV.updateById(product)>0){
            ProductLog productLog = new ProductLog();
            BeanUtils.copyProperties(productLog,product);
            productLogAtomSV.install(productLog);
        }
    }
}
