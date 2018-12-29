package com.luoqiz.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.luoqiz.code.entity.ColumnInfo;

public class DBSqlDeal {

	public static ArrayList<ColumnInfo> executeSql(Connection connection, String sql, boolean camelCase) {
		ArrayList<ColumnInfo> results = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		Statement cs = null;
		try {
			cs = connection.createStatement();
			rs = cs.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			// 通过rsmd获取该结果集有多少列
			int columnNum = rsmd.getColumnCount();
			// 循环取出数据，并封装到arraylist中
			while (rs.next()) {
				ColumnInfo columnInfo = new ColumnInfo();
				Map<String, Object> map = new HashMap<>();
				Object[] objects = new Object[columnNum];
				for (int i = 1; i <= objects.length; i++) {
					if (camelCase) {
						map.put(ConvertUtils.camelCase(rsmd.getColumnName(i), '_', true), rs.getObject(i));
					} else {
						columnInfo.setDbColumnType(rsmd.getColumnTypeName(i));
						columnInfo.setJavaColumnType(rsmd.getColumnClassName(i));
						columnInfo.setDbColumnName(rsmd.getColumnName(i));
						columnInfo.setValue(rs.getObject(i));
					}
				}
				results.add(columnInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBSqlDeal.closecon(null, ps, rs, cs);
		return results;
	}

	public static ArrayList<ColumnInfo> executeSql(Connection connection, String sql) {
		return executeSql(connection, sql, false);
	}

	public static ArrayList<Map<String,Object>> executeSqlMap(Connection connection, String sql, boolean camelCase) {
		ArrayList<Map<String,Object>> results = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		Statement cs = null;
		try {
			cs = connection.createStatement();
			rs = cs.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			// 通过rsmd获取该结果集有多少列
			int columnNum = rsmd.getColumnCount();
			// 循环取出数据，并封装到arraylist中
			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				Object[] objects = new Object[columnNum];
				for (int i = 1; i <= objects.length; i++) {
					if (camelCase) {
						map.put(ConvertUtils.camelCase(rsmd.getColumnName(i), '_', true), rs.getObject(i));
					} else {
						System.out.println("--------------------");
						System.out.println(rsmd.getColumnTypeName(i));
						System.out.println(rsmd.getColumnClassName(i));
						System.out.println(rsmd.getColumnName(i));
						System.out.println(rsmd.getCatalogName(i));
						System.out.println(rsmd.getSchemaName(i));
						map.put(rsmd.getColumnName(i), rs.getObject(i));
					}
				}
				results.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBSqlDeal.closecon(null, ps, rs, cs);
		return results;
	}

	public static ArrayList<Map<String,Object>> executeSqlMap(Connection connection, String sql) {
		return executeSqlMap(connection, sql, false);
	}
	
	public static int selectCount(Connection connection, String sql) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Statement cs = null;
		int result = 0;
		try {
			cs = connection.createStatement();
			rs = cs.executeQuery(sql);
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBSqlDeal.closecon(null, ps, rs, cs);
		return result;
	}

	public static Connection getCon(String driverName, String dbUrl, String username, String password) {
		Connection con = null;
		try {
			Class.forName(driverName);
			if (username != null) {
				con = DriverManager.getConnection(dbUrl, username, password);
			} else {
				con = DriverManager.getConnection(dbUrl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void closecon(Connection con, PreparedStatement ps, ResultSet rs, Statement cs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
			if (cs != null) {
				cs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
