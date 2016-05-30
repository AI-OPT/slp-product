package com.ai.slp.product.service.business.impl;

import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.api.storageserver.param.StorageNumRes;
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
import com.ai.slp.product.service.business.interfaces.IStorageNumBusiSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by jackieliu on 16/5/26.
 */
@Service
@Transactional
public class StorageNumDbBusiSVImpl {
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
    /**
     * 库存量减少操作
     * @param skuId SKU标识
     * @param skuNumMap
     * @param isUser true:库存使用量;false:库存回退量
     * @param priorityChange true:库存组优先级切换;
     * @return
     */
    @Async
    public void storageNumChange(String tenantId,String skuId,Map<String,Integer> skuNumMap, boolean isUser, boolean priorityChange) {
        if (skuNumMap==null || skuNumMap.isEmpty())
            return;
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
            //若SKU库存小于等于零,且状态不为"废弃",则设置为"自动停用
            if (skuStorage.getUsableNum()<=0
                    && !StorageConstants.SkuStorage.State.AUTO_DISCARD.equals(skuStorage.getState())){
                skuStorage.setState(StorageConstants.SkuStorage.State.AUTO_STOP);
            //若SKU库存大于零,且状态为"自动停用",则设置为"启用".
            } else if (skuStorage.getUsableNum()>0
                    && StorageConstants.SkuStorage.State.AUTO_STOP.equals(skuStorage.getState())){
                skuStorage.setState(StorageConstants.SkuStorage.State.ACTIVE);
            }
            skuStorageAtomSV.updateById(skuStorage);

            //更新库存信息
            Storage storage = storageAtomSV.queryById(skuStorage.getStorageId());
            if (storage==null)
                continue;
            storage.setUsableNum(skuStorage.getUsableNum()+skuNum);
            //若库存小于等于零,且状态不为"废弃",则状态变更为"自动停用"
            if (skuStorage.getUsableNum()<1
                    && !StorageConstants.Storage.State.DISCARD.equals(storage.getState())
                    && !StorageConstants.Storage.State.AUTO_DISCARD.equals(storage.getState())) {
                storage.setState(StorageConstants.Storage.State.AUTO_STOP);
            }

            if (storageAtomSV.updateById(storage)>0){
                StorageLog storageLog = new StorageLog();
                BeanUtils.copyProperties(storageLog,storage);
                storageLogAtomSV.installLog(storageLog);
            }
        }
        ProdSku prodSku = prodSkuAtomSV.querySkuById(tenantId,skuId);
        Product product = prodSku==null?null:productAtomSV.selectByProductId(tenantId,prodSku.getProdId());
        //若为回退,则检查是否售罄下架
        if (!isUser && product!=null
                && ProductConstants.Product.State.IN_SALE.equals(product.getState())){
            //如果商品为售罄下架,则需要检查库存可用量

        }
        //若为减少,且需要切换优先级检查,则进行优先级切换
        else if (isUser && priorityChange){

            // 如果检查优先级属于促销优先级,且当前时间在促销时间内,则不处理.
            // 查询当前优先级下库存可用量,数据库中
            // 大于零,则不处理
            // 小于等于零,则检查下一个优先级中库存可用量,数据库中,非废弃的可用量大于零的
            // 若不存在库存可用量大于零的优先级,则为售罄,将销售商品设置为"售罄下架"
        }

    }



}
