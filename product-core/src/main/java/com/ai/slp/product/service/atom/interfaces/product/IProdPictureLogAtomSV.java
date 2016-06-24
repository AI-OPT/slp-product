package com.ai.slp.product.service.atom.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdPictureLog;

/**
 * 销售商品图片日志原子操作
 * Created by jackieliu on 16/6/24.
 */
public interface IProdPictureLogAtomSV {
    /**
     * 插入图片日志
     * @param log
     * @return
     */
    public int installLog(ProdPictureLog log);
}
