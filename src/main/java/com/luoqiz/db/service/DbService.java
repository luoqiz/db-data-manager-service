package com.luoqiz.db.service;

import java.util.List;
import java.util.Map;

import com.luoqiz.code.entity.ColumnInfo;
import com.luoqiz.code.entity.DatabaseInfo;
import com.luoqiz.code.entity.TableInfo;

public interface DbService {

	List<TableInfo> findTableList(DatabaseInfo dbInfo);

	List<ColumnInfo> findColumnByTable(DatabaseInfo dbInfo);

	Map<String, Object> findDataByDbInfo(DatabaseInfo dbInfo);

}
