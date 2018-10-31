package com.luoqiz.db.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbInfo {

	public static Logger log = LoggerFactory.getLogger(DbInfo.class);
	// 数据库类型
	private String dbType;

	// 数据库连接地址
	private String dbAddr;
	// 数据库连接端口
	private Integer dbPort;
	// 数据库连接库名
	private String dbName;

	// 数据库连接用户名
	private String dbUserName;

	// 数据库连接密码
	private String dbPassword;

	// 数据库选择的表名
	private String tableName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getDbAddr() {
		return dbAddr;
	}

	public void setDbAddr(String dbAddr) {
		this.dbAddr = dbAddr;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUserName() {
		return dbUserName;
	}

	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public Integer getDbPort() {
		return dbPort;
	}

	public void setDbPort(Integer dbPort) {
		this.dbPort = dbPort;
	}

	@Override
	public String toString() {
		return "ConnectModel [dbType=" + dbType + ", dbAddr=" + dbAddr + ", dbPort=" + dbPort + ", dbName=" + dbName
				+ ", dbUserName=" + dbUserName + ", dbPassword=" + dbPassword + ", tableName=" + tableName + "]";
	}

}
