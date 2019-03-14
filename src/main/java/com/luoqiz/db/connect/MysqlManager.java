package com.luoqiz.db.connect;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.luoqiz.code.constant.ColumnType;
import com.luoqiz.code.entity.ColumnInfo;
import com.luoqiz.code.entity.DatabaseInfo;
import com.luoqiz.code.entity.TableInfo;
import com.luoqiz.db.util.ConvertUtils;
import com.luoqiz.db.util.DBSqlDeal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MysqlManager extends DBmanagerAdapter {

	public Connection getConnect(DatabaseInfo connectModel) {
		if (connectModel.getDbPort() == null) {
			connectModel.setDbPort(3306);
		}
		if (StringUtils.isBlank(connectModel.getDbUserName())) {
			connectModel.setDbUserName("root");
		}
		if (StringUtils.isBlank(connectModel.getDbPassword())) {
			connectModel.setDbPassword("");
		}
		String driverName = "com.mysql.cj.jdbc.Driver";
		String dbUrl = String.format(
				"jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&useOldAliasMetadataBehavior=true",
				connectModel.getDbAddr(), connectModel.getDbPort(), connectModel.getDbName());
		return DBSqlDeal.getCon(driverName, dbUrl, connectModel.getDbUserName(), connectModel.getDbPassword());
	}

	public List<TableInfo> getTableList(Connection connection, String dbName) {
		String sql = String.format(
				"select TABLE_NAME as tableName, TABLE_COMMENT as tableComment from information_schema.tables where table_schema='%s'",
				dbName);
		DBSqlDeal.executeSql(connection, sql);
		ArrayList<TableInfo> columnList = new ArrayList<TableInfo>();
		List<Map<String, Object>> rows = DBSqlDeal.executeSqlMap(connection, sql);
		rows.forEach(row -> {
			TableInfo tableInfo = JSON.parseObject(JSON.toJSONString(row), TableInfo.class);
			tableInfo.setClassName(ConvertUtils.capFirst(ConvertUtils.camelCase(tableInfo.getTableName(), '_', true)));
			columnList.add(tableInfo);
		});
		return columnList;
	}

	@Override
	public List<ColumnInfo> getColumnByTable(Connection connection, String dbName, String tableName) {
		String sql = "select ordinal_position  fieldNum, column_name as dbColumnName,"
				+ "data_type  dbColumnType,'' as fieldIndex,column_key as fieldKey,"
				+ "character_maximum_length as length,(case is_nullAble when 'NO' then false when 'YES' then true end) nullValue,"
				+ "column_default as defaultValue,column_comment as comment "
				+ "from information_schema.columns where table_name='" + tableName + "' AND TABLE_SCHEMA='" + dbName
				+ "' ORDER BY fieldNum asc";

		log.info("sql 执行语句：" + sql);
		List<ColumnInfo> columnList = new ArrayList<>();
		List<Map<String, Object>> rows = DBSqlDeal.executeSqlMap(connection, sql);
		rows.forEach(row -> {

			ColumnInfo column = JSON.parseObject(JSON.toJSONString(row), ColumnInfo.class);
			jdbcTypeToJavaType(column);
			column.setJavaColumnName(ConvertUtils.camelCase(column.getDbColumnName(), '_', true));
			fieldKey(column, String.valueOf(row.get("fieldKey")));
			columnList.add(column);
		});
		return columnList;
	}

	@Override
	public List<Map<String, Object>> getDataByDBInfo(Connection connection, String dbName, String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getDataByDBInfo(Connection con, DatabaseInfo dbInfo) {
		List<ColumnInfo> columnList = getColumnByTable(con, dbInfo.getDbName(), dbInfo.getTableName());
		String columnString = "";
		for (ColumnInfo column : columnList) {
			columnString += "," + column.getDbColumnName();
		}
		String sql = "select " + columnString.substring(1) + " from " + dbInfo.getDbName() + "."
				+ dbInfo.getTableName();

		return DBSqlDeal.executeSqlMap(con, sql);
	}

	public void fieldKey(ColumnInfo columnInfo, String fieldKey) {
		if (fieldKey != null) {
			switch (fieldKey) {
			case "PRI":
				columnInfo.setPrimayKey(true);
				break;

			case "UNI":
				columnInfo.setIndexKey(true);
				break;

			default:
				break;
			}
		}
	}

	private void jdbcTypeToJavaType(ColumnInfo columnInfo) {
		String jdbcType = columnInfo.getDbColumnType().split(" ")[0].toLowerCase();
		if (jdbcType == null) {
			return;
		}
		switch (jdbcType) {
		case ColumnType.INTEGER:
			columnInfo.setJavaColumnType(ColumnType.INTEGER);
			break;
		case ColumnType.INT:
			columnInfo.setDbColumnType(ColumnType.INTEGER);
			columnInfo.setJavaColumnType(ColumnType.INTEGER);
			break;
		case ColumnType.BIGINT:
			columnInfo.setJavaColumnType(ColumnType.LONG);
			break;
		case ColumnType.TINYINT:
			columnInfo.setJavaColumnType(ColumnType.BOOLEAN);
			break;
		case ColumnType.BIT:
			columnInfo.setJavaColumnType(ColumnType.BOOLEAN);
			break;
		case ColumnType.DATE:
			columnInfo.setJavaColumnType(ColumnType.DATE_CAP);
			break;
		case ColumnType.DATETIME:
			columnInfo.setDbColumnType(ColumnType.TIMESTAMP);
			columnInfo.setJavaColumnType(ColumnType.DATE_CAP);
			break;

		case ColumnType.TIMESTAMP:
			columnInfo.setJavaColumnType(ColumnType.DATE_CAP);
			break;
		case ColumnType.MEDIUMTEXT:
			columnInfo.setDbColumnType(ColumnType.LONGVARCHAR);
			columnInfo.setJavaColumnType(ColumnType.DATE_CAP);
			break;
		case ColumnType.TEXT:
			columnInfo.setDbColumnType(ColumnType.LONGVARCHAR);
			columnInfo.setJavaColumnType(ColumnType.DATE_CAP);
			break;
		case ColumnType.LONGTEXT:
			columnInfo.setDbColumnType(ColumnType.LONGVARCHAR);
			columnInfo.setJavaColumnType(ColumnType.DATE_CAP);
			break;
		default:
			columnInfo.setJavaColumnType(ColumnType.STRING);
			break;
		}
	}

}
