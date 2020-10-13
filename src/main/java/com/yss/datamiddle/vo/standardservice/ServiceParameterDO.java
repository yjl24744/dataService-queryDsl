package com.yss.datamiddle.vo.standardservice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 服务参数信息
 * 
 * @author LT
 * @date 2020年5月20日
 */
@ApiModel("服务详细信息")
public class ServiceParameterDO implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 参数id */
	@ApiModelProperty(value = "参数id" , required = false)
	private String id;
	/** 服务id */
	@ApiModelProperty(value = "服务id" , required = false)
	private String serviceId;
	/** 参数名 */
	@ApiModelProperty(value = "参数名" , required = false)
	private String name;
	/** 参数数据类型 */
	@ApiModelProperty(value = "参数数据类型" , required = false)
	private String type;
	/** 操作关系符 */
	@ApiModelProperty(value = "操作关系符" , required = false)
	private String operator;
	/** 默认值 */
	@ApiModelProperty(value = "默认值" , required = false)
	private String defaultValue;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getOperator() { return operator; }

	public void setOperator(String operator) { this.operator = operator; }
}
