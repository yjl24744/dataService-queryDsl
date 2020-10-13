package com.yss.datamiddle.vo.standardservice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 标准服务信息VO
 * 
 * @author LT
 * @date 2020年5月28日
 */
@ApiModel("标准服务信息")
public class ServiceInfoVO extends ServiceInfoDO {

	private static final long serialVersionUID = 1L;

	/** 集市id */
	@ApiModelProperty(value = "集市id" , required = false)
	private String datamartId;
	/** 服务来源名 */
	@ApiModelProperty(value = "服务来源名" , required = false)
	private String sourceName;
	/** 服务地址 */
	@ApiModelProperty(value = "服务地址" , required = false)
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDatamartId() {
		return datamartId;
	}

	public void setDatamartId(String datamartId) {
		this.datamartId = datamartId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
}
