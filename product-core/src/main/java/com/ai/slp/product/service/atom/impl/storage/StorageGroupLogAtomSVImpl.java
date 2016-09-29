package com.ai.slp.product.service.atom.impl.storage;

import com.ai.slp.product.dao.mapper.bo.storage.StorageGroupLog;
import com.ai.slp.product.dao.mapper.interfaces.storage.StorageGroupLogMapper;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupLogAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/5/5.
 */
@Component
public class StorageGroupLogAtomSVImpl implements IStorageGroupLogAtomSV {
    @Autowired
    StorageGroupLogMapper groupLogMapper;
    /**
     * 添加库存组日志
     *
     * @param groupLog
     * @return
     */
    @Override
    public int install(StorageGroupLog groupLog) {
        groupLog.setLogId(SequenceUtil.genStorageGroupLogId());
        if (groupLog.getOperTime()==null){
        	groupLog.setOperTime(DateUtils.currTimeStamp());
        }
        return groupLogMapper.insert(groupLog);
    }
}
