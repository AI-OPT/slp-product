package com.ai.slp.product.service.atom.impl.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdSkuLog;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdSkuLogMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuLogAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SKU单品日志原子操作
 * Created by jackieliu on 16/5/12.
 */
@Component
public class ProdSkuLogAtomSV implements IProdSkuLogAtomSV{
    @Autowired
    ProdSkuLogMapper prodSkuLogMapper;
    /**
     * 插入SKU日志
     *
     * @param skuLog
     * @return
     */
    @Override
    public int install(ProdSkuLog skuLog) {
        skuLog.setOperTime(DateUtils.currTimeStamp());
        skuLog.setLogId(SequenceUtil.genSkuLogId());
        return prodSkuLogMapper.insert(skuLog);
    }
}
