package com.yss.datamiddle.vo.standardservice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 服务详细信息
 * 
 * @author LT
 * @date 2020年5月21日
 */
@ApiModel("服务详细信息")
public class ServiceDetailVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "标准服务信息" , required = false)
	private ServiceInfoVO serviceInfo;
	@ApiModelProperty(value = "服务字段信息" , required = false)
	private List<ServiceFieldDO> fields;
	@ApiModelProperty(value = "服务参数信息" , required = false)
	private List<ServiceParameterDO> parameters;

	public ServiceInfoVO getServiceInfo() {
		return serviceInfo;
	}

	public void setServiceInfo(ServiceInfoVO serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

	public List<ServiceFieldDO> getFields() {
		return fields;
	}

	public void setFields(List<ServiceFieldDO> fields) {
		this.fields = fields;
	}

	public List<ServiceParameterDO> getParameters() {
		return parameters;
	}

	public void setParameters(List<ServiceParameterDO> parameters) {
		this.parameters = parameters;
	}
}
