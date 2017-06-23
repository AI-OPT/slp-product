package com.ai.slp.product.api.comment;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.paas.ipaas.util.JSonUtil;
import com.ai.slp.product.api.productcomment.interfaces.IProdCommentManagerSV;
import com.ai.slp.product.api.productcomment.param.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProdCommentManagerSVTest {
	@Autowired IProdCommentManagerSV prodCommentManagerSV;
	
	@Test
	public void createProdCommentTest(){
		ProdCommentCreateRequest prodCommentCreateRequest = new ProdCommentCreateRequest();
		prodCommentCreateRequest.setOrderId("0001");
		prodCommentCreateRequest.setUserId("3da3109cdb3f4d9e");
		prodCommentCreateRequest.setTenantId("changhong");
		List<ProdCommentVO> commentList=new LinkedList<ProdCommentVO>();
		ProdCommentVO prodComment = new ProdCommentVO();
		prodComment.setCommentBody("测试商品评价：商品太次了!!2017-9-19 14:46:33");
		prodComment.setShopScoreFw(3L);
		prodComment.setShopScoreMs(2L);
		prodComment.setShopScoreWl(3L);
		//prodComment.setSkuId("0000000000002083");
		prodComment.setSkuId("0000000000002153");
		prodComment.setSubOrderId("00001");
		List<PictureVO> pictureList=new LinkedList<PictureVO>();
		PictureVO pictureVO=new PictureVO();
//		pictureVO.setPicAddr("test.image.01");
		pictureVO.setPicAddr("/product-web/resources/template/default/images/2.jpg");
		pictureVO.setPicName("测试图片添加");
		pictureVO.setSerialNumber(1L);
		pictureVO.setVfsId("57514007d601800009c0b0f4");
		pictureList.add(pictureVO);
		prodComment.setPictureList(pictureList);
		commentList.add(prodComment );
		prodCommentCreateRequest.setCommentList(commentList);
		BaseResponse createProdComment = prodCommentManagerSV.createProdComment(prodCommentCreateRequest );
		System.out.println(JSonUtil.toJSon(createProdComment));
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("OK");
		
	}
	
	@Test
	public void queryPageInfoBySkuTest(){
		ProdCommentPageRequest prodCommentPageRequest = new ProdCommentPageRequest();
		prodCommentPageRequest.setPageNo(1);
		prodCommentPageRequest.setPageSize(5);
		prodCommentPageRequest.setSkuId("0000000000000153");
		prodCommentPageRequest.setTenantId("changhong");
		prodCommentPageRequest.setShopScoreMs((long) 1);
		PageInfoResponse<ProdCommentPageResponse> queryPageInfoBySku = prodCommentManagerSV.queryPageInfoBySku(prodCommentPageRequest );
		System.out.println(JSonUtil.toJSon(queryPageInfoBySku));
	}
	
	@Test
	public void queryPageInfoTest(){
		CommentPageRequest commentPageRequest = new CommentPageRequest();
		commentPageRequest.setPageNo(1);
		commentPageRequest.setPageSize(10);
		commentPageRequest.setTenantId("changhong");
		//commentPageRequest.setShopScoreMs(1L);
		//commentPageRequest.setCommentTimeBegin(Timestamp.valueOf("2017-04-18 10:41:11"));
		PageInfoResponse<CommentPageResponse> queryPageInfoBySku = prodCommentManagerSV.queryPageInfo(commentPageRequest);
		System.out.println(JSonUtil.toJSon(queryPageInfoBySku));
	}
	
	@Test
	public void updateCommentStateTest() throws BusinessException, SystemException, IOException, Exception{
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
		queryRequset.setCommentId("64");
		CommentPictureQueryResponse queryPictureByCommentId = prodCommentManagerSV.queryPictureByCommentId(queryRequset );
		System.out.println(JSonUtil.toJSon(queryPictureByCommentId));
	}
	
	@Test
	public void replyCommentTest(){
		ProdReplyComment replyComment = new ProdReplyComment();
		replyComment.setTenantId("changhong");
		replyComment.setSupplierId("-1");
		replyComment.setCommentId("371");
		replyComment.setReplierId("01");
		replyComment.setReplyComment("哇咔咔...");
		BaseResponse baseResponse = prodCommentManagerSV.replyComment(replyComment);
		System.out.println(JSonUtil.toJSon(baseResponse));
	}


}
