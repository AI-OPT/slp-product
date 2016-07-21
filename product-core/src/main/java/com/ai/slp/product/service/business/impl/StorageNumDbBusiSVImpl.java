package com.ai.slp.product.service.business.impl;

import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageLog;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageLogAtomSV;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.util.IPassStorageUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by jackieliu on 16/5/26.
 */
@Service
@Transactional
public class StorageNumDbBusiSVImpl {
    private static Logger logger = LoggerFactory.getLogger(StorageNumDbBusiSVImpl.class);
    @Autowired
    ISkuStorageAtomSV skuStorageAtomSV;
    @Autowired
    IStorageAtomSV storageAtomSV;
    @Autowired
    IStorageLogAtomSV storageLogAtomSV;
    @Autowired
    IProductAtomSV productAtomSV;
    @Autowired
    IProdSkuAtomSV prodSkuAtomSV;
    @Autowired
    IProductBusiSV productBusiSV;
    /**
     * 库存量减少操作
     * @param skuId SKU标识
     * @param skuNumMap
     * @param isUser true:库存使用量;false:库存回退量
     * @param priorityChange true:库存组优先级切换;
     * @return
     */
    @Async
    public void storageNumChange(
            String tenantId,String skuId,Map<String,Integer> skuNumMap, boolean isUser, boolean priorityChange) {
        if (skuNumMap==null || skuNumMap.isEmpty())
            return;
        ProdSku prodSku = prodSkuAtomSV.querySkuById(tenantId,skuId);
        logger.info("tenantId={},skuId={},isUser={},priorityChange={}",tenantId,skuId,isUser,priorityChange);
        Product product = null;
        if (prodSku!=null){
            product = productAtomSV.selectByProductId(tenantId,prodSku.getProdId());
            logger.info("\r\nthe product[{}] is {} null,status is [{}]",
                    prodSku.getProdId(),product==null?"":"not",product==null?"null":product.getState());
        }
        Short priorityNum = null;
        Short userPriority = product==null?null:queryPriorityNumOfGroup(tenantId,product.getStorageGroupId());
        logger.info("\r\nThe product priority num is [{}]",priorityNum);
        List<Storage> autoActiveStoList = new ArrayList<>();
        Iterator<String> iterator = skuNumMap.keySet().iterator();
        while (iterator.hasNext()){
            String skuStorageId = iterator.next();
            int skuNum = skuNumMap.get(skuStorageId);
            //若为使用量,则为减少量
            if (isUser)
                skuNum = 0-skuNum;
            SkuStorage skuStorage = skuStorageAtomSV.queryById(skuStorageId,true);
            if (skuStorage ==null)
                continue;
            skuStorage.setUsableNum(skuStorage.getUsableNum()+skuNum);
            skuStorageAtomSV.updateById(skuStorage);
            //若未更新任何SKU库存,或sku库存不是"启用",则不进行处理
            if (skuStorageAtomSV.updateById(skuStorage)<1
                    ||!StorageConstants.SkuStorage.State.ACTIVE.equals(skuStorage.getState())){
                continue;
            }

            //更新库存信息
            Storage storage = storageAtomSV.queryById(skuStorage.getStorageId());
            if (storage==null)
                continue;
            logger.info("\r\nThe usable num of storage[{}]is {}.",storage.getStorageId(),storage.getUsableNum());
            storage.setUsableNum(storage.getUsableNum()+skuNum);
            logger.info("\r\nNow,the usable num of storage[{}]is {}.",storage.getStorageId(),storage.getUsableNum());
            //若库存小于等于零,且状态不为"废弃",则状态变更为"自动停用"
            if (storage.getUsableNum()<1
                    && !StorageConstants.Storage.State.DISCARD.equals(storage.getState())
                    && !StorageConstants.Storage.State.AUTO_DISCARD.equals(storage.getState())) {
                storage.setState(StorageConstants.Storage.State.AUTO_STOP);
            }

            //回退,且商品"售罄下架"
            if (!isUser && ProductConstants.Product.State.SALE_OUT.equals(product.getState())){
                if (priorityNum == null || priorityNum > storage.getPriorityNumber() ) {
                    priorityNum = storage.getPriorityNumber();
                    autoActiveStoList.clear();
                }
                if (storage.getPriorityNumber().equals(priorityNum))
                    autoActiveStoList.add(storage);
            }//若为回退,且当前优先级与库存优先级一致
            else if(!isUser && userPriority.equals(storage.getPriorityNumber())){
                storage.setState(StorageConstants.Storage.State.AUTO_ACTIVE);
                logger.info("\r\nthe state of storage[{}] change to {}.",storage.getStorageId(),storage.getState());
            }

            if (storageAtomSV.updateById(storage)>0){
                StorageLog storageLog = new StorageLog();
                BeanUtils.copyProperties(storageLog,storage);
                storageLogAtomSV.installLog(storageLog);
            }

        }

        //若库存不为空,则设置为自动启动
        logger.info("\r\nthe auto active storage size is {}",autoActiveStoList.size());
        for (int i=0;!isUser && i<autoActiveStoList.size();i++){
            Storage storage = autoActiveStoList.get(i);
            storage.setState(StorageConstants.Storage.State.AUTO_ACTIVE);
            logger.info("\r\nthe state of storage[{}] change to {}.",storage.getStorageId(),storage.getState());
            if (storageAtomSV.updateById(storage)>0){
                StorageLog storageLog = new StorageLog();
                BeanUtils.copyProperties(storageLog,storage);
                storageLogAtomSV.installLog(storageLog);
            }
        }

        //若为回退,且为售罄下架,则需要进行上架处理
        if (!isUser && product!=null
                && ProductConstants.Product.State.SALE_OUT.equals(product.getState())){

            productBusiSV.changeToSaleForStop(tenantId,prodSku.getProdId(),null);

        }
        //若为减少,且需要切换优先级检查,则进行优先级切换
        else if (isUser && product!=null && priorityChange){
            changeGroupPriority(tenantId,product.getStorageGroupId(),product.getProdId());
        }

    }

