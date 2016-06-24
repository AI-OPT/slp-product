package com.ai.slp.product.service.atom.impl.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdAttrLog;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdAttrLogMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProdAttrLogAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/6/24.
 */
@Component
public class ProdAttrLogAtomSVImpl implements IProdAttrLogAtomSV {
    @Autowired
    ProdAttrLogMapper prodAttrLogMapper;
    @Override
    public int installLog(ProdAttrLog attrLog) {
        attrLog.setLogId(SequenceUtil.genProdAttrLogId());
        attrLog.setOperTime(DateUtils.currTimeStamp());
        return prodAttrLogMapper.insert(attrLog);
    }
}
