package com.luoqiz.db.connect;

import com.luoqiz.code.entity.DatabaseInfo;


public class DbManagerFactory {

	public static DBmanager getManager(DatabaseInfo connectModel) {
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
