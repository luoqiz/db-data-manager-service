<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${mapperTargetPackage}.${tempTableInfo.className}Mapper">
    <resultMap id="BaseResultMap" type="${entityTargetPackage}.${tempTableInfo.className?cap_first}">
    <#list tempTableInfo.columnInfoList as columnInfo>
	<#if columnInfo["primayKey"]==true>
		<id column="${columnInfo["dbColumnName"]}" jdbcType="${columnInfo["dbColumnType"]?upper_case}" property="${columnInfo["javaColumnName"]}" />
	<#else>
      	<result column="${columnInfo["dbColumnName"]}" jdbcType="${columnInfo["dbColumnType"]?upper_case}" property="${columnInfo["javaColumnName"]}" />
	</#if>
	</#list>
    </resultMap>
    <#if tempTableInfo.tempStr?? && tempTableInfo.tempStr!="">
	${tempTableInfo.tempStr}
	</#if>
</mapper>