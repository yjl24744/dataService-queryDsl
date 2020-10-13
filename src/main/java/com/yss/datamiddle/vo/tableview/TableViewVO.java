package com.yss.datamiddle.vo.tableview;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 数据视图信息
 * 
 * @author LT
 * @date 2020年4月14日
 */
@ApiModel("数据视图信息")
public class TableViewVO extends TableView {

	private static final long serialVersionUID = 1L;
	/** 数据集市分类编码 */
	@ApiModelProperty(value = "数据集市分类编码" , required = false)
	private String datamartId;
	/** 数据集市分类所属路径 */
	@ApiModelProperty(value = "数据集市分类所属路径" , required = false)
	private String path;
	/** 服务url地址 */
	@ApiModelProperty(value = "服务url地址" , required = false)
	private String url;
	/** 是否一键创建了主题 */
	@ApiModelProperty(value = "是否一键创建了主题" , required = false)
	private boolean theme;
	/** 是否发布为标准服务 */
	@ApiModelProperty(value = "是否发布为标准服务" , required = false)
	private boolean service;

	public boolean isService() {
		return service;
	}

	public void setService(boolean service) {
		this.service = service;
	}

	public boolean isTheme() {
		return theme;
	}

	public void setTheme(boolean theme) {
		this.theme = theme;
	}

	public String getDatamartId() {
		return datamartId;
	}

	public void setDatamartId(String datamartId) {
		this.datamartId = datamartId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
