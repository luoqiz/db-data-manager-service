package com.luoqiz.db.connect;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.luoqiz.db.model.DbInfo;
import com.luoqiz.db.util.DBSqlDeal;

public class MysqlManager implements DBmanager {

	public Connection getConnect(DbInfo connectModel) {
		if(connectModel.getDbPort()==null) {
			connectModel.setDbPort(3306);
		}
		if(StringUtils.isBlank(connectModel.getDbUserName())) {
			connectModel.setDbUserName("root");
		}
		if(StringUtils.isBlank(connectModel.getDbPassword())) {
			connectModel.setDbPassword("");
		}
		String driverName = "com.mysql.jdbc.Driver";
		String dbUrl = String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT&useSSL=false",
				connectModel.getDbAddr(), connectModel.getDbPort(), connectModel.getDbName());
		return DBSqlDeal.getCon(driverName, dbUrl, connectModel.getDbUserName(), connectModel.getDbPassword());
	}

	public List<Map<String, Object>> getTableList(Connection connection, String dbName) {
		String sql = String.format(
				"select TABLE_NAME, TABLE_COMMENT from information_schema.tables where table_schema='%s'", dbName);
		return DBSqlDeal.executeSql(connection, sql);
	}

	@Override
	public List<Map<String, Object>> getColumnByTable(Connection connection, String dbName, String tableName) {
		String sql = "select ordinal_position as fieldNum,column_name as fieldName,data_type as fieldType,'' as fieldIndex,column_key as fieldKey,character_maximum_length as fieldLen,is_nullAble as fieldIsNull,column_default as fieldDefault,column_comment as fieldMark "
				+ "from information_schema.columns where table_name='" + tableName + "' AND TABLE_SCHEMA='" + dbName
				+ "'";
		return DBSqlDeal.executeSql(connection, sql);
	}
}
