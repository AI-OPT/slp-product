package com.ai.slp.product.api.productcat.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.productcat.param.AttrDefInfo;
import com.ai.slp.product.api.productcat.param.AttrValParam;
import com.ai.slp.product.api.productcat.param.ProAttrGroup;
import com.ai.slp.product.api.productcat.param.ProAttrGroupParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 类目属性组接口
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
@Path("/catAttrGroup")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ICatAttrGroupSV {
    
    /**
     * 查询类目分组
     * 
     * @param proAttrGroupParam
     * @return 符合条件的集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_GROUP_0301
    *  @RestRelativeURL catAttrGroup/catGroup
     */
	@POST
	@Path("/catGroup")
    public PageInfoResponse<ProAttrGroup> queryProAttrGroup(ProAttrGroupParam proAttrGroupParam)
            throws BusinessException, SystemException;
    @interface QueryProAttrGroup{}
    
    /**
     * 添加类目分组
     * 
     * @param proAttrGroupParam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_GROUP_0302
    *  @RestRelativeURL catAttrGroup/addCatGroup
     */
	@POST
	@Path("/addCatGroup")
    public BaseResponse addProAttrGroup(ProAttrGroupParam proAttrGroupParam) 
            throws BusinessException, SystemException;
    @interface AddProAttrGroup {}
    
    /**
     * 修改类目分组
     * 
     * @param proAttrGroupParam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_GROUP_0303
    *  @RestRelativeURL catAttrGroup/saveCatGroup
     */
	@POST
	@Path("/saveCatGroup")
    public BaseResponse updateProAttrGroup(ProAttrGroupParam proAttrGroupParam) 
            throws BusinessException, SystemException;
    @interface UpdateProAttrGroup{}
    
    /**
     * 删除类目分组
     * 
     * @param proAttrGroupParam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_GROUP_0304
    *  @RestRelativeURL catAttrGroup/removeCatGroup
     */
	@POST
	@Path("/removeCatGroup")
    public BaseResponse deleteProAttrGroup(ProAttrGroupParam proAttrGroupParam) 
            throws BusinessException, SystemException;
    @interface DeleteProAttrGroup{}
    
    /**
     * 类目组内属性查询：仅在本类目组内查询，加类目属性组ID为必须条件
     * 
     * @param proAttrGroupParam
     * @return 符合条件的商品属性集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_GROUP_0305
    *  @RestRelativeURL catAttrGroup/groupAttr
     */
	@POST
	@Path("/groupAttr")
    public List<AttrDefInfo> queryGroupAttr(ProAttrGroupParam proAttrGroupParam)
            throws BusinessException, SystemException; 
    @interface QueryGroupAttr {}
    
    /**
     * 类目组内属性添加
     * 
     * @param listProAttrVal
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_GROUP_0306
    *  @RestRelativeURL catAttrGroup/addGroupAttr
     */
	@POST
	@Path("/addGroupAttr")
    public BaseResponse addGroupAttr(List<AttrValParam> listProAttrVal)
            throws BusinessException, SystemException; 
    @interface AddGroupAttr {}
    
    /**
     * 类目组内属性删除
     * 
     * @param listProAttrVal
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_GROUP_0307
    *  @RestRelativeURL catAttrGroup/removeGroupAttr
     */
	@POST
	@Path("/removeGroupAttr")
    public BaseResponse deleteGroupAttr(List<AttrValParam> listProAttrVal)
            throws BusinessException, SystemException; 
    @interface DeleteGroupAttr {}
    
    
}
