package com.ai.slp.product.dao.mapper.attach;

import java.util.Map;

public class ProductPageSqlProvider {

	/**
	 * 查询状态为不废弃的商城商品
	 * 
	 * @param param
	 * @return
	 * @author lipeng16
	 */
	public String queryProductPage(Map<String, Object> param){
		StringBuffer seqBuffer = new StringBuffer();
		seqBuffer.append("SELECT prod_id, prod_name, product_cat_id, product_cat_name, product_type, "
				+ "storage_group_id, storage_group_name, standed_prod_id, standed_product_name FROM "
				+ "product p, product_cat pc, storage_group sg, standed_product sp WHERE p.product_type = "
				+ param.get("productType") + "and p.product_cat_id = " + param.get("productCatId") 
				+ "and p.state <> '7' and p.tenant_id = " + param.get("tenantId"));
		if(param.get("prodId") != null)
			seqBuffer.append("and p.prod_id = " + param.get("prodId"));
		if(param.get("storageGroupId") != null)
			seqBuffer.append("and sg.storage_group_id = " + param.get("storageGroupId"));
		if(param.get("standedProdId") != null)
			seqBuffer.append("and sp.standed_prod_id = " + param.get("standedProdId"));
		if(param.get("prodName") != null)
			seqBuffer.append("and p.prod_name like '%" + param.get("prodName") + "%'");
		if(param.get("storageGroupName") != null)
			seqBuffer.append("and sg.storage_group_name like '%" + param.get("storageGroupName") + "%'");
		if(param.get("standedProdName") != null)
			seqBuffer.append("and sp.standed_prod_name like '%" + param.get("standedProdName") + "%'");
		return seqBuffer.toString();
	}

}
