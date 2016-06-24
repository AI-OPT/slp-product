package com.ai.slp.product.service.atom.impl.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdPictureLog;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdPictureLogMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProdPictureLogAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/6/24.
 */
@Component
public class ProdPictureLogAtomSVImpl implements IProdPictureLogAtomSV {
    @Autowired
    ProdPictureLogMapper prodPictureLogMapper;
    @Override
    public int installLog(ProdPictureLog log) {
        log.setLogId(SequenceUtil.genProdPriceLogId());
        log.setOperTime(DateUtils.currTimeStamp());
        return prodPictureLogMapper.insert(log);
    }
}
