package com.ai.slp.product.service.atom.impl.product;

import com.ai.slp.product.dao.mapper.bo.product.ProductLog;
import com.ai.slp.product.dao.mapper.interfaces.product.ProductLogMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProductLogAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/5/5.
 */
@Component
public class ProductLogAtomSVImpl implements IProductLogAtomSV {
    @Autowired
    ProductLogMapper productLogMapper;

    /**
     * 添加商品日志
     *
     * @param productLog
     * @return
     */
    @Override
    public int install(ProductLog productLog) {
        if (productLog.getOperTime()==null){
        	productLog.setOperTime(DateUtils.currTimeStamp());
        }
        productLog.setLogId(SequenceUtil.createProductLogId());
        return productLogMapper.insert(productLog);
    }
}
