package com.yss.datamiddle.vo.tableview;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 数据预览参数
 * 
 * @author LT 2019年11月20日
 */
public class WideViewVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String dsId;
	private String code;
	private String name;
	private String sql;
	private List<String> tables;
	private Map<String, Object> parameters;
	/** SQL内容解析器是否支持 */
	private boolean sqlParser = true;

	public boolean isSqlParser() {
		return sqlParser;
	}

	public void setSqlParser(boolean sqlParser) {
		this.sqlParser = sqlParser;
	}

	public String getDsId() {
		return dsId;
	}

	public void setDsId(String dsId) {
		this.dsId = dsId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<String> getTables() {
		return tables;
	}

	public void setTables(List<String> tables) {
		this.tables = tables;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

}
