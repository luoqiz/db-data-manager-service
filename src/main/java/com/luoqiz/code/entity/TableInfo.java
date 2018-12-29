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
	private String tempStr; // 存放从旧文件中读取的旧方法
}
