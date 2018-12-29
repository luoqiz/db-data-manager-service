package com.luoqiz.code.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TableInfo {

	// 列信息
	private List<ColumnInfo> columnInfoList = new ArrayList<ColumnInfo>();
	private String tableName; // 表名
	private String className; // 表名对应的类名
	private String tableComment;

	private boolean importUtil = false; // 是否需要导入包java.util.*
	private boolean importSql = false; // 是否需要导入包java.sql.*
	private String[] colnames; // 列名数组
	private String[] jdbcColNames; // 数据库列名数组
	private String[] javaColTypes; // java列名类型数组
	private String[] jdbcColTypes; // 列名类型数组
	private int[] colSizes; // 列名大小数组
	private int colSize; // 列的个数
	private String projectPath; // 获取项目路径
	private String fullPackagePath; // 完整路径（no include subs）
	private String fullPackageName; // 包名称
	private String pagePath; // 页面路径
	private String sqlTablePK; // 表的主键（在数据库中）
	private String javaTablePK; // 表的主键（在java中对应）
	private String sqlTablePKType; // 表的主键类型（数据库中对应）
	private String javaTablePKType; // 表的主键类型（java中对应）

}
