package ${serviceTargetPackage};

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.entity.Example;
import ${entityTargetPackage}.${tempTableInfo.className?cap_first}Entity;

/**
 * <#if author??>@author ${author}</#if>
 * @Date: ${.now}
 * 
 */
public interface ${tempTableInfo.className?cap_first}Service {

    /**
     * @Title: save${tempTableInfo.className?cap_first}Entity 
     * @Description: 添加一条记录(操作所有字段)
     * @param ${tempTableInfo.className?cap_first}Entity
     * @return int
     * @throws Exception
     */
    int saveEntity(${tempTableInfo.className?cap_first}Entity ${tempTableInfo.className?uncap_first}) throws Exception;

    /**
     * @Title: save${tempTableInfo.className?cap_first}EntitySelective      
     * @Description: 添加一条记录(只操作不为空的字段)
     * @param ${tempTableInfo.className?cap_first}Entity
     * @return int 
     * @throws Exception
     */
    int saveEntitySelective(${tempTableInfo.className?cap_first}Entity ${tempTableInfo.className?uncap_first}Entity) throws Exception;
    
    /**
     * @Title: saveList${tempTableInfo.className?cap_first}Entity      
     * @Description: 批量添加记录(只操作不为空的字段)
     * @param ${tempTableInfo.className?cap_first}Entity
     * @return int 
     * @throws Exception
     */
    int saveListEntity(List<${tempTableInfo.className?cap_first}Entity> ${tempTableInfo.className?uncap_first}List) throws Exception;
    
    /**
     * @Title: count${tempTableInfo.className?cap_first}EntityByExample      
     * @Description: 根据条件获取记录总数
     * @param ${tempTableInfo.className?cap_first}Entity
     * @return int
     * @throws Exception 
     */
    int countEntity(${tempTableInfo.className?cap_first}Entity ${tempTableInfo.className?uncap_first}Entity) throws Exception;
    
    /**
     * @Title: count${tempTableInfo.className?cap_first}EntityByExample      
     * @Description: 根据条件获取记录总数
     * @param ${tempTableInfo.className?cap_first}Entity
     * @return int
     * @throws Exception 
     */
    int countEntityByExample(Example ${tempTableInfo.className?uncap_first}EntityExample) throws Exception;
    
    /**
     * @Title: findByPrimaryKey  
     * @Description: 根据主键获取单条记录
     * @param ${tempTableInfo.className?cap_first}Entity
     * @return ${tempTableInfo.className?cap_first}Entity
     * @throws Exception
     */
    ${tempTableInfo.className?cap_first}Entity findByPrimaryKey(<@compress single_line=true>
	<#-- 判断是否是第一个主键来决定是否添加逗号 -->
	<#assign index=0 />
	<#list tempTableInfo.columnInfoList as columnInfo>
		<#if columnInfo["primayKey"]==true>
		<#if index != 0>, </#if>
		${columnInfo["javaColumnType"]} ${columnInfo["javaColumnName"]}
		</#if>
		<#assign index=index+1 />
	</#list>
    </@compress>) throws Exception;
    
    /**
     * @Title: findListEntityByExample      
     * @Description: 根据条件获取记录
     * @param ${tempTableInfo.className?cap_first}EntityExample
     * @return List<${tempTableInfo.className?cap_first}Entity> 
     * @throws Exception 
     */
    List<${tempTableInfo.className?cap_first}Entity> findListEntityByExample(${tempTableInfo.className?cap_first}Entity ${tempTableInfo.className?uncap_first}, int offset, int limit) throws Exception;
    
    /**
     * @Title: findListEntityByExample      
     * @Description: 根据条件获取记录
     * @param ${tempTableInfo.className?cap_first}EntityExample
     * @return List<${tempTableInfo.className?cap_first}Entity> 
     * @throws Exception 
     */
    List<${tempTableInfo.className?cap_first}Entity> findListEntity(${tempTableInfo.className?cap_first}Entity ${tempTableInfo.className?uncap_first}, int offset, int limit) throws Exception;
    
    /**
     * @Title: remove${tempTableInfo.className?cap_first}EntityByPrimaryKey     
     * @Description: 根据主键删除一条记录 
     * @param ${tempTableInfo.className?cap_first}Entity
     * @return int 
     * @throws Exception
     */
    int removeEntityByPrimaryKey(<@compress single_line=true>
	<#-- 判断是否是第一个主键来决定是否添加逗号 -->
	<#assign index=0 />
	<#list tempTableInfo.columnInfoList as columnInfo>
		<#if columnInfo["primayKey"]==true>
		<#if index != 0>, </#if>
		${columnInfo["javaColumnType"]} ${columnInfo["javaColumnName"]}
		</#if>
		<#assign index=index+1 />
	</#list>
    </@compress>) throws Exception;
    
	/**
     * @Title: removeListByPrimaryKey
     * @Description: 根据主键批量删除记录 
     * @param ${tempTableInfo.className?cap_first}EntityArray
     * @return int 
     * @throws Exception
     */
    int removeListByPrimaryKey(List<Map<String,Object>> list) throws Exception;
    
    /**
     * @Title: removeByExample
     * @Description: 根据主键批量删除记录 
     * @param ${tempTableInfo.className?cap_first}EntityArray
     * @return int 
     * @throws Exception
     */
    int removeByExample(Example ${tempTableInfo.className?cap_first}EntityExample) throws Exception;
    
    /**
     * @Title: updateByPrimaryKey     
     * @Description: 根据主键更新一条记录（所有字段更新）
     * @param ${tempTableInfo.className?cap_first}Entity
     * @return int
     * @throws Exception
     */
    int updateByPrimaryKey(${tempTableInfo.className?cap_first}Entity ${tempTableInfo.className?uncap_first}) throws Exception;
    
    /**
     * @Title: updateByPrimaryKeySelective     
     * @Description: 根据主键更新一条记录（不为空 的字段更新）
     * @param ${tempTableInfo.className?cap_first}Entity
     * @return int
     * @throws Exception
     */
    int updateByPrimaryKeySelective(${tempTableInfo.className?cap_first}Entity ${tempTableInfo.className?uncap_first}) throws Exception;
    
}