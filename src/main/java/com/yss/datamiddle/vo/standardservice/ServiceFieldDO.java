package com.yss.datamiddle.vo.standardservice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 服务字段信息
 * 
 * @author LT
 * @date 2020年5月20日
 */
@ApiModel("服务字段信息")
public class ServiceFieldDO implements Serializable {

	private static final long serialVersionUID = 5118494867249091423L;
	/** 字段id */
	@ApiModelProperty(value = "字段id" , required = false)
	private String id;
	/** 服务id */
	@ApiModelProperty(value = "服务id" , required = false)
	private String serviceId;
	/** 服务列名 */
	@ApiModelProperty(value = "服务列名" , required = false)
	private String name;
	/** 列名 */
	@ApiModelProperty(value = "列名" , required = false)
	private String innerName;
	/** 数据类型 */
	@ApiModelProperty(value = "数据类型" , required = false)
	private String type;
	/** 中文描述 */
	@ApiModelProperty(value = "中文描述" , required = false)
	private String alias;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInnerName() {
		return innerName;
	}

	public void setInnerName(String innerName) {
		this.innerName = innerName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
}
