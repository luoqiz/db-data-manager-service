package com.luoqiz.db.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ConvertUtils {

	private static Pattern humpPattern = Pattern.compile("[A-Z]");

	/**
	 * 指定符号转大写
	 * 
	 * @param param      字符串
	 * @param replace    符号
	 * @param smallCamel 首字母是否处理
	 * @return
	 */
	public static String camelCase(String param, char replace, boolean smallCamel) {
		if (StringUtils.isBlank(param)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		String regString = String.format("([A-Za-z\\d]+)(%s)?", replace);
		Pattern pattern = Pattern.compile(regString);
		Matcher matcher = pattern.matcher(param);
		while (matcher.find()) {
			String word = matcher.group();
			sb.append(smallCamel && matcher.start() == 0 ? Character.toLowerCase(word.charAt(0))
					: Character.toUpperCase(word.charAt(0)));
			int index = word.lastIndexOf(replace);
			if (index > 0) {
				sb.append(word.substring(1, index).toLowerCase());
			} else {
				sb.append(word.substring(1).toLowerCase());
			}
		}
		return sb.toString();
	}

	/**
	 * 大写转指定符号
	 * 
	 * @param param      字符串
	 * @param replace    符号
	 * @param smallCamel 首字母是否处理
	 * @return
	 */
	public static String humpToSymbol(String param, char replace, boolean smallCamel) {
		if (StringUtils.isBlank(param)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		String regString = String.format("[A-Z]", replace);
		Pattern pattern = Pattern.compile(regString);
		Matcher matcher = pattern.matcher(param);
		while (matcher.find()) {
			if (matcher.start() != 0) {
				matcher.appendReplacement(sb, replace + matcher.group(0).toLowerCase());
			} else {
				if (smallCamel) {
					matcher.appendReplacement(sb, replace + matcher.group(0).toLowerCase());
				}
			}
		}
		matcher.appendTail(sb);
		return sb.toString();

	}

	public static String capFirst(String str) {
		if (StringUtils.isNotBlank(str)) {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}
		return str;
	}

	public static String uncapFirst(String str) {
		if (StringUtils.isNotBlank(str)) {
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		}
		return str;
	}

	public static void main(String[] args) {
//		System.out.println(camelCase("a_sldfj_bjio", '_', true));
		System.out.println(humpToSymbol("ASldfjBjio", '_', true));
		System.out.println(uncapFirst("ASldfjBjio"));
		System.out.println(capFirst("aSldfjBjio"));
	}
}