    /**
     * 切换库存组非促销优先级
     * @param tenantId
     * @param groupId
     */
    public void changeGroupPriority(String tenantId,String groupId,String productId){
        // 获取缓存客户端
        ICacheClient cacheClient = IPassStorageUtils.getClient();
        // 获取库存组的cacheKey
        String groupKey = IPassStorageUtils.genMcsStorageGroupKey(tenantId,groupId);
        // 查询当前非促销优先级
        String serial = cacheClient.hget(groupKey,StorageConstants.IPass.McsParams.GROUP_SERIAL_HTAGE);
        // 查询当前非促销优先级之后,非促销,非废弃,可用量大于零的优先级
        Short nowPriority = Short.valueOf(serial);
        //查询当前优先级之后
        List<Storage> storageList = storageAtomSV.queryNoTimeNoPrioritySelf(groupId,nowPriority,true,false);
        //若当前优先级之后不存在有效的库存,则查询当前优先级之前的有效库存
        if (CollectionUtil.isEmpty(storageList)){
            storageList = storageAtomSV.queryNoTimeNoPrioritySelf(groupId,nowPriority,false,false);
        }
        //若当前优先级之外均无有效库存,且不处于促销期内,则表示售罄
        if (CollectionUtil.isEmpty(storageList)
                && CollectionUtil.isEmpty(storageAtomSV.queryTimeActiveOfNow(groupId,false))){
            //进行售罄操作
            productBusiSV.offSale(tenantId,productId,null);
            return;
        }
        //刷新下一使用优先级
        Short usePriority = storageList.get(0).getPriorityNumber();
        flushPriorityStorage(tenantId,groupId,Short.valueOf(usePriority),true);
        //设置使用优先级
        cacheClient.hset(groupKey,StorageConstants.IPass.McsParams.GROUP_STATE_HTAGE,usePriority.toString());
    }

