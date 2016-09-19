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
import com.ai.slp.product.api.productcomment.param.CommentPageRequest;
import com.ai.slp.product.api.productcomment.param.CommentPageResponse;
import com.ai.slp.product.api.productcomment.param.CommentPictureQueryRequset;
import com.ai.slp.product.api.productcomment.param.CommentPictureQueryResponse;
import com.ai.slp.product.api.productcomment.param.PictureVO;
import com.ai.slp.product.api.productcomment.param.ProdCommentCreateRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageResponse;
import com.ai.slp.product.api.productcomment.param.ProdCommentVO;
import com.ai.slp.product.api.productcomment.param.UpdateCommentStateRequest;

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
		prodComment.setCommentBody("测试商品评价：商品太次了!!2016-9-19 14:46:33");
		prodComment.setShopScoreFw(3L);
		prodComment.setShopScoreMs(2L);
		prodComment.setShopScoreWl(3L);
		prodComment.setSkuId("0000000000000153");
		prodComment.setSubOrderId("00001");
		List<PictureVO> pictureList=new LinkedList<PictureVO>();
		PictureVO pictureVO=new PictureVO();
		pictureVO.setPicAddr("test.image.01");
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
	
	@Test
	public void queryPageInfoTest(){
		CommentPageRequest commentPageRequest = new CommentPageRequest();
		commentPageRequest.setPageNo(1);
		commentPageRequest.setPageSize(5);
		commentPageRequest.setTenantId("changhong");
		//commentPageRequest.setShopScoreMs(1L);
		PageInfoResponse<CommentPageResponse> queryPageInfoBySku = prodCommentManagerSV.queryPageInfo(commentPageRequest);
		System.out.println(JSonUtil.toJSon(queryPageInfoBySku));
	}
	
	@Test
	public void updateCommentStateTest(){
		UpdateCommentStateRequest updateCommentStateRequest = new UpdateCommentStateRequest();
		updateCommentStateRequest.setOperId("001");
		updateCommentStateRequest.setState("0");
		updateCommentStateRequest.setTenantId("changhong");
		List<String> commentIdList = new LinkedList<String>();
		commentIdList.add("10");
		commentIdList.add("11");
		commentIdList.add("12");
		updateCommentStateRequest.setCommentIdList(commentIdList );
		BaseResponse updateCommentState = prodCommentManagerSV.updateCommentState(updateCommentStateRequest);
		System.out.println(JSonUtil.toJSon(updateCommentState));
	}
	
	@Test
	public void queryPictureTest(){
		CommentPictureQueryRequset queryRequset = new CommentPictureQueryRequset();
		queryRequset.setCommentId("14");
		CommentPictureQueryResponse queryPictureByCommentId = prodCommentManagerSV.queryPictureByCommentId(queryRequset );
		System.out.println(JSonUtil.toJSon(queryPictureByCommentId));
	}

}
