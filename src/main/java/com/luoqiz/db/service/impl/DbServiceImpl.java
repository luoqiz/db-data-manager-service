package com.luoqiz.db.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.luoqiz.db.connect.DBmanager;
import com.luoqiz.db.connect.DbManagerFactory;
import com.luoqiz.db.model.DbInfo;
import com.luoqiz.db.service.DbService;

@Service
public class DbServiceImpl implements DbService{

	@Override
	public List<Map<String, Object>> findTableList(DbInfo dbInfo) {
		DBmanager dbmanager = DbManagerFactory.getManager(dbInfo);
		Connection con = dbmanager.getConnect(dbInfo);
		return dbmanager.getTableList(con, dbInfo.getDbName());
	}

	@Override
	public List<Map<String, Object>> findColumnByTable(DbInfo dbInfo) {
		DBmanager dbmanager = DbManagerFactory.getManager(dbInfo);
		Connection con = dbmanager.getConnect(dbInfo);
		return dbmanager.getColumnByTable(con, dbInfo.getDbName(),dbInfo.getTableName());
	}

}
