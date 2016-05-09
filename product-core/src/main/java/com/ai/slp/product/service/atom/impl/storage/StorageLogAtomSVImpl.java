package com.ai.slp.product.service.atom.impl.storage;

import com.ai.slp.product.dao.mapper.bo.storage.StorageLog;
import com.ai.slp.product.dao.mapper.interfaces.storage.StorageLogMapper;
import com.ai.slp.product.service.atom.interfaces.ISysSequenceCreditAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageLogAtomSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/5/9.
 */
@Component
public class StorageLogAtomSVImpl implements IStorageLogAtomSV {
    @Autowired
    StorageLogMapper storageLogMapper;
    @Autowired
    ISysSequenceCreditAtomSV creditAtomSV;

    @Override
    public int installLog(StorageLog storageLog) {
        storageLog.setLogId(creditAtomSV.getSeqByName()+"");
        return storageLogMapper.insert(storageLog);
    }
}
