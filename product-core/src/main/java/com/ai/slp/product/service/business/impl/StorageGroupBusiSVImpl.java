package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.storage.param.STOStorageGroup;
import com.ai.slp.product.constants.ProductCatConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.attach.ProdCatAttrAttch;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroupLog;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAttachAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupLogAtomSV;
import com.ai.slp.product.service.business.interfaces.IStorageGroupBusiSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 库存组操作
 * Created by jackieliu on 16/5/5.
 */
@Service
@Transactional
public class StorageGroupBusiSVImpl implements IStorageGroupBusiSV {
    private static Logger logger = LoggerFactory.getLogger(StorageGroupBusiSVImpl.class);
    @Autowired
    IStandedProductAtomSV standedProductAtomSV;
    @Autowired
    IStorageGroupAtomSV storageGroupAtomSV;
    @Autowired
    IStorageGroupLogAtomSV storageGroupLogAtomSV;
    @Autowired
    IProductAtomSV productAtomSV;
    @Autowired
    IProductLogAtomSV productLogAtomSV;
    @Autowired
    IProdCatAttrAttachAtomSV catAttrAttachAtomSV;
    /**
     * 添加库存组
     *
     * @param storageGroup
     * @return
     */
    @Override
    public int installGroup(STOStorageGroup storageGroup) {
        //查询标准品是否有销售属性
        String tenantId = storageGroup.getTenantId();
        String standedProdId = storageGroup.getProdId();
        StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId,standedProdId);
        if (standedProduct==null) {
            throw new BusinessException("","未找到对应标准品信息,租户id:"+tenantId+",标准品标识:"+standedProdId);
        }
        //添加库存组信息,状态默认为停用
        StorageGroup group = new StorageGroup();
        BeanUtils.copyProperties(group,storageGroup);
        //默认为停用状态
        group.setState(StorageConstants.GROUP_STATE_STOP);
        //添加库存组日志
        int installNum = storageGroupAtomSV.installGroup(group);
        if (installNum>0){
            StorageGroupLog groupLog = new StorageGroupLog();
            BeanUtils.copyProperties(groupLog,groupLog);
            storageGroupLogAtomSV.install(groupLog);
        }
        //查询类目是否有销售属性
        List<ProdCatAttrAttch> catAttrAttches = catAttrAttachAtomSV.queryAttrOfByIdAndType(
                tenantId,standedProduct.getProductCatId(), ProductCatConstants.ATTR_TYPE_SALE);

        //添加商品,商品名称同标准品名称
        Product product = new Product();
        product.setTenantId(tenantId);
        product.setProductCatId(standedProduct.getProductCatId());
        product.setStandedProdId(standedProdId);
        product.setStorageGroupId(group.getStorageGroupId());
        product.setProductType(standedProduct.getProductType());
        product.setProdName(standedProduct.getStandedProductName());//使用标准品名称设置为商品名称
        product.setIsSaleAttr(catAttrAttches==null||catAttrAttches.isEmpty()?"N":"Y");
        product.setCreateTime(storageGroup.getCreateTime());
//        product.setState();
        product.setOperId(group.getCreateId());
        product.setOperTime(storageGroup.getOperTime());
        productAtomSV.installProduct(product);
        return installNum;
    }
}
