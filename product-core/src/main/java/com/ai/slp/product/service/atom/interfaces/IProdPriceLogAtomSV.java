package com.ai.slp.product.service.atom.interfaces;

import com.ai.slp.product.dao.mapper.bo.ProdPriceLog;

/**
 * 添加价格类日志
 * 
 * Date: 2016年5月5日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public interface IProdPriceLogAtomSV {
    /**
     * 添加价格类日志
     * 
     * @param prodPriceLog
     * @return
     * @author lipeng16
     */
    public int insert(ProdPriceLog prodPriceLog);
}
