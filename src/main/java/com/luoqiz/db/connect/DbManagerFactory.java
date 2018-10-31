package com.luoqiz.db.connect;

import com.luoqiz.db.model.DbInfo;


public class DbManagerFactory {

	public static DBmanager getManager(DbInfo connectModel) {
		if("Mysql".equalsIgnoreCase(connectModel.getDbType())) {
			return new MysqlManager();
		}else if("Mssql".equalsIgnoreCase(connectModel.getDbType())) {
			return new MssqlManager();
		}else if("Oracle".equalsIgnoreCase(connectModel.getDbType())) {
			return new OracleManager();
		}
		return null;
	};
}