    /**
     * 刷新指定优先级库存缓存
     * @param tenantId
     * @param groupId
     * @param priority
     * @param autoActive 是否自动启用
     */
    public void flushPriorityStorage(String tenantId,String groupId,Short priority,boolean autoActive){
        logger.info("===刷新优先级的库存信息(开始),库存组ID:{},优先级:{},是否自动切换:{}",groupId,priority,autoActive);
        //查询已"启用"状态库存.
        List<Storage> storageList = storageAtomSV.queryActive(tenantId,groupId,false);
        //若"启用"状态库存为空,或启用库存所属优先级与当前不一致
        if (CollectionUtil.isEmpty(storageList)
                || !priority.equals(storageList.get(0).getPriorityNumber())){
            //查询优先级内所有非废弃的库存
            storageList = storageAtomSV.queryStorageByGroupIdAndPriority(groupId,priority,false);
        }
        //若当前优先级不存在非废弃的库存,则直接返回.
        if (CollectionUtil.isEmpty(storageList))
            return;
        ICacheClient cacheClient = IPassStorageUtils.getClient();

        List<String> stopList = new ArrayList<>();
        stopList.add(StorageConstants.Storage.State.STOP);
        stopList.add(StorageConstants.Storage.State.AUTO_STOP);
        //(C)
        String skuUsableKey = IPassStorageUtils.genMcsSerialSkuUsableKey(tenantId,groupId,priority.toString());
        //优先级总可用量(F)
        String priorityUsableKey = IPassStorageUtils.genMcsPriorityUsableKey(tenantId,groupId,priority.toString());
        //将优先级的库存量初始化为零
        cacheClient.set(priorityUsableKey,new Long(0).toString());
        //该优先级下,sku可用量已经做过初始化的SKU标识
        Set<String> skuIds = new HashSet<>();
        for (Storage storage:storageList){
            logger.info("当前库存信息,库存ID:{},库存总量:{},库存可用量:{}",
                    storage.getStorageId(),storage.getTotalNum(),storage.getUsableNum());
            //将可用量大于零,且停用的库存,设置为"自动启用"
            if (autoActive && storage.getUsableNum() > 0
                    && stopList.contains(storage.getState())){
                storage.setState(StorageConstants.Storage.State.AUTO_ACTIVE);
                if (storageAtomSV.updateById(storage)>0){
                    StorageLog storageLog = new StorageLog();
                    BeanUtils.copyProperties(storageLog,storage);
                    storageLogAtomSV.installLog(storageLog);
                }
            }
            //查询库存下SKU库存
            List<SkuStorage> skuStorageList = skuStorageAtomSV.queryByStorageId(storage.getStorageId());
            for (SkuStorage skuStorage:skuStorageList){
                logger.info("当前SKU库存信息,库存ID:{},SKU库存ID:{},库存总量:{},库存可用量:{}",
                        storage.getStorageId(),skuStorage.getSkuStorageId(),skuStorage.getTotalNum(),skuStorage.getUsableNum());
                //若没有将sku可用量进行初始操作,则需要将sku可用量设置为零
                if (!skuIds.contains(skuStorage.getSkuId())){
                    cacheClient.hset(skuUsableKey,skuStorage.getSkuId(),new Long(0).toString());
                }
                //设置SKU库存
                String skuStorageKey = IPassStorageUtils.genMcsSkuStorageUsableKey(
                        tenantId,groupId,priority.toString(),skuStorage.getSkuId());
                cacheClient.zadd(skuStorageKey,skuStorage.getUsableNum(),skuStorage.getSkuStorageId());
                //设置SKU库存可用量
                cacheClient.hincrBy(skuUsableKey,skuStorage.getSkuId(),skuStorage.getUsableNum());
                //设置优先级可用量
                cacheClient.incrBy(priorityUsableKey,skuStorage.getUsableNum());
            }
        }
        logger.info("===刷新指定优先级库存(结束)");
        initCacheKey(tenantId,priority,storageList.get(0));
    }

