package com.yss.datamiddle.vo.tableview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yss.datamiddle.vo.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 数据视图表 t_bi_tableview
 * 
 * @author yss
 * @date 2018-12-24
 */
@ApiModel("数据视图表信息")
public class TableView extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 视图别名 */
	@ApiModelProperty(value = "视图别名" , required = false)
	private String viewAlias;
	/** 视图名 */
	@ApiModelProperty(value = "视图名" , required = false)
	private String name;
	/** 描述 */
	@ApiModelProperty(value = "描述" , required = false)
	private String description;
	/** 数据id */
	@ApiModelProperty(value = "数据id" , required = false)
	private String dsId;
	/** sql内容 */
	@ApiModelProperty(value = "sql内容" , required = false)
	private String sql;
	/** 返显信息 */
	@ApiModelProperty(value = "返显信息" , required = false)
	private String content;
	/** 內置維度标志 */
	@ApiModelProperty(value = "內置維度标志" , required = false)
	private String inlineDim;
	/** 内置维度关联字段 */
	@ApiModelProperty(value = "内置维度关联字段" , required = false)
	private String relationField;
	/** 是否在目标库创建视图对象 */
	@ApiModelProperty(value = "是否在目标库创建视图对象" , required = false)
	private String createView;
	// /** 发布外部数据服务 ，True:外部可查询数据/ False:外部不可查询数据 */
	// private String extdataServiceState;
	/** 数据源名称 */
	@ApiModelProperty(value = "数据源名称" , required = false)
	private String dsName;
	@ApiModelProperty(value = "相关表" , required = false)
	private String tableNames;
	/**
	 * 是否为目标库视图
	 * 
	 * @return
	 */
	@JsonIgnore
	public boolean isView() {
		return "1".equals(createView);
	}

	public String getCreateView() {
		return createView;
	}

	public void setCreateView(String createView) {
		this.createView = createView;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDsId(String dsId) {
		this.dsId = dsId;
	}

	public String getDsId() {
		return dsId;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", getId())
				.append("chName", getViewAlias()).append("name", getName())
				.append("desc", getDescription()).append("fdsId", getDsId())
				.append("fSQL", getSql()).append("creatorId", getCreatorId())
				.append("createTime", getCreateTime()).append("lastEditorId", getLastEditorId())
				.append("lastEditTime", getLastEditTime()).append("deleteTime", getDeleteTime())
				.append("deleteState", getDeleteState()).append("checkerId", getCheckerId())
				.append("checkTime", getCheckTime()).toString();
	}

	public String getInlineDim() {
		return inlineDim;
	}

	public void setInlineDim(String inlineDim) {
		this.inlineDim = inlineDim;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRelationField() {
		return relationField;
	}

	public void setRelationField(String relationField) {
		this.relationField = relationField;
	}

	public String getViewAlias() {
		return viewAlias;
	}

	public void setViewAlias(String viewAlias) {
		this.viewAlias = viewAlias;
	}

	public String getDsName() {
		return dsName;
	}

	public void setDsName(String dsName) {
		this.dsName = dsName;
	}

	public String getTableNames() {
		return tableNames;
	}

	public void setTableNames(String tableNames) {
		this.tableNames = tableNames;
	}
}
