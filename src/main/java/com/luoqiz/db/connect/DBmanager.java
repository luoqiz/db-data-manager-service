package com.luoqiz.db.connect;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.luoqiz.db.model.DbInfo;

public interface DBmanager {

	/**
	 * 获取连接
	 * 
	 * @param connectModel
	 * @return
	 */
	Connection getConnect(DbInfo dbInfo);
	
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
	List<Map<String, Object>> getTableList(Connection connection, String dbName);

	/**
	 * 获取表内所有列
	 * @param connection
	 * @param dbName
	 * @param tableName
	 * @return
	 */
	List<Map<String, Object>> getColumnByTable(Connection connection, String dbName, String tableName);

	/**
	 * 获取表内指定数据
	 * @param connection
	 * @param dbName
	 * @param tableName
	 * @return
	 */
	List<Map<String, Object>> getDataByDBInfo(Connection connection,String dbName, String tableName);

	List<Map<String,Object>> getDataByDBInfo(Connection con, DbInfo dbInfo);

	int selectCount(Connection con, DbInfo dbInfo);

}
