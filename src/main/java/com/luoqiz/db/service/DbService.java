package com.luoqiz.db.service;

import java.util.List;
import java.util.Map;

import com.luoqiz.db.model.DbInfo;

public interface DbService {

	List<Map<String, Object>> findTableList(DbInfo dbInfo);

	List<Map<String, Object>> findColumnByTable(DbInfo dbInfo);

	Map<String, Object> findDataByDbInfo(DbInfo dbInfo);

}
