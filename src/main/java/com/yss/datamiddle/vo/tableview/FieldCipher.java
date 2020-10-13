package com.yss.datamiddle.vo.tableview;

import java.io.Serializable;

/**
 * 字段加密信息
 * 
 * @author LT
 * @date 2019年12月30日
 */
public class FieldCipher implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String cipher;
	private boolean dataCipher;

	public FieldCipher(String name, String cipher, boolean dataCipher) {
		this.name = name;
		this.cipher = cipher;
		this.dataCipher = dataCipher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCipher() {
		return cipher;
	}

	public void setCipher(String cipher) {
		this.cipher = cipher;
	}

	public boolean isDataCipher() {
		return dataCipher;
	}

	public void setDataCipher(boolean dataCipher) {
		this.dataCipher = dataCipher;
	}
}
