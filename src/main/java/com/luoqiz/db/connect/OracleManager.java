package com.luoqiz.db.connect;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.luoqiz.code.entity.ColumnInfo;
import com.luoqiz.code.entity.DatabaseInfo;
import com.luoqiz.code.entity.TableInfo;
import com.luoqiz.db.util.DBSqlDeal;

public class OracleManager extends DBmanagerAdapter {

	public Connection getConnect(DatabaseInfo connectModel) {
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

	public List<TableInfo> getTableList(Connection connection, String dbName) {
		return null;
	}

	@Override
	public List<ColumnInfo> getColumnByTable(Connection connection, String dbName, String tableName) {
		return null;
	}

	@Override
	public List<Map<String, Object>> getDataByDBInfo(Connection connection, String dbName, String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getDataByDBInfo(Connection con, DatabaseInfo dbInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}
