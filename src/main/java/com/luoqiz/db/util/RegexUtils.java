package com.luoqiz.db.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

	public static String twoStringBetweenGreed(String str, String start, String end) {
		String rgex = String.format("%s([\\s\\S]*)%s", start, end);
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
		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
				"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" + 
				"<mapper namespace=\"com.boya.db.mapper.EventRecordMapper\">\n" + 
				"  <resultMap id=\"BaseResultMap\" type=\"com.boya.db.domain.EventRecord\">\n" + 
				"    <!--\n" + 
				"      WARNING - @mbg.generated\n" + 
				"    -->\n" + 
				"    <id column=\"id\" jdbcType=\"VARCHAR\" property=\"id\" />\n" + 
				"    <result column=\"user_id\" jdbcType=\"BIGINT\" property=\"userId\" />\n" + 
				"    <result column=\"event_title\" jdbcType=\"VARCHAR\" property=\"eventTitle\" />\n" + 
				"    <result column=\"event_digest\" jdbcType=\"VARCHAR\" property=\"eventDigest\" />\n" + 
				"    <result column=\"keywords\" jdbcType=\"VARCHAR\" property=\"keywords\" />\n" + 
				"    <result column=\"exclude\" jdbcType=\"VARCHAR\" property=\"exclude\" />\n" + 
				"    <result column=\"start_time\" jdbcType=\"TIMESTAMP\" property=\"startTime\" />\n" + 
				"    <result column=\"end_time\" jdbcType=\"TIMESTAMP\" property=\"endTime\" />\n" + 
				"    <result column=\"create_time\" jdbcType=\"TIMESTAMP\" property=\"createTime\" />\n" + 
				"    <result column=\"update_time\" jdbcType=\"TIMESTAMP\" property=\"updateTime\" />\n" + 
				"    <result column=\"effective\" jdbcType=\"BIT\" property=\"effective\" />\n" + 
				"    <result column=\"remarks\" jdbcType=\"VARCHAR\" property=\"remarks\" />\n" + 
				"  </resultMap>\n" + 
				"  \n" + 
				"  <select id=\"selectEventInfoByUidEid\" resultType=\"com.boya.common.vo.event.EventInfoByUserIdVo\">\n" + 
				"  		select elist.id AS eventId,\n" + 
				"		  	IFNULL( eat.id, NULL ) attention,\n" + 
				"			IFNULL( ef.id, NULL ) forewarning,\n" + 
				"			IFNULL( ers.id, NULL ) retrospective,\n" + 
				"			IFNULL( eld.id, NULL ) look,\n" + 
				"			IFNULL( elc.num_look, 0 ) num_look\n" + 
				"		\n" + 
				"		from (select substring_index(substring_index(a.id,',',b.help_topic_id+1),',',-1)  as id\n" + 
				"			from (select CONCAT_WS	\n" + 
				"		<foreach close=\")\" collection=\"list\" index=\"index\" item=\"item\" open=\"(',',\" separator=\",\">\n" + 
				"			#{item}       \n" + 
				"		</foreach>\n" + 
				"			 as id) a\n" + 
				"			join mysql.help_topic b\n" + 
				"			on b.help_topic_id &lt; (length(a.id) - length(replace(a.id,',',''))+1)\n" + 
				"		) elist\n" + 
				"		LEFT JOIN event_forewarning ef 		on 		ef.event_id  = elist.id and ef.user_id = #{userId,jdbcType=BIGINT}\n" + 
				"		LEFT JOIN	event_retrospective ers on 		ers.event_id = elist.id and ers.user_id = #{userId,jdbcType=BIGINT}\n" + 
				"		LEFT JOIN event_attention eat 		on		eat.event_id = elist.id and eat.user_id = #{userId,jdbcType=BIGINT}\n" + 
				"		LEFT JOIN	event_look_details eld 	on 		eld.event_id = elist.id and eld.user_id = #{userId,jdbcType=BIGINT}\n" + 
				"		LEFT JOIN event_look_count elc 		on		elc.event_id = elist.id\n" + 
				"  </select>\n" + 
				"</mapper>";
		String rgex = "\\{(.*?)\\}";
//		System.out.println(getList(str, rgex));
		System.out.println(twoStringBetweenGreed(str, "resultMap>", "</mapper>"));
//		System.out.println(twoStringBetweenFrist(str, "\\{", "\\}"));
//		System.out.println("twoStringBetweenLast : " + twoStringBetweenLast(str, "\\{", "\\}"));
	}
}
