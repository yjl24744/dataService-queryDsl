package com.yss.datamiddle.vo;

import com.yss.datamiddle.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Entity基类
 * 
 * @author ruoyi
 */
@ApiModel("Entity基类")
public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6462855047649154772L;

	public static final String CHECK_STATE_NO = "0";
	public static final String CHECK_STATE_YES = "1";

	public static final String DELETE_STATE_NO = "0";
	public static final String DELETE_STATE_YES = "1";

	/** 记录ID */
	@ApiModelProperty(value = "记录ID" , required = false)
	protected String id;
	/** 创建者ID */

	@ApiModelProperty(value = "创建者ID" , required = false)
	protected String creatorId;
	/** 创建人名称 */

	@ApiModelProperty(value = "创建人名称" , required = false)
	protected String creatorName;
	/** 创建时间 */

	@ApiModelProperty(value = "创建时间" , required = false)
	protected String createTime;
	/** 最后编辑者ID */

	@ApiModelProperty(value = "最后编辑者ID" , required = false)
	protected String lastEditorId;
	/** 最后编辑者名称 */

	@ApiModelProperty(value = "最后编辑者名称" , required = false)
	protected String lastEditorName;
	/** 最后编辑时间 */

	@ApiModelProperty(value = "最后编辑时间" , required = false)
	protected String lastEditTime;
	/** 删除时间 */

	@ApiModelProperty(value = "删除时间" , required = false)
	protected String deleteTime;
	/** 删除状态,0:未删除状态, 1:删除状态 */

	@ApiModelProperty(value = "删除状态" , example = "0", notes = "0:未删除状态, 1:删除状态", required = false)
	protected String deleteState;
	/** 审核状态 ,0:未审核状态, 1:审核状态 */

	@ApiModelProperty(value = "视图别名" , example = "0", notes = "0:未审核状态, 1:审核状态", required = false)
	protected String checkState;
	/** 审核者ID */

	@ApiModelProperty(value = "审核者ID" , required = false)
	protected String checkerId;
	/** 审核人名称 */

	@ApiModelProperty(value = "审核人名称" , required = false)
	protected String checkerName;
	/** 审核时间 */

	@ApiModelProperty(value = "审核时间" , required = false)
	protected String checkTime;

	@ApiModelProperty(value = "当前页数" , required = false)
	protected Integer pageNum;

	@ApiModelProperty(value = "页大小" , required = false)
	protected Integer pageSize;

	@ApiModelProperty(value = "排序字段" , required = false)
	protected String orderByColumn;

	@ApiModelProperty(value = "是否升序排列" , required = false)
	protected String isAsc;

	/**
	 * 存放操作权限列表
	 */
	@ApiModelProperty(value = "存放操作权限列表" , required = false)
	private Set<String> permissions;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastEditorId() {
		return lastEditorId;
	}

	public void setLastEditorId(String lastEditorId) {
		this.lastEditorId = lastEditorId;
	}

	public String getLastEditorName() {
		return lastEditorName;
	}

	public void setLastEditorName(String lastEditorName) {
		this.lastEditorName = lastEditorName;
	}

	public String getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(String lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public String getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime;
	}

	public String getDeleteState() {
		return deleteState;
	}

	public void setDeleteState(String deleteState) {
		this.deleteState = deleteState;
	}

	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

	public String getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(String checkerId) {
		this.checkerId = checkerId;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	public void addPermissions(List<String> permissionList) {
		if (this.permissions == null) {
			permissions = new HashSet<String>();
		}
		permissions.addAll(permissionList);
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderByColumn() {
		return orderByColumn;
	}

	public void setOrderByColumn(String orderByColumn) {
		this.orderByColumn = orderByColumn;
	}

	public String getIsAsc() {
		return isAsc;
	}

	public void setIsAsc(String isAsc) {
		this.isAsc = isAsc;
	}
	
	public boolean isChecked() {
		return CHECK_STATE_YES.equals(checkState);
	}

	public String getOrderBy() {
		if (StringUtils.isEmpty(orderByColumn)) {
			return "";
		}
		return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
	}
}