    /**
     * 初始化相关KEY
     * @param priority 优先级
     * @param storage 库存信息
     */
    public void initCacheKey(String tenantId,Short priority,Storage storage){
        logger.info("===设置相关缓存的指定失效时间(开始)");
        ICacheClient cacheClient = IPassStorageUtils.getClient();
        String groupId = storage.getStorageGroupId();
        //查询库存下SKU库存
        List<SkuStorage> skuStorageList = skuStorageAtomSV.queryByStorageId(storage.getStorageId());
        //优先级促销开始时间
        Timestamp activeTime = storage.getActiveTime();
        //优先级促销结束时间
        Timestamp inActiveTime = storage.getInactiveTime();
        //缓存有效期
        long expireTime = 0;
        //便于在促销过期后仍能完成处理,需要将过期时间延长十分钟.
        if (inActiveTime!=null)
            expireTime = inActiveTime.getTime()/1000+StorageConstants.IPass.McsParams.CACHE_EXT_TIME;
        //若存在开始时间,则添加优先级开始时间
        if (activeTime!=null){
            //(D)
            String startPriceKey = IPassStorageUtils.genMcsGroupSerialStartTimeKey(tenantId,groupId);
            cacheClient.zadd(startPriceKey,new Double(activeTime.getTime()).doubleValue(),priority.toString());
        }

        //(B)
        String serialPriceKey = IPassStorageUtils.genMcsGroupSerialPriceKey(tenantId,groupId,priority.toString());
        //设置优先级下SKU价格
        for (SkuStorage skuStorage:skuStorageList){
            //设置SKU价格
            cacheClient.hset(serialPriceKey,skuStorage.getSkuId(),skuStorage.getSalePrice().toString());
            //设置SKU库存失效时间
            if (inActiveTime!=null){
                //设置SKU库存(E)
                String skuStorageKey = IPassStorageUtils.genMcsSkuStorageUsableKey(
                        tenantId,groupId,priority.toString(),skuStorage.getSkuId());
                cacheClient.expireAt(skuStorageKey,expireTime);
            }

        }
        //若失效时间不为空,则设置过期时间,单位:秒
        if (inActiveTime!=null) {
            //(B) 用来判断是否过期,不需要延长
            cacheClient.expireAt(serialPriceKey, inActiveTime.getTime()/1000);
            //优先级中SKU库存量(C)
            String skuUsableKey = IPassStorageUtils.genMcsSerialSkuUsableKey(tenantId,groupId,priority.toString());
            cacheClient.expireAt(skuUsableKey,expireTime);
            //优先级总可用量(F)
            String priorityUsableKey = IPassStorageUtils.genMcsPriorityUsableKey(tenantId,groupId,priority.toString());
            cacheClient.expireAt(priorityUsableKey,expireTime);
        }
        logger.info("===设置相关缓存的指定失效时间(结束)");
    }

    /**
     * 查询商品当前使用且有效(非促销)优先级
     * 有效:库存组不为废弃,优先级可用量大于零
     *
     * @param tenantId
     * @param groupId
     * @return null:库存组不存在/停用/废弃.
     */
    private Short queryPriorityNumOfGroup(String tenantId, String groupId) {
        ICacheClient cacheClient = IPassStorageUtils.getClient();
        //获取库存组的cacheKey
        String groupKey = IPassStorageUtils.genMcsStorageGroupKey(tenantId,groupId);
        //库存组状态
        String status = cacheClient.hget(groupKey,StorageConstants.IPass.McsParams.GROUP_STATE_HTAGE);
        //使用当前优先级
        String priority = cacheClient.hget(groupKey,StorageConstants.IPass.McsParams.GROUP_SERIAL_HTAGE);
        //优先级中库存可用量对应KEY
        String priorityUsableKey = IPassStorageUtils.genMcsPriorityUsableKey(tenantId,groupId,priority);
        //优先级库存可用量
        String usableNumStr = cacheClient.get(priorityUsableKey);
        long usableNum = StringUtils.isNotBlank(usableNumStr)?Long.parseLong(usableNumStr):0;
        Short priorityNum = null;
        if (!StorageConstants.Storage.State.DISCARD.equals(status)
                && !StorageConstants.Storage.State.AUTO_DISCARD.equals(status)
                && usableNum>0){
            priorityNum = new Short(priority);
        }
        return priorityNum;
    }
}
