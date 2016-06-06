package com.ai.slp.product.cache;

import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.service.business.interfaces.IStorageGroupBusiSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by jackieliu on 16/6/6.
 */
public class StorageCacheFlushThread extends Thread {
    private static Logger logger = LoggerFactory.getLogger(StorageCacheFlushThread.class);
    IStorageGroupBusiSV groupBusiSV;
    private List<StorageGroup> groupList;
    public StorageCacheFlushThread(IStorageGroupBusiSV groupBusiSV,List<StorageGroup> groupList){
        this.groupBusiSV = groupBusiSV;
        this.groupList = groupList;
    }

    @Override
    public synchronized void start() {
        if (CollectionUtil.isEmpty(groupList))
            return;
        for (StorageGroup group:groupList){
            logger.info("Start flush----TenantId={},GroupId={},",group.getTenantId(),group.getStorageGroupId());
//            groupBusiSV.flushStorageCache(group);
            logger.info("End flush");
        }
    }
}
