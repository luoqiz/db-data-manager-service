package com.luoqiz.db.model;
/**
 * 
* @ClassName: JtableDataModel 
* @Description: 表格数据模型
* @author 罗强争
* @date 2017年6月30日 下午1:24:10 
*
 */
public class JtableDataModel {

	private int num;	//序号
	private String dbColumnName; //数据库中的列名
	private String javaColumnName; //java中的列名
	private String dbColumnType;	//数据库中的字段类型
	private String javaColumnType;	//java中对应的字段数据类型
	private boolean indexKey;		//是否是索引字段
	private boolean primayKey;		//是否是主键
	private int columnLength;		//共有多少列
	private boolean nullValue;		//是否允许为空
	private String defaultValue;	//默认值是
	private String comment;			//备注
	private String searchWay;		//查询模式
	private String otherOper;		//其他操作
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public boolean isIndexKey() {
		return indexKey;
	}
	public void setIndexKey(boolean indexKey) {
		this.indexKey = indexKey;
	}
	public boolean isPrimayKey() {
		return primayKey;
	}
	public void setPrimayKey(boolean primayKey) {
		this.primayKey = primayKey;
	}
	public int getColumnLength() {
		return columnLength;
	}
	public void setColumnLength(int columnLength) {
		this.columnLength = columnLength;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getSearchWay() {
		return searchWay;
	}
	public void setSearchWay(String searchWay) {
		this.searchWay = searchWay;
	}
	public String getOtherOper() {
		return otherOper;
	}
	public void setOtherOper(String otherOper) {
		if(otherOper==null || "".equals(otherOper) || "null".equals(otherOper)) {
			otherOper="and";
		}
		this.otherOper = otherOper;
	}
	public boolean isNullValue() {
		return nullValue;
	}
	public void setNullValue(boolean nullValue) {
		this.nullValue = nullValue;
	}
	public String getJavaColumnType() {
		return javaColumnType;
	}
	public void setJavaColumnType(String javaColumnType) {
		this.javaColumnType = javaColumnType;
	}
	public String getDbColumnName() {
		return dbColumnName;
	}
	public void setDbColumnName(String dbColumnName) {
		this.dbColumnName = dbColumnName;
	}
	public String getJavaColumnName() {
		return javaColumnName;
	}
	public void setJavaColumnName(String javaColumnName) {
		this.javaColumnName = javaColumnName;
	}
	public String getDbColumnType() {
		return dbColumnType;
	}
	public void setDbColumnType(String dbColumnType) {
		this.dbColumnType = dbColumnType;
	}
}
