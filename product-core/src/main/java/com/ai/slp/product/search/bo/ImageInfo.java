package com.ai.slp.product.search.bo;

import com.google.gson.annotations.Expose;

/**
 * Created by xin on 16-5-17.
 */
public class ImageInfo {
	/**
	 * 图片类型
	 */
	@Expose
	private String imagetype;
	/**
	 * 文件附件模块ID
	 */
	@Expose
	private String vfsid;
	/**
	 * 文件附件模块ID
	 */
	@Expose
	private String ismainpic;

	public ImageInfo() {
		super();
	}

	public String getIsmainpic() {
		return ismainpic;
	}

	public void setIsmainpic(String ismainpic) {
		this.ismainpic = ismainpic;
	}

	public ImageInfo(String imagetype, String vfsid) {
		this.imagetype = imagetype;
		this.vfsid = vfsid;
	}

	public String getImagetype() {
		return imagetype;
	}

	public void setImagetype(String imagetype) {
		this.imagetype = imagetype;
	}

	public String getVfsid() {
		return vfsid;
	}

	public void setVfsid(String vfsid) {
		this.vfsid = vfsid;
	}

}
