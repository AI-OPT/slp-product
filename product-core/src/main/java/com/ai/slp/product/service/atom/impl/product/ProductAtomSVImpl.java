package com.ai.slp.product.service.atom.impl.product;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.product.ProductCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProductMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import com.ai.slp.product.vo.ProductPageQueryVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jackieliu on 16/5/5.
 */
@Component
public class ProductAtomSVImpl implements IProductAtomSV {
    @Autowired
    ProductMapper productMapper;
    /**
     * 添加销售商品信息
     *
     * @param product
     * @return
     */
    @Override
    public int installProduct(Product product) {
        product.setProdId(SequenceUtil.createProductProdId());
        if (product.getCreateTime() == null)
            product.setCreateTime(DateUtils.currTimeStamp());
        product.setOperTime(product.getCreateTime());
        return productMapper.insert(product);
    }

    /**
     * 查询指定库存组下的销售商品
     *
     * @param tenantId
     * @param groupId
     * @return
     */
    @Override
    public Product selectByGroupId(String tenantId, String groupId) {
        ProductCriteria example = new ProductCriteria();
        example.setOrderByClause("CREATE_TIME desc");
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andStorageGroupIdEqualTo(groupId);
        List<Product> products = productMapper.selectByExample(example);
        return products==null||products.isEmpty()?null:products.get(0);
    }

    /**
     * 查询指定商品
     *
     * @param tenantId
     * @param prodId
     * @return
     */
    @Override
    public Product selectByProductId(String tenantId, String prodId) {
        ProductCriteria example = new ProductCriteria();
        example.setOrderByClause("CREATE_TIME desc");
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andProdIdEqualTo(prodId);
        List<Product> products = productMapper.selectByExample(example);
        return products==null||products.isEmpty()?null:products.get(0);
    }

    /**
     * 根据标识更新商品信息
     *
     * @param product
     * @return
     */
    @Override
    public int updateById(Product product) {
        product.setOperTime(DateUtils.currTimeStamp());
        return productMapper.updateByPrimaryKey(product);
    }

	@Override
	public PageInfo<Product> selectProductPage(ProductPageQueryVo productPageQueryVo) {
		ProductCriteria example = new ProductCriteria();
		example.createCriteria().andTenantIdEqualTo(productPageQueryVo.getTenantId()).andProductCatIdEqualTo(productPageQueryVo.getProductCatId());
		return null;
	}
}
