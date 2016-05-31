package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.slp.product.dao.mapper.bo.product.ProdSaleAll;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.service.atom.interfaces.product.IProdSaleAllAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.business.interfaces.IProdSaleAllBusiSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jackieliu on 16/5/31.
 */
@Service
@Transactional
public class ProdSaleAllBusiSVImpl implements IProdSaleAllBusiSV {
    private static Logger logger = LoggerFactory.getLogger(ProdSaleAllBusiSVImpl.class);
    @Autowired
    IProdSaleAllAtomSV prodSaleAllAtomSV;
    @Autowired
    IProdSkuAtomSV prodSkuAtomSV;
    /**
     * 增加商品销量
     *
     * @param tenantId
     * @param skuId
     * @param saleNum
     */
    @Override
    public void addSaleNum(String tenantId, String skuId, int saleNum) {
        ProdSku prodSku = prodSkuAtomSV.querySkuById(tenantId,skuId);
        if (prodSku==null){
            logger.warn("未查询相应SKU信息,租户ID:{},SKU标识:{}",tenantId,skuId);
            throw new BusinessException("","未查询相应SKU信息,租户ID:"+tenantId+",SKU标识:{}"+skuId);
        }
        ProdSaleAll prodSaleAll = prodSaleAllAtomSV.querySaleAllOfSku(tenantId,skuId);
        if (prodSaleAll==null){
            prodSaleAll = new ProdSaleAll();
            prodSaleAll.setProdId(prodSku.getProdId());
            prodSaleAll.setSaleNum(new Long(saleNum));
            prodSaleAll.setSkuId(skuId);
            prodSaleAll.setTenantId(tenantId);
            prodSaleAllAtomSV.installSaleAll(prodSaleAll);
        }else {
            prodSaleAll.setSaleNum(prodSaleAll.getSaleNum()+saleNum);
            prodSaleAllAtomSV.updateById(prodSaleAll);
        }
    }
}