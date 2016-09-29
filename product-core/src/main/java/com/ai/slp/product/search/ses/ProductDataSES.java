package com.ai.slp.product.search.ses;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sdk.components.ses.base.AbstractSES;
import com.ai.slp.product.dao.mapper.attach.ProdSkuInfoSes;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.business.interfaces.search.ISKUIndexBusiSV;
import com.alibaba.fastjson.JSON;

/**
 * 刷新搜索引擎数据
 */
@Component
public class ProductDataSES extends AbstractSES {
	private static final Logger LOG=LoggerFactory.getLogger(ProductDataSES.class);

    private static final int MAX_SIZE = 1000;
    @Autowired
    private IProdSkuAtomSV prodSkuAtomSV;
	@Autowired
	private ISKUIndexBusiSV skuIndexBusiSV;
	@Override
    public void write() throws Exception {
        ExecutorService pool = null;
        try {
            LOG.info(">>>>>>SES刷新数据开始");
            pool = Executors.newCachedThreadPool();

            List<SKUInfo> skuInfoList;
            int start = 0;
            while (true) {
                // 获取指定条目的sku信息
                List<ProdSkuInfoSes> skuInfoSesList = prodSkuAtomSV.queryInSaleForSearch(start,MAX_SIZE);
                skuInfoList = skuIndexBusiSV.fillSkuInfo(skuInfoSesList);

                if (!skuInfoList.isEmpty()) {
                    Thread t = new ProductDataSESTread(skuInfoList);
                    pool.execute(t);
                    LOG.info("SESData:" + JSON.toJSONString(skuInfoList));
                }
                if (skuInfoList.size() < MAX_SIZE) {
                    break;
                }

                start += MAX_SIZE;
            }
            LOG.info(">>>>>>SES刷新数据结束");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally{
            if(pool!=null){
                pool.shutdown();
            }

        }
    }

}
