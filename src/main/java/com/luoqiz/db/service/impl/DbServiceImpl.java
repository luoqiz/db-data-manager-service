package com.luoqiz.db.service.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.luoqiz.db.connect.DBmanager;
import com.luoqiz.db.connect.DbManagerFactory;
import com.luoqiz.db.model.DbInfo;
import com.luoqiz.db.service.DbService;

@Service
public class DbServiceImpl implements DbService {

	@Override
	public List<Map<String, Object>> findTableList(DbInfo dbInfo) {
		DBmanager dbmanager = DbManagerFactory.getManager(dbInfo);
		Connection con = dbmanager.getConnect(dbInfo);
		List<Map<String, Object>> result = dbmanager.getTableList(con, dbInfo.getDbName());
		dbmanager.closeConnect(con);
		return result;
	}

	@Override
	public List<Map<String, Object>> findColumnByTable(DbInfo dbInfo) {
		DBmanager dbmanager = DbManagerFactory.getManager(dbInfo);
		Connection con = dbmanager.getConnect(dbInfo);
		List<Map<String, Object>> result = dbmanager.getColumnByTable(con, dbInfo.getDbName(), dbInfo.getTableName());
		dbmanager.closeConnect(con);
		return result;
	}

	@Override
	public Map<String, Object> findDataByDbInfo(DbInfo dbInfo) {
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
