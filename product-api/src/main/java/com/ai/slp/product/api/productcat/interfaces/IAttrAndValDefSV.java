package com.ai.slp.product.api.productcat.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.BaseListResponse;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.productcat.param.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 属性及属性值管理接口
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
@Path("/attrAndValDef")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IAttrAndValDefSV {
    
    /**
     * 属性分页查询
     * 
     * @param attrDefParam
     * @return 符合页数的属性集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_VAL_0200
    *  @RestRelativeURL attrAndValDef/pageAttr
     */
	@POST
	@Path("/pageAttr")
    public PageInfoResponse<AttrDefInfo> queryPageAttrs(AttrDefParam attrDefParam)
            throws BusinessException, SystemException;
    @interface QueryPageAttrs {}
    
    /**
     * 单个属性查询
     * 
     * @param attrPam
     * @return 通过ID查询到的单个属性
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_VAL_0201
    *  @RestRelativeURL attrAndValDef/attr
     */
	@POST
	@Path("/attr")
    public AttrInfo queryAttr(AttrPam attrPam)
            throws BusinessException, SystemException;
    @interface QueryAttr {}
    
    /**
     * 属性批量添加
     * 
     * @param attrParamList
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_VAL_0202
    *  @RestRelativeURL attrAndValDef/addAttrs
     */
	@POST
	@Path("/addAttrs")
    public BaseResponse createAttrs(List<AttrParam> attrParamList) 
            throws BusinessException, SystemException;
    @interface CreateAttrs{}
    
    /**
     * 属性修改
     * 
     * @param attrParam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_VAL_0203
    *  @RestRelativeURL attrAndValDef/saveAttr
     */
	@POST
	@Path("/saveAttr")
    public BaseResponse updateAttr(AttrParam attrParam) 
            throws BusinessException, SystemException;
    @interface UpdateAttr{}
    
    /**
     * 单个属性删除
     * 
     * @param attrPam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_VAL_0204
    *  @RestRelativeURL attrAndValDef/removeAttr
     */
	@POST
	@Path("/removeAttr")
    public BaseResponse deleteAttr(AttrPam attrPam)
            throws BusinessException, SystemException;
    @interface DeleteAttr{}
    
    /**
     * 属性的属性值分页查询
     * 
     * @param pageQuery
     * @return 符合条件的属性集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_VAL_0205
    *  @RestRelativeURL attrAndValDef/pageAttrval
     */
	@POST
	@Path("/pageAttrval")
    public PageInfoResponse<AttrValInfo> queryPageAttrvalue(AttrValPageQuery pageQuery)
            throws BusinessException, SystemException;
    @interface QueryPageAttrvalue {}
    
    /**
     * 属性值添加
     * 
     * @param attrValParamList
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_VAL_0206
    *  @RestRelativeURL attrAndValDef/addAttrval
     */
	@POST
	@Path("/addAttrval")
    public BaseResponse createAttrvalue(List<AttrValParam> attrValParamList)
            throws BusinessException, SystemException;
    @interface CreateAttrvalue {}
    
    /**
     * 属性值修改
     * 
     * @param attrValParam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_VAL_0207
    *  @RestRelativeURL attrAndValDef/saveAttrval
     */
	@POST
	@Path("/saveAttrval")
    public BaseResponse updateAttrvalue(AttrValParam attrValParam)
            throws BusinessException, SystemException;
    @interface UpdateAttrvalue {}
    
    /**
     * 属性值删除
     * 
     * @param attrValUniqueReq
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_VAL_0208
    *  @RestRelativeURL attrAndValDef/removeAttrval
     */
	@POST
	@Path("/removeAttrval")
    public BaseResponse deleteAttrvalue(AttrValUniqueReq attrValUniqueReq)
            throws BusinessException, SystemException;
    @interface DeleteAttrvalue {}

    /**
     * 单个属性值查询
     *
     * @param attrValUniqueReq
     * @return 符合条件的单个属性值信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     * @ApiCode ATTR_VAL_0209
     * @RestRelativeURL attrAndValDef/attrval
     */
	@POST
	@Path("/attrval")
    public AttrVal queryAttrvalue(AttrValUniqueReq attrValUniqueReq)
            throws BusinessException, SystemException;
    @interface QueryAttrvalue {}

    /**
     * 查询所有的属性和属性值
     * 
     * @return 由属性对象对应的属性值List的Map
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_VAL_0210
    *  @RestRelativeURL attrAndValDef/attrsAndValues
     */
	@POST
	@Path("/attrsAndValues")
    public BaseListResponse<AttrDef> queryAllAttrAndVal(BaseInfo baseInfo)
            throws BusinessException, SystemException;
    @interface QueryAllAttrAndVal {}

}
