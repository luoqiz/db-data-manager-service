package com.luoqiz.code.entity;

import lombok.Data;

@Data
public class ColumnInfo {

	private int fieldNum;			//序号
	private String dbColumnName; 	//数据库中的列名
	private String javaColumnName; 	//java中的列名
	private String dbColumnType;	//数据库中的字段类型
	private String javaColumnType;	//java中对应的字段数据类型
	private boolean indexKey;		//是否是索引字段
	private boolean primayKey;		//是否是主键
	private int length;				//限制长度
	private boolean nullValue;		//是否允许为空
	private String defaultValue;	//默认值是
	private String comment;			//备注
	private String searchWay;		//查询模式
	private Object value;			//查询值
	private String otherOper;		//其他操作

}
