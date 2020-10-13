package com.yss.datamiddle.vo.tableview;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 数据视图反显信息解析处理
 * 
 * @author LT
 * @date 2020年4月17日
 */
public class ViewContent implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<ViewSelectedField> fieldCollections;
	private List<ViewDrawData> drawData;

	public List<ViewSelectedField> getFieldCollections() {
		return fieldCollections;
	}

	public void setFieldCollections(List<ViewSelectedField> fieldCollections) {
		this.fieldCollections = fieldCollections;
	}

	public List<ViewDrawData> getDrawData() {
		return drawData;
	}

	public void setDrawData(List<ViewDrawData> drawData) {
		this.drawData = drawData;
	}

	public static class ViewSelectedField {
		private String tableName;
		private String fieldName;
		private String fieldAlias;
		private String fieldType;
		private String path;
		private String key;

		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
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

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}
	}

	public static class ViewDrawData {
		private ViewTableInfo tableInfo;
		private Integer x;
		private Integer y;
		private String px;
		private String py;
		private Map<String, Object> itemStyle;

		public ViewTableInfo getTableInfo() {
			return tableInfo;
		}

		public void setTableInfo(ViewTableInfo tableInfo) {
			this.tableInfo = tableInfo;
		}

		public Integer getX() {
			return x;
		}

		public void setX(Integer x) {
			this.x = x;
		}

		public Integer getY() {
			return y;
		}

		public void setY(Integer y) {
			this.y = y;
		}

		public String getPx() {
			return px;
		}

		public void setPx(String px) {
			this.px = px;
		}

		public String getPy() {
			return py;
		}

		public void setPy(String py) {
			this.py = py;
		}

		public Map<String, Object> getItemStyle() {
			return itemStyle;
		}

		public void setItemStyle(Map<String, Object> itemStyle) {
			this.itemStyle = itemStyle;
		}
	}

	public static class ViewTableInfo {
		private String tableName;
		private String tableAlias;
		private Set<String> selected;
		private List<ViewField> fields;
		private String parentTable;
		private List<ViewJoin> join;

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

		public List<ViewField> getFields() {
			return fields;
		}

		public Set<String> getSelected() {
			return selected;
		}

		public void setSelected(Set<String> selected) {
			this.selected = selected;
		}

		public void setFields(List<ViewField> fields) {
			this.fields = fields;
		}

		public String getParentTable() {
			return parentTable;
		}

		public void setParentTable(String parentTable) {
			this.parentTable = parentTable;
		}

		public List<ViewJoin> getJoin() {
			return join;
		}

		public void setJoin(List<ViewJoin> join) {
			this.join = join;
		}
	}

	public static class ViewField {
		private String fieldName;
		private String fieldAlias;
		private String fieldType;
		private Integer length;
		private Integer scale;
		private String flag;
		private String showFlag;
		private Boolean checked;

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

		public Integer getLength() {
			return length;
		}

		public void setLength(Integer length) {
			this.length = length;
		}

		public Integer getScale() {
			return scale;
		}

		public void setScale(Integer scale) {
			this.scale = scale;
		}

		public String getFlag() {
			return flag;
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}

		public String getShowFlag() {
			return showFlag;
		}

		public void setShowFlag(String showFlag) {
			this.showFlag = showFlag;
		}

		public Boolean getChecked() {
			return checked;
		}

		public void setChecked(Boolean checked) {
			this.checked = checked;
		}
	}

	public static class ViewJoin {
		private String relation;
		private String sourceTable;
		private String targetTable;
		private List<ViewFieldjoin> fieldjoins;

		public String getRelation() {
			return relation;
		}

		public void setRelation(String relation) {
			this.relation = relation;
		}

		public String getSourceTable() {
			return sourceTable;
		}

		public void setSourceTable(String sourceTable) {
			this.sourceTable = sourceTable;
		}

		public String getTargetTable() {
			return targetTable;
		}

		public void setTargetTable(String targetTable) {
			this.targetTable = targetTable;
		}

		public List<ViewFieldjoin> getFieldjoins() {
			return fieldjoins;
		}

		public void setFieldjoins(List<ViewFieldjoin> fieldjoins) {
			this.fieldjoins = fieldjoins;
		}
	}

	public static class ViewFieldjoin {
		private String sourceField;
		private String targetField;

		public String getSourceField() {
			return sourceField;
		}

		public void setSourceField(String sourceField) {
			this.sourceField = sourceField;
		}

		public String getTargetField() {
			return targetField;
		}

		public void setTargetField(String targetField) {
			this.targetField = targetField;
		}
	}
}