package com.yss.datamiddle.vo.tableview;

import com.yss.datamiddle.vo.BaseEntity;

import java.util.List;

/**
 * 数据视图VO
 * @author wangyh
 * @date 2019-05-21
 */
public class BiTableView extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**视图别名 */
	private String viewAlias;
	/** 视图名 */
	private String name;
	/**  描述 */
	private String description;
	/** 数据源ID */
	private String dsId;
	/**  SQL */
	private String sql;
	/** 返显信息 */
	private String content;
	/** 內置維度标志 */
	private String inlineDim;
	/** 关联字段 */
	private String relationField;

	public String getViewAlias() {
		return viewAlias;
	}

	public void setViewAlias(String viewAlias) {
		this.viewAlias = viewAlias;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDsId() {
		return dsId;
	}

	public void setDsId(String dsId) {
		this.dsId = dsId;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getInlineDim() {
		return inlineDim;
	}

	public void setInlineDim(String inlineDim) {
		this.inlineDim = inlineDim;
	}

	public String getRelationField() {
		return relationField;
	}

	public void setRelationField(String relationField) {
		this.relationField = relationField;
	}

}
