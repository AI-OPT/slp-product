package com.ai.slp.product.service.atom.impl.product;

import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.dao.mapper.attach.ProdSaleAllAttachMapper;
import com.ai.slp.product.dao.mapper.bo.product.ProdSaleAll;
import com.ai.slp.product.dao.mapper.bo.product.ProdSaleAllCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdSaleAllMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProdSaleAllAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jackieliu on 16/5/31.
 */
@Component
public class ProdSaleAllAtomSVImpl implements IProdSaleAllAtomSV {
    @Autowired
    ProdSaleAllMapper saleAllMapper;
    @Autowired
    ProdSaleAllAttachMapper saleAllAttachMapper;

    /**
     * 插入SKU销量信息
     *
     * @param prodSaleAll
     * @return
     */
    @Override
    public int installSaleAll(ProdSaleAll prodSaleAll) {
        prodSaleAll.setProSaleId(SequenceUtil.genProdSaleAllId());
        prodSaleAll.setUpdateTime(DateUtils.currTimeStamp());
        return saleAllMapper.insert(prodSaleAll);
    }

    /**
     * 查询某个SKU的销量
     *
     * @param tenantId
     * @param skuId
     * @return
     */
    @Override
    public ProdSaleAll querySaleAllOfSku(String tenantId, String skuId) {
        ProdSaleAllCriteria example = new ProdSaleAllCriteria();
        
       // example.setOrderByClause("UPDATE_TIME desc");
        
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andSkuIdEqualTo(skuId);
        List<ProdSaleAll> prodSaleAllList = saleAllMapper.selectByExample(example);
        return CollectionUtil.isEmpty(prodSaleAllList)?null:prodSaleAllList.get(0);
    }

    /**
     * 更新商品销售信息
     *
     * @param prodSaleAll
     * @return
     */
    @Override
    public int updateById(ProdSaleAll prodSaleAll) {
        prodSaleAll.setUpdateTime(DateUtils.currTimeStamp());
        return saleAllMapper.updateByPrimaryKey(prodSaleAll);
    }

    /**
     * 查询指定销售商品的销量
     *
     * @param tenantId
     * @param productId
     * @return
     */
    @Override
    public long queryNumOfProduc(String tenantId, String productId) {
        Long num = saleAllAttachMapper.selectCatAttr(tenantId,productId);
        return num==null?0:num.longValue();
    }
}
