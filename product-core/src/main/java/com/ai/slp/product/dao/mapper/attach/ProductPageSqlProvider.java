package com.ai.slp.product.dao.mapper.attach;

import com.ai.slp.product.vo.ProdRouteGroupQueryVo;
import org.apache.commons.lang.StringUtils;

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
		if(param.get("prodId") != null){
			seqBuffer.append("and p.prod_id = " + param.get("prodId"));
		}
		if(param.get("storageGroupId") != null){
			seqBuffer.append("and sg.storage_group_id = " + param.get("storageGroupId"));
		}
		if(param.get("standedProdId") != null){
			seqBuffer.append("and sp.standed_prod_id = " + param.get("standedProdId"));
		}
		if(param.get("prodName") != null){
			seqBuffer.append("and p.prod_name like '%" + param.get("prodName") + "%'");
		}
		if(param.get("storageGroupName") != null){
			seqBuffer.append("and sg.storage_group_name like '%" + param.get("storageGroupName") + "%'");
		}
		if(param.get("standedProdName") != null){
			seqBuffer.append("and sp.standed_prod_name like '%" + param.get("standedProdName") + "%'");
		}
		seqBuffer.append("p.CREATE_TIME DESC");
		return seqBuffer.toString();
	}

	/**
	 * 统计商品数量包括路由组条件约束
	 * @param queryVo
	 * @return
     */
	public String countProdRouteGroup(ProdRouteGroupQueryVo queryVo){
		StringBuffer seqBuffer = new StringBuffer();
		seqBuffer.append("select count(*) ");
		seqBuffer.append(addBuild(queryVo));
		return seqBuffer.toString();
	}

	/**
	 * 统计商品数量包括路由组条件约束
	 * @param queryVo
	 * @return
	 */
	public String queryProdRouteGroup(ProdRouteGroupQueryVo queryVo){
		StringBuffer seqBuffer = new StringBuffer();
		seqBuffer.append("select p.PROD_ID,p.PROD_NAME,p.STANDED_PROD_ID,p.STATE,sp.STANDED_PRODUCT_NAME,sg.ROUTE_GROUP_ID");
		seqBuffer.append(addBuild(queryVo));
		//排序信息
		if (StringUtils.isNotBlank(queryVo.getOrderByClause())){
			seqBuffer.append(" order by "+queryVo.getOrderByClause());
		}
		//limit信息
		if (queryVo.getLimitStart()!=null && queryVo.getLimitStart()>=0) {
			seqBuffer.append(" limit "+queryVo.getLimitStart());
			if (queryVo.getLimitEnd()!=null && queryVo.getLimitEnd() >0){
				seqBuffer.append(","+queryVo.getLimitEnd());
			}
		}
		return seqBuffer.toString();
	}
	//拼接sql
	private String addBuild(ProdRouteGroupQueryVo queryVo){
		StringBuffer seqBuffer = new StringBuffer();
		seqBuffer.append(" from product p,storage_group sg,standed_product sp");
		seqBuffer.append(" WHERE p.STORAGE_GROUP_ID = sg.STORAGE_GROUP_ID and p.STANDED_PROD_ID=sp.STANDED_PROD_ID");
		seqBuffer.append(" and p.TENANT_ID = '"+queryVo.getTenantId()+"'");
		if (StringUtils.isNotBlank(queryVo.getProdId())){
			seqBuffer.append(" and p.PROD_ID like '%"+queryVo.getProdId()+"%' ");
		}
		if (StringUtils.isNotBlank(queryVo.getProdName())){
			seqBuffer.append(" and p.PROD_NAME like '%"+queryVo.getProdName()+"%' ");
		}
		if (StringUtils.isNotBlank(queryVo.getStandedProdId())){
			seqBuffer.append(" and p.STANDED_PROD_ID like '%"+queryVo.getStandedProdId()+"%' ");
		}
		if (StringUtils.isNotBlank(queryVo.getStandedProdName())){
			seqBuffer.append(" and sp.STANDED_PRODUCT_NAME like '%"+queryVo.getStandedProdName()+"%' ");
		}
		if (StringUtils.isNotBlank(queryVo.getRouteGroupId())){
			seqBuffer.append(" and sg.ROUTE_GROUP_ID like '%"+queryVo.getRouteGroupId()+"%' ");
		}
		return seqBuffer.toString();
	}
}
