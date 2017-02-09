package com.ai.slp.product.dao.mapper.attach;

import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.vo.StoGroupPageQueryVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by jackieliu on 16/8/1.
 */
public class StorageGroupPageSqlProvider {
    private static final Logger logger = LoggerFactory.getLogger(StorageGroupPageSqlProvider.class);

    /**
     * 产生查询语句
     *
     * @param params
     * @return
     * @author liutong5
     */
    public String queryPageSql(Map<String, Object> params){
        StoGroupPageQueryVo queryVo = (StoGroupPageQueryVo)params.get("vo");//与@Param
        String limitStr = (String) params.get("limitStr");
        StringBuffer seqBuffer = new StringBuffer();
        seqBuffer.append("select sg.TENANT_ID,sg.STORAGE_GROUP_ID,sg.STORAGE_GROUP_NAME,sp.STANDED_PROD_ID,"
                + "sp.STANDED_PRODUCT_NAME,sg.STATE,sg.OPER_TIME,sg.OPER_ID,sg.SERIAL_NUMBER,"
                + "sg.LOW_SALE_PRICE,sg.HIGH_SALE_PRICE,sg.CREATE_ID,sg.CREATE_TIME ");
        seqBuffer = genQueryStr(seqBuffer,queryVo);
        //排序
        if (StringUtils.isNotBlank(queryVo.getOrderByClause())){
        	seqBuffer.append(" order by "+queryVo.getOrderByClause()+" ");
        }
        seqBuffer.append(limitStr);//添加分页内容
        logger.debug("\r\n"+seqBuffer.toString());
        return seqBuffer.toString();
    }

    /**
     * 产生统计语句
     * @param params
     * @return
     */
    public String countSql(Map<String, Object> params){
        StoGroupPageQueryVo queryVo = (StoGroupPageQueryVo)params.get("vo");
        StringBuffer seqBuffer = new StringBuffer();
        seqBuffer.append("select COUNT(sg.STORAGE_GROUP_ID) ");
        return genQueryStr(seqBuffer,queryVo).toString();
    }
    //商品相关查询的sql拼接
    private StringBuffer genQueryStr(StringBuffer seqBuffer,StoGroupPageQueryVo queryVo){
        seqBuffer.append("from standed_product as sp,storage_group as sg ");
        seqBuffer.append("where sp.STANDED_PROD_ID = sg.STANDED_PROD_ID and sg.TENANT_ID= #{vo.tenantId} ");
        //销售商(租户)标识
        if (!CommonConstants.ALL_SUPPLIER.equals(queryVo.getSupplierId())){
        	seqBuffer.append(" and sg.SUPPLIER_ID = #{vo.supplierId}% ");
        }
        //库存组名称
        if (StringUtils.isNotBlank(queryVo.getStorageGroupName())){
        	seqBuffer.append(" and sg.STORAGE_GROUP_NAME like #{vo.storageGroupName}% ");
        }
        //库存组ID
        if (StringUtils.isNotBlank(queryVo.getStorageGroupId())){
        	seqBuffer.append(" and sg.STORAGE_GROUP_ID like #{vo.storageGroupId} ");
        }
        //标准品ID
        if (StringUtils.isNotBlank(queryVo.getStandedProdId())){
        	seqBuffer.append(" and sp.STANDED_PROD_ID like #{vo.standedProdId}");
        }
        //标准品名称
        if (StringUtils.isNotBlank(queryVo.getStandedProductName())){
        	seqBuffer.append(" and sp.STANDED_PRODUCT_NAME like #{vo.standedProductName}");
        }
        //库存组状态
        if (StringUtils.isNotBlank(queryVo.getState())){
        	seqBuffer.append(" and sg.STATE = #{vo.state} ");
        }
        //操作时间开始
        if (queryVo.getOperTimeStart()!=null){
        	seqBuffer.append(" and sg.OPER_TIME >= #{vo.operTimeStart} ");
        }
        //操作时间结束
        if (queryVo.getOperTimeEnd()!=null){
        	seqBuffer.append(" and sg.OPER_TIME <= #{vo.operTimeEnd} ");
        }
        //创建时间开始
        if (queryVo.getCreateTimeStart() !=null){
        	seqBuffer.append(" and sg.CREATE_TIME >= #{vo.createTimeStart} ");
        }
        //创建时间结束
        if (queryVo.getCreateTimeEnd()!=null){
        	seqBuffer.append(" and sg.CREATE_TIME <= #{vo.createTimeEnd} ");
        }
        //操作者标识
        if (queryVo.getOperId()!=null){
        	seqBuffer.append(" and sg.OPER_ID = #{vo.operId} ");
        }
        logger.debug("\r\n"+seqBuffer.toString());
        return seqBuffer;
    }
}
