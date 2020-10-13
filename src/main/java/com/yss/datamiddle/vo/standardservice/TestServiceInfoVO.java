package com.yss.datamiddle.vo.standardservice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 标准服务测试
 * 
 * @author LT
 * @date 2020年5月26日
 */
@ApiModel("标准服务测试")
public class TestServiceInfoVO implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 服务信息 */
	@ApiModelProperty(value = "服务信息" , required = false)
	private ServiceInfoDO serviceInfo;
	/** 请求头信息 */
	@ApiModelProperty(value = "请求头信息" , required = false)
	private List<ServiceHeaderVO> headers;
	/** 参数信息 */
	@ApiModelProperty(value = "参数信息" , required = false)
	private List<ServiceParameterVO> parameters;

	public ServiceInfoDO getServiceInfo() {
		return serviceInfo;
	}

	public void setServiceInfo(ServiceInfoDO serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

	public List<ServiceHeaderVO> getHeaders() {
		return headers;
	}

	public void setHeaders(List<ServiceHeaderVO> headers) {
		this.headers = headers;
	}

	public List<ServiceParameterVO> getParameters() {
		return parameters;
	}

	public void setParameters(List<ServiceParameterVO> parameters) {
		this.parameters = parameters;
	}

	/**
	 * 参数信息
	 * 
	 * @author LT
	 * @date 2020年5月27日
	 */
	@ApiModel("请求头信息")
	public static class ServiceHeaderVO implements Serializable {

		private static final long serialVersionUID = 1L;
		/** 参数名 */
		@ApiModelProperty(value = "参数名" , required = false)
		private String name;
		/** 值 */
		@ApiModelProperty(value = "值" , required = false)
		private String value;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

	/**
	 * 参数信息
	 * 
	 * @author LT
	 * @date 2020年5月26日
	 */
	@ApiModel("参数信息")
	public static class ServiceParameterVO implements Serializable {

		private static final long serialVersionUID = 1L;
		/** 参数名 */
		@ApiModelProperty(value = "参数名" , required = false)
		private String name;
		/** 参数数据类型 */
		@ApiModelProperty(value = "参数数据类型" , required = false)
		private String type;
		/** 参数数据类型 */
		@ApiModelProperty(value = "操作关系符" , required = false)
		private String operator;
		/** 默认值 */
		@ApiModelProperty(value = "默认值" , required = false)
		private String value;

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

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getOperator() { return operator; }

		public void setOperator(String operator) { this.operator = operator; }
	}
}
