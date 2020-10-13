package com.yss.datamiddle.vo.tableview;

import com.yss.datamiddle.vo.BaseEntity;

import java.util.Map;

/**
 * 表加密信息
 * 
 * @author LT
 * @date 2019年12月10日
 *
 */
public class TableCipher extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id;
	private String uri;
	private String tableName;
	private String cipherName;
	private String algorithm;
	// private List<BiField> biFields;
	/** 字段名明文、密文映射 */
	private Map<String, FieldCipher> field2CipherMap;
	/** 字段名密文、明文映射 */
	private Map<String, FieldCipher> cipher2FieldMap;

	/**
	 * 是否为加密表结构
	 * 
	 * @return true:是/false:否
	 */
	public boolean isCipher() {
		return !tableName.equals(cipherName);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getCipherName() {
		return cipherName;
	}

	public void setCipherName(String cipherName) {
		this.cipherName = cipherName;
	}

	public Map<String, FieldCipher> getField2CipherMap() {
		return field2CipherMap;
	}

	public void setField2CipherMap(Map<String, FieldCipher> field2CipherMap) {
		this.field2CipherMap = field2CipherMap;
	}

	public Map<String, FieldCipher> getCipher2FieldMap() {
		return cipher2FieldMap;
	}

	public void setCipher2FieldMap(Map<String, FieldCipher> cipher2FieldMap) {
		this.cipher2FieldMap = cipher2FieldMap;
	}
}
