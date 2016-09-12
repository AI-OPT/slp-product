package com.ai.slp.product.api.comment;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.paas.ipaas.util.JSonUtil;
import com.ai.slp.product.api.productcomment.interfaces.IProdCommentManagerSV;
import com.ai.slp.product.api.productcomment.param.PictureVO;
import com.ai.slp.product.api.productcomment.param.ProdCommentCreateRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageResponse;
import com.ai.slp.product.api.productcomment.param.ProdCommentVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProdCommentManagerSVTest {
	@Autowired IProdCommentManagerSV prodCommentManagerSV;
	
	@Test
	public void createProdCommentTest(){
		ProdCommentCreateRequest prodCommentCreateRequest = new ProdCommentCreateRequest();
		prodCommentCreateRequest.setOrderId("0001");
		prodCommentCreateRequest.setUserId("003");
		prodCommentCreateRequest.setTenantId("changhong");
		List<ProdCommentVO> commentList=new LinkedList<ProdCommentVO>();
		ProdCommentVO prodComment = new ProdCommentVO();
		prodComment.setCommentBody("测试商品评价：商品太次了!!");
		prodComment.setShopScoreFw(3L);
		prodComment.setShopScoreMs(2L);
		prodComment.setShopScoreWl(3L);
		prodComment.setSkuId("002");
		prodComment.setSubOrderId("00001");
		List<PictureVO> pictureList=new LinkedList<PictureVO>();
		PictureVO pictureVO=new PictureVO();
		pictureVO.setPicDir("test.image.01");
		pictureVO.setPicName("测试图片添加");
		pictureVO.setSerialNumber(1L);
		pictureVO.setVfsId("001");
		pictureList.add(pictureVO);
		prodComment.setPictureList(pictureList);
		commentList.add(prodComment );
		prodCommentCreateRequest.setCommentList(commentList);
		BaseResponse createProdComment = prodCommentManagerSV.createProdComment(prodCommentCreateRequest );
		System.out.println(JSonUtil.toJSon(createProdComment));
	}
	
	@Test
	public void queryPageInfoBySkuTest(){
		ProdCommentPageRequest prodCommentPageRequest = new ProdCommentPageRequest();
		prodCommentPageRequest.setPageNo(1);
		prodCommentPageRequest.setPageSize(5);
		prodCommentPageRequest.setSkuId("0000000000000153");
		prodCommentPageRequest.setTenantId("changhong");
		prodCommentPageRequest.setShopScoreMs(1L);
		PageInfoResponse<ProdCommentPageResponse> queryPageInfoBySku = prodCommentManagerSV.queryPageInfoBySku(prodCommentPageRequest );
		System.out.println(JSonUtil.toJSon(queryPageInfoBySku));
	}

}
