package com.luoqiz.db.connect;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.luoqiz.code.entity.ColumnInfo;
import com.luoqiz.code.entity.DatabaseInfo;
import com.luoqiz.code.entity.TableInfo;

public interface DBmanager {

	/**
	 * 获取连接
	 * 
	 * @param connectModel
	 * @return
	 */
	Connection getConnect(DatabaseInfo dbInfo);

	/**
	 * 关闭连接
	 * 
	 * @param connectModel
	 * @return
	 */
	void closeConnect(Connection con);

	/**
	 * 获取所有表列表
	 * 
	 * @param connection
	 * @param dbName
	 * @return
	 */
	List<TableInfo> getTableList(Connection connection, String dbName);

	/**
	 * 获取表内所有列
	 * 
	 * @param connection
	 * @param dbName
	 * @param tableName
	 * @return
	 */
	List<ColumnInfo> getColumnByTable(Connection connection, String dbName, String tableName);

	/**
	 * 获取表内指定数据
	 * 
	 * @param connection
	 * @param dbName
	 * @param tableName
	 * @return
	 */
	List<Map<String, Object>> getDataByDBInfo(Connection connection, String dbName, String tableName);

	/**
	 * 获取数据
	 */
	List<Map<String, Object>> getDataByDBInfo(Connection con, DatabaseInfo dbInfo);

	/**
	 * 获取记录总数
	 * 
	 * @param con
	 * @param dbInfo
	 * @return
	 */
	int selectCount(Connection con, DatabaseInfo dbInfo);

}
