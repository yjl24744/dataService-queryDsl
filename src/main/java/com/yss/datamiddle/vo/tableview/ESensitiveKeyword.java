package com.yss.datamiddle.vo.tableview;

/**
 * SQL查询敏感关键词过滤
 * @author wangyh
 * @date 2019-05-21
 */
public enum ESensitiveKeyword {

	insert("INSERT "),
	update("UPDATE "),
	drop("DROP "),
	delete("DELETE "),
	truncate("TRUNCATE ");

	private String keyword;

	ESensitiveKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

	void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * 是否包含关键词
	 * @param sql sql语句
	 * @return 布尔值
	 */
	public static boolean isContainSensitiveKeyword(String sql) {
		String upperSql=sql.toUpperCase();
		for (ESensitiveKeyword eSensitiveKeyword : ESensitiveKeyword.values()) {
			if(upperSql.contains(eSensitiveKeyword.getKeyword())){
				return true;
			}
		}
		return false;
	}

}
