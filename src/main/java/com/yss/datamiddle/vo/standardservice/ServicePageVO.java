package com.yss.datamiddle.vo.standardservice;

import com.yss.datamiddle.vo.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服务信息分页VO
 * 
 * @author LT
 * @date 2020年5月20日
 */
@ApiModel("服务信息分页VO")
public class ServicePageVO extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 查询关键字信息 */
	@ApiModelProperty(value = "查询关键字信息" , required = false)
	private String keyWord;
	/** 数据集市分类所属路径 */
	@ApiModelProperty(value = "数据集市分类所属路径" , required = false)
	private String path;

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
