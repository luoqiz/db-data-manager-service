package com.luoqiz.code.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DatabaseInfo {

	public static Logger log = LoggerFactory.getLogger(DatabaseInfo.class);
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

}
