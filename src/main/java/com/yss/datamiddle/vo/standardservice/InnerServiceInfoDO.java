package com.yss.datamiddle.vo.standardservice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 内部服务信息
 * 
 * @author LT
 * @date 2020年5月21日
 */
@ApiModel("服务详细信息")
public class InnerServiceInfoDO implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 服务id */
	@ApiModelProperty(value = "服务id" , required = false)
	private String id;
	/** 服务编码 */
	@ApiModelProperty(value = "服务编码" , required = false)
	private String code;
	/** 服务版本 */
	@ApiModelProperty(value = "服务版本" , required = false)
	private String version;
	/** 服务中文名 */
	@ApiModelProperty(value = "服务中文名" , required = false)
	private String alias;
	/** 服务类型， 0:数据主题服务, 1：数据视图服务 */
	@ApiModelProperty(value = "服务类型" , example = "0", notes = "0:数据主题服务, 1：数据视图服务", required = false)
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
