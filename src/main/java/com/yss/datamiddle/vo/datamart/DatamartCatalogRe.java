package com.yss.datamiddle.vo.datamart;

import com.yss.datamiddle.vo.BaseEntity;

/**
 * 数据集市关联数据
 * 
 * @author LT
 * @date 2019年12月20日
 */
public class DatamartCatalogRe extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String datamartId;
	private String dataType;
	private String relId;

	public String getDatamartId() {
		return datamartId;
	}

	public void setDatamartId(String datamartId) {
		this.datamartId = datamartId;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getRelId() {
		return relId;
	}

	public void setRelId(String relId) {
		this.relId = relId;
	}

}
