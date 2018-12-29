package com.luoqiz.db.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

	public static String twoStringBetweenGreed(String str, String start, String end) {
		String rgex = String.format("%s(.*)%s", start, end);
		Pattern pattern = Pattern.compile(rgex);// 匹配的模式
		Matcher m = pattern.matcher(str);
		while (m.find()) {
			return m.group(1);
		}
		return "";
	}

	public static String twoStringBetweenFrist(String str, String start, String end) {
		String rgex = String.format("%s(.*?)%s", start, end);
		Pattern pattern = Pattern.compile(rgex);// 匹配的模式
		Matcher m = pattern.matcher(str);
		while (m.find()) {
			return m.group(1);
		}
		return "";
	}

	public static String twoStringBetweenLast(String str, String start, String end) {
		String rgex = String.format("%s(.*?)%s", start, end);
		Pattern pattern = Pattern.compile(rgex);// 匹配的模式
		Matcher m = pattern.matcher(str);
		String rs = "";
		while (m.find()) {
			rs = m.group(1);
		}
		return rs;
	}

	public static List<String> getList(String str, String rgex) {
		List<String> list = new ArrayList<String>();
		Pattern pattern = Pattern.compile(rgex);// 匹配的模式
		Matcher m = pattern.matcher(str);
		while (m.find()) {
			int i = 1;
			list.add(m.group(i));
			i++;
		}
		return list;
	}

	/**
	 * 
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
//		String str = "package com.luoqiz.code.test.mapper;import com.luoqiz.db.config.MybatisMapper;import com.luoqiz.code.test.entity.QrtzCalendarsEntity;/** * @author luoqiz * @Date: 2018-12-29 17:13:38 *  */public interface QrtzCalendarsMapper extends MybatisMapper<QrtzCalendarsEntity> {	public void test();}";
		String str = "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" + 
				"\n" + 
				"<mapper namespace=\"${mapperTargetPackage}.${tempTableInfo.className}Mapper\">\n" + 
				"    <resultMap id=\"BaseResultMap\" type=\"${entityTargetPackage}.${tempTableInfo.className?cap_first}Entity\">\n" + 
				"    <#list tempTableInfo.columnInfoList as columnInfo>\n" + 
				"	<#if columnInfo[\"primayKey\"]==true>\n" + 
				"		<id column=\"${columnInfo[\"dbColumnName\"]}\" jdbcType=\"${columnInfo[\"dbColumnType\"]?upper_case}\" property=\"${columnInfo[\"javaColumnName\"]}\" />\n" + 
				"	<#else>\n" + 
				"      	<result column=\"${columnInfo[\"dbColumnName\"]}\" jdbcType=\"${columnInfo[\"dbColumnType\"]?upper_case}\" property=\"${columnInfo[\"javaColumnName\"]}\" />\n" + 
				"	</#if>\n" + 
				"	</#list>\n" + 
				"    </resultMap>484sf4a5s"
				+ "f54esklllllllllllllll" + 
				"</mapper>";
		String rgex = "\\{(.*?)\\}";
		System.out.println(getList(str, rgex));
		System.out.println(twoStringBetweenGreed(str, "resultMap>", "</mapper>"));
		System.out.println(twoStringBetweenFrist(str, "\\{", "\\}"));
		System.out.println("twoStringBetweenLast : " + twoStringBetweenLast(str, "\\{", "\\}"));
	}
}
