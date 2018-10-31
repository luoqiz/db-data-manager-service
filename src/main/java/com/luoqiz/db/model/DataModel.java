package com.luoqiz.db.model;

import java.io.Serializable;
import java.util.ArrayList;


public class DataModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<?> arrayList;

	// 设置当前作者
	private String author;

	//生成包结构是否添加表名
	private boolean tableExsits;
	
	private String tableName; // 表名
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

	// 实体类
	private String entityName; // 实体类名称
	private String entityPackagePath; // 实体类路径
	private String entityPackageName; // 实体类包名
	private String entityTempPackageName; // 实体类临时包名

	public ArrayList<?> getArrayList() {
		return arrayList;
	}

	public void setArrayList(ArrayList<?> arrayList) {
		this.arrayList = arrayList;
	}

	public boolean isImportUtil() {
		return importUtil;
	}

	public void setImportUtil(boolean importUtil) {
		this.importUtil = importUtil;
	}

	public boolean isImportSql() {
		return importSql;
	}

	public void setImportSql(boolean importSql) {
		this.importSql = importSql;
	}

	public String[] getColnames() {
		return colnames;
	}

	public void setColnames(String[] colnames) {
		this.colnames = colnames;
	}

	public String[] getJdbcColNames() {
		return jdbcColNames;
	}

	public void setJdbcColNames(String[] jdbcColNames) {
		this.jdbcColNames = jdbcColNames;
	}

	public String[] getJavaColTypes() {
		return javaColTypes;
	}

	public void setJavaColTypes(String[] javaColTypes) {
		this.javaColTypes = javaColTypes;
	}

	public String[] getJdbcColTypes() {
		return jdbcColTypes;
	}

	public void setJdbcColTypes(String[] jdbcColTypes) {
		this.jdbcColTypes = jdbcColTypes;
	}

	public int[] getColSizes() {
		return colSizes;
	}

	public void setColSizes(int[] colSizes) {
		this.colSizes = colSizes;
	}

	public int getColSize() {
		return colSize;
	}

	public void setColSize(int colSize) {
		this.colSize = colSize;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public String getSqlTablePK() {
		return sqlTablePK;
	}

	public void setSqlTablePK(String sqlTablePK) {
		this.sqlTablePK = sqlTablePK;
	}

	public String getJavaTablePK() {
		return javaTablePK;
	}

	public void setJavaTablePK(String javaTablePK) {
		this.javaTablePK = javaTablePK;
	}

	public String getSqlTablePKType() {
		return sqlTablePKType;
	}

	public void setSqlTablePKType(String sqlTablePKType) {
		this.sqlTablePKType = sqlTablePKType;
	}

	public String getJavaTablePKType() {
		return javaTablePKType;
	}

	public void setJavaTablePKType(String javaTablePKType) {
		this.javaTablePKType = javaTablePKType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFullPackagePath() {
		return fullPackagePath;
	}

	public void setFullPackagePath(String fullPackagePath) {
		this.fullPackagePath = fullPackagePath;
	}

	public String getFullPackageName() {
		return fullPackageName;
	}

	public void setFullPackageName(String fullPackageName) {
		this.fullPackageName = fullPackageName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isTableExsits() {
		return tableExsits;
	}

	public void setTableExsits(boolean tableExsits) {
		this.tableExsits = tableExsits;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityPackagePath() {
		return entityPackagePath;
	}

	public void setEntityPackagePath(String entityPackagePath) {
		this.entityPackagePath = entityPackagePath;
	}

	public String getEntityPackageName() {
		return entityPackageName;
	}

	public void setEntityPackageName(String entityPackageName) {
		this.entityPackageName = entityPackageName;
	}

	public String getEntityTempPackageName() {
		return entityTempPackageName;
	}

	public void setEntityTempPackageName(String entityTempPackageName) {
		this.entityTempPackageName = entityTempPackageName;
	}
}
