package com.yss.datamiddle.vo.tableview;

/**
 * 数据视图宽表字段信息
 * 
 * @author LT 2019年11月20日
 */
public class WideViewFieldVO {

	/**
	 * 数据键值
	 */
	private String dataKey;

	private String fieldName;
	/** 字段别名 */
	private String fieldAlias;
	/** 字段数据类型 */
	private String fieldType;

	private String tableName;

	private String tableAlias;

	public String getDataKey() {
		return dataKey;
	}

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldAlias() {
		return fieldAlias;
	}

	public void setFieldAlias(String fieldAlias) {
		this.fieldAlias = fieldAlias;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableAlias() {
		return tableAlias;
	}

	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}

}
