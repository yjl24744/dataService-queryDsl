package com.yss.datamiddle.vo.datamart;

/**
 * 数据集市分类信息
 * 
 * @author LT
 * @date 2019年12月4日
 */
public class DatamartCatalogVO extends DatamartCatalog {

	private static final long serialVersionUID = 1L;
	/** 新分类父路径 */
	private String newPath;

	public String getNewPath() {
		return newPath;
	}

	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}

}
