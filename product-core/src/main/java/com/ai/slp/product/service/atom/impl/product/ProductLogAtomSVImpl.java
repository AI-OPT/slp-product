package com.ai.slp.product.service.atom.impl.product;

import com.ai.slp.product.dao.mapper.bo.product.ProductLog;
import com.ai.slp.product.dao.mapper.interfaces.product.ProductLogMapper;
import com.ai.slp.product.service.atom.interfaces.ISysSequenceCreditAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductLogAtomSV;
import com.ai.slp.product.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/5/5.
 */
@Component
public class ProductLogAtomSVImpl implements IProductLogAtomSV {
    @Autowired
    ProductLogMapper productLogMapper;
    @Autowired
    ISysSequenceCreditAtomSV creditAtomSV;

    /**
     * 添加商品日志
     *
     * @param productLog
     * @return
     */
    @Override
    public int install(ProductLog productLog) {
        if (productLog.getOperTime()==null)
            productLog.setOperTime(DateUtils.currTimeStamp());
        productLog.setLogId(creditAtomSV.getSeqByName()+"");
        return productLogMapper.insert(productLog);
    }
}
