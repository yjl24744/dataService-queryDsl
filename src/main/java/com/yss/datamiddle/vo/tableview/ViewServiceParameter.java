package com.yss.datamiddle.vo.tableview;

import java.io.Serializable;
import java.util.List;

/**
 * 视图发布服务参数
 * 
 * @author LT
 * @date 2020年5月29日
 */
public class ViewServiceParameter implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 数据id */
	private List<String> ids;
	/** 服务code */
	private String code;
	/** 服务版本号 */
	private String version;
	/** 服务中文名 */
	private String name;

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
