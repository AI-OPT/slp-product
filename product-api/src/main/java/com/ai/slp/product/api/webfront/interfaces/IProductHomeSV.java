package com.ai.slp.product.api.webfront.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.webfront.param.FastProductInfoRes;
import com.ai.slp.product.api.webfront.param.FastProductReq;
import com.ai.slp.product.api.webfront.param.ProductHomeRequest;
import com.ai.slp.product.api.webfront.param.ProductHomeResponse;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/producthome")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IProductHomeSV {
    /**
     * 获取首页流量、话费数据
     * @param productHomeRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiCode PROD_HOME_0100
     * @RestRelativeURL producthome/homesearch
     */
	@POST
	@Path("/homesearch")
    public List<ProductHomeResponse> queryHomeDataProduct(ProductHomeRequest request)throws BusinessException, SystemException;
    /**
     * 获取推荐产品
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiCode PROD_HOME_0101
     * @RestRelativeURL producthome/homehot
     */
	@POST
	@Path("/homehot")
    public List<ProductHomeResponse> queryHotProduct(ProductHomeRequest request)throws BusinessException, SystemException;


    /**
     * 获取快充产品
     * @param request
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode PROD_HOME_0102
     * @RestRelativeURL producthome/fastproduct
     */
	@POST
	@Path("/fastproduct")
    public FastProductInfoRes queryFastProduct(FastProductReq request)throws BusinessException, SystemException;
    @interface QueryFastProduct{}

}
