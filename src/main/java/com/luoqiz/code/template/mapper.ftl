package ${mapperTargetPackage};

<#if mapperExtendsClass?? && mapperExtendsClass!=''>
import ${mapperExtendsClass};
import ${entityTargetPackage}.${tempTableInfo.className?cap_first}Entity;

/**
 * <#if author??>@author ${author}</#if>
 * @Date: ${.now}
 * 
 */
<#list mapperExtendsClass?split(".") as word>
    <#if word_has_next>
	<#else>
public interface ${tempTableInfo.className?cap_first}Mapper extends ${word}<${tempTableInfo.className?cap_first}Entity> {
	</#if>
</#list>
<#else>
public interface ${tempTableInfo.className?cap_first}Mapper{
</#if>

}