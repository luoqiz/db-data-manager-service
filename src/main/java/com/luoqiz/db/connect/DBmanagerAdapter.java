package com.luoqiz.db.connect;

import java.sql.Connection;

import com.luoqiz.code.entity.DatabaseInfo;
import com.luoqiz.db.util.DBSqlDeal;

public abstract class DBmanagerAdapter implements DBmanager {

	/**
	 * 关闭连接
	 * 
	 * @param connectModel
	 * @return
	 */
	public void closeConnect(Connection con) {
		DBSqlDeal.closecon(con, null, null, null);
	};

	/**
	 * 关闭连接
	 * 
	 * @param connectModel
	 * @return
	 */
	public int selectCount(Connection con, DatabaseInfo dbInfo) {
		String sql = "select count(1)  from " + dbInfo.getDbName() + "." + dbInfo.getTableName();
		return DBSqlDeal.selectCount(con, sql);
	};
}
