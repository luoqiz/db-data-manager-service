package com.luoqiz.db.service.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.luoqiz.code.entity.ColumnInfo;
import com.luoqiz.code.entity.DatabaseInfo;
import com.luoqiz.code.entity.TableInfo;
import com.luoqiz.db.connect.DBmanager;
import com.luoqiz.db.connect.DbManagerFactory;
import com.luoqiz.db.service.DbService;

@Service
public class DbServiceImpl implements DbService {

	@Override
	public List<TableInfo> findTableList(DatabaseInfo dbInfo) {
		DBmanager dbmanager = DbManagerFactory.getManager(dbInfo);
		Connection con = dbmanager.getConnect(dbInfo);
		List<TableInfo> result = dbmanager.getTableList(con, dbInfo.getDbName());
		dbmanager.closeConnect(con);
		return result;
	}

	@Override
	public List<ColumnInfo> findColumnByTable(DatabaseInfo dbInfo) {
		DBmanager dbmanager = DbManagerFactory.getManager(dbInfo);
		Connection con = dbmanager.getConnect(dbInfo);
		List<ColumnInfo> result = dbmanager.getColumnByTable(con, dbInfo.getDbName(), dbInfo.getTableName());
		dbmanager.closeConnect(con);
		return result;
	}

	@Override
	public Map<String, Object> findDataByDbInfo(DatabaseInfo dbInfo) {
		DBmanager dbmanager = DbManagerFactory.getManager(dbInfo);
		Connection con = dbmanager.getConnect(dbInfo);
		List<Map<String, Object>> result = dbmanager.getDataByDBInfo(con, dbInfo);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("dataList", result);
		int total = dbmanager.selectCount(con, dbInfo);
		map.put("total", total);
		dbmanager.closeConnect(con);
		return map;
	}

}
