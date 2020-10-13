package com.yss.datamiddle.vo.datamart;

import com.yss.datamiddle.vo.BaseEntity;

/**
 * 数据集市分类
 * 
 * @author LT
 * @date 2019年11月28日
 */
public class DatamartCatalog extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	private String desc;
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
}
