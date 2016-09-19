package com.ai.slp.product.api.productcomment.param;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

public class CommentPictureQueryResponse extends BaseResponse{

	private static final long serialVersionUID = 1L;

	private List<PictureVO> pictureList;

	public List<PictureVO> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<PictureVO> pictureList) {
		this.pictureList = pictureList;
	}
}
