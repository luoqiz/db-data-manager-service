package com.luoqiz.db.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ConvertUtils {

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

	public static void main(String[] args) {
		System.out.println(camelCase("a_sldfj_bjio", '_',true));
	}
}
