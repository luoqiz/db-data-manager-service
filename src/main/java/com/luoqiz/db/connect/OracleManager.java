package com.luoqiz.db.connect;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.luoqiz.db.model.DbInfo;
import com.luoqiz.db.util.DBSqlDeal;

public class OracleManager implements DBmanager {

	public Connection getConnect(DbInfo connectModel) {
		if (connectModel.getDbPort() == null) {
			connectModel.setDbPort(1521);
		}
		if (StringUtils.isBlank(connectModel.getDbUserName())) {
			connectModel.setDbUserName("");
		}
		if (StringUtils.isBlank(connectModel.getDbPassword())) {
			connectModel.setDbPassword("");
		}
		String driverName = "oracle.jdbc.driver.OracleDriver";
		// XE是精简版Oracle的默认数据库名
		String dbUrl = String.format("jdbc:oracle:thin:@%s:%s:%s", connectModel.getDbAddr(), connectModel.getDbPort(),
				connectModel.getDbName());
		return DBSqlDeal.getCon(driverName, dbUrl, connectModel.getDbUserName(), connectModel.getDbPassword());
	}

	public List<Map<String, Object>> getTableList(Connection connection, String dbName) {
		return null;
	}

	@Override
	public List<Map<String, Object>> getColumnByTable(Connection connection, String dbName, String tableName) {
		return null;
	}
}
