package com.luoqiz.db.connect;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.luoqiz.db.model.DbInfo;
import com.luoqiz.db.util.DBSqlDeal;

public class MssqlManager implements DBmanager {

	public Connection getConnect(DbInfo connectModel) {
		if (connectModel.getDbPort() == null) {
			connectModel.setDbPort(1433);
		}
		if (StringUtils.isBlank(connectModel.getDbUserName())) {
			connectModel.setDbUserName("sa");
		}
		if (StringUtils.isBlank(connectModel.getDbPassword())) {
			connectModel.setDbPassword("");
		}
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbUrl = String.format("jdbc:sqlserver://%s:%s;DatabaseName=%s", connectModel.getDbAddr(),
				connectModel.getDbPort(), connectModel.getDbName());
		return DBSqlDeal.getCon(driverName, dbUrl, connectModel.getDbUserName(), connectModel.getDbPassword());
	}

	public List<Map<String, Object>> getTableList(Connection connection, String dbName) {
		String sql = "select name from sysobjects where xtype='u'";
		return DBSqlDeal.executeSql(connection, sql);
	}

	@Override
	public List<Map<String, Object>> getColumnByTable(Connection connection, String dbName, String tableName) {
		String sql = "SELECT fieldNum = a.colorder,fieldName  = a.name,fieldType = b.name,fieldIndex = case when COLUMNPROPERTY( a.id,a.name,'IsIdentity')=1 then 'Yes' else '' end,"
				+ " fieldKey = case when exists(SELECT 1 FROM sysobjects where xtype='PK' and parent_obj=a.id and name in (SELECT name FROM sysindexes WHERE indid in( SELECT indid FROM sysindexkeys WHERE id = a.id AND colid=a.colid))) then 'Yes' else '' end,"
				+ " fieldLen = COLUMNPROPERTY(a.id,a.name,'PRECISION'),fieldIsNull= case when a.isnullable=1 then 'Yes' else '' end,"
				+ " fieldDefault= isnull(e.text,''),fieldMark = isnull(cast(g.value as varchar(500)) ,'')"
				+ " FROM syscolumns a left join systypes b on a.xusertype=b.xusertype inner join sysobjects d on a.id=d.id  and d.xtype='U' and  d.name<>'dtproperties' left join syscomments e on a.cdefault=e.id left join sys.extended_properties g on a.id=G.major_id and a.colid=g.minor_id  left join sys.extended_properties f"
				+ " on d.id=f.major_id and f.minor_id=0 where d.name='" + tableName + "' order by  a.id,a.colorder";
		return DBSqlDeal.executeSql(connection, sql);
	}
}
