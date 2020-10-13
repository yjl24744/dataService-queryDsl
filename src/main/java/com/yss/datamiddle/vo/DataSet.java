package com.yss.datamiddle.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 宽表数据结果集
 * 
 * @author LT 2019年8月6日
 */
public class DataSet implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<String> columns;
	private List<Map<String, Object>> rows;

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		if (columns != null && columns.size() > 0) {
			List<String> newColumns = new ArrayList<String>(columns.size());
			for (String col : columns) {
				newColumns.add(col.toUpperCase());
			}
			columns = newColumns;
		}
		this.columns = columns;
	}

	public List<Map<String, Object>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}

	public DataSet cloneColumns() {
		DataSet dataSet = new DataSet();
		dataSet.setColumns(new ArrayList<String>(columns));
		dataSet.setRows(new ArrayList<Map<String, Object>>());
		return dataSet;
	}

	public DataSet clone() {
		DataSet dataSet = new DataSet();
		dataSet.setColumns(new ArrayList<String>(columns));
		dataSet.setRows(new ArrayList<Map<String, Object>>(rows));
		return dataSet;
	}
}
