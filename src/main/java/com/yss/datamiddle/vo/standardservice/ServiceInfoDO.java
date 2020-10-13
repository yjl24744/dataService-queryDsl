package com.yss.datamiddle.vo.standardservice;

import com.yss.datamiddle.vo.BaseEntity;

/**
 * 标准服务信息
 * 
 * @author LT
 * @date 2020年5月20日
 */
public class ServiceInfoDO extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 服务id */
	private String id;
	/** 服务编码 */
	private String code;
	/** 服务版本 */
	private String version;
	/** 服务名称 */
	private String name;
	/** 服务描述 */
	private String desc;
	/** 服务类型， 0:数据主题服务, 1：数据视图服务 */
	private String type;
	/** 关联数据主键ID */
	private String relId;
	// /** 服务URL地址 */
	// private String url;
	/** 创建人 */
	private String userName;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRelId() {
		return relId;
	}

	public void setRelId(String relId) {
		this.relId = relId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
