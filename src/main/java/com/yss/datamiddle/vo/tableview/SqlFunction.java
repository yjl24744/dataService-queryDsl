package com.yss.datamiddle.vo.tableview;

/**
 * Sql函数
 * @author wangyh
 * @date 2019-05-21
 */
public class SqlFunction {

	/**函数名称*/
	private String name;
	/** 语法 */
	private String usage;
	/** 描述 */
	private String desc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
