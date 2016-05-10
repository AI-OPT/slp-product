package com.ai.slp.product.service.atom.impl.storage;

import com.ai.slp.product.dao.mapper.bo.storage.StorageLog;
import com.ai.slp.product.dao.mapper.interfaces.storage.StorageLogMapper;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageLogAtomSV;
import com.ai.slp.product.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/5/9.
 */
@Component
public class StorageLogAtomSVImpl implements IStorageLogAtomSV {
    @Autowired
    StorageLogMapper storageLogMapper;

    @Override
    public int installLog(StorageLog storageLog) {
        storageLog.setLogId(SequenceUtil.genStorageLogId());
        return storageLogMapper.insert(storageLog);
    }
}
