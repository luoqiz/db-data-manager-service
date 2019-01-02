package ${serviceImplTargetPackage};

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import ${entityTargetPackage}.${tempTableInfo.className?cap_first}Entity;
import ${serviceTargetPackage}.${tempTableInfo.className?cap_first}Service;
import ${mapperTargetPackage}.${tempTableInfo.className?cap_first}Mapper;

/**
 * <#if author??>@author ${author}</#if>
 * @Date: ${.now}
 * 
 */
@Service
public class ${tempTableInfo.className?cap_first}ServiceImpl implements ${tempTableInfo.className?cap_first}Service{

    @Autowired
    private ${tempTableInfo.className?cap_first}Mapper ${tempTableInfo.className?uncap_first}Mapper;
    
    @Override 
    public int saveEntity(${tempTableInfo.className?cap_first}Entity ${tempTableInfo.className?uncap_first}) throws Exception{
        return ${tempTableInfo.className?uncap_first}Mapper.insert(${tempTableInfo.className?uncap_first});
    }
    
    @Override
    public int saveEntitySelective(${tempTableInfo.className?cap_first}Entity ${tempTableInfo.className?uncap_first}Entity) throws Exception{
        return ${tempTableInfo.className?uncap_first}Mapper.insertSelective(${tempTableInfo.className?uncap_first}Entity);
    }
    
    @Override
    public int saveListEntity(List<${tempTableInfo.className?cap_first}Entity> ${tempTableInfo.className?uncap_first}List) throws Exception{
        return ${tempTableInfo.className?uncap_first}Mapper.insertList(${tempTableInfo.className?uncap_first}List);
    }
    
    @Override
    public int countEntity(${tempTableInfo.className?cap_first}Entity ${tempTableInfo.className?uncap_first}List) throws Exception{
        return ${tempTableInfo.className?uncap_first}Mapper.selectCount(${tempTableInfo.className?uncap_first}List);
    }
    
    @Override
    public int countEntityByExample(Example ${tempTableInfo.className?cap_first}EntityExample) throws Exception{
        return ${tempTableInfo.className?uncap_first}Mapper.selectCountByExample(${tempTableInfo.className?cap_first}EntityExample);
    }

	@Override
    public ${tempTableInfo.className?cap_first}Entity findByPrimaryKey(<@compress single_line=true>
	<#-- 判断是否是第一个主键来决定是否添加逗号 -->
	<#assign index=0 />
	<#list tempTableInfo.columnInfoList as columnInfo>
		<#if columnInfo["primayKey"]==true>
		<#if index != 0>, </#if>
		${columnInfo["javaColumnType"]} ${columnInfo["javaColumnName"]}
		</#if>
		<#assign index=index+1 />
	</#list>
    </@compress>) throws Exception{
    
    	Map<String, Object> map = new HashMap<String, Object>();
	<#list tempTableInfo.columnInfoList as columnInfo>
		<#if columnInfo["primayKey"]==true>
		map.put("${columnInfo["javaColumnName"]}", ${columnInfo["javaColumnName"]});
		</#if>
	</#list>
        return ${tempTableInfo.className?uncap_first}Mapper.selectByPrimaryKey(map);
    }
    
    @Override
    public List<${tempTableInfo.className?cap_first}Entity> findListEntityByExample(${tempTableInfo.className?cap_first}Entity ${tempTableInfo.className?uncap_first}, int offset, int limit) throws Exception{
        Example ${tempTableInfo.className?uncap_first}Example = new Example(${tempTableInfo.className?cap_first}Entity.class);
        RowBounds rowBounds = new RowBounds(offset, limit);
        return ${tempTableInfo.className?uncap_first}Mapper.selectByExampleAndRowBounds(${tempTableInfo.className?uncap_first}Example, rowBounds);
    }
    
    @Override
    public List<${tempTableInfo.className?cap_first}Entity> findListEntity(${tempTableInfo.className?cap_first}Entity ${tempTableInfo.className?uncap_first}, int offset, int limit) throws Exception{
        RowBounds rowBounds = new RowBounds(offset, limit);
        return ${tempTableInfo.className?uncap_first}Mapper.selectByRowBounds(${tempTableInfo.className?uncap_first}, rowBounds);
    }
    
    @Override
    public int removeEntityByPrimaryKey(<@compress single_line=true>
	<#-- 判断是否是第一个主键来决定是否添加逗号 -->
	<#assign index=0 />
	<#list tempTableInfo.columnInfoList as columnInfo>
		<#if columnInfo["primayKey"]==true>
		<#if index != 0>, </#if>
		${columnInfo["javaColumnType"]} ${columnInfo["javaColumnName"]}
		</#if>
		<#assign index=index+1 />
	</#list>
    </@compress>) throws Exception{
    
    	Map<String, Object> map = new HashMap<String, Object>();
    <#list tempTableInfo.columnInfoList as columnInfo>
		<#if columnInfo["primayKey"]==true>
		map.put("${columnInfo["javaColumnType"]}", ${columnInfo["javaColumnName"]});
		</#if>
	</#list>
        return ${tempTableInfo.className?uncap_first}Mapper.deleteByPrimaryKey(map);
    }
    
    <#list tempTableInfo.columnInfoList as columnInfo>
    <#if columnInfo["primayKey"]==true>
	@Override
    public int removeListByPrimaryKey(List<Map<String,Object>> list) throws Exception{
        Example example = new Example(${tempTableInfo.className?cap_first}Entity.class); 
        //Criteria cc = example.createCriteria();
        //cc.andIn("${columnInfo["javaColumnName"]}", ${columnInfo["javaColumnName"]}List);
        return ${tempTableInfo.className?uncap_first}Mapper.deleteByExample(example);
    }
    <#break>
	</#if>
	</#list>
    
    @Override
    public int removeByExample(Example ${tempTableInfo.className?cap_first}EntityExample) throws Exception{
        return ${tempTableInfo.className?uncap_first}Mapper.deleteByExample(${tempTableInfo.className?cap_first}EntityExample);
    }
    
    @Override
    public int updateByPrimaryKey(${tempTableInfo.className?cap_first}Entity ${tempTableInfo.className?uncap_first}) throws Exception{
        return ${tempTableInfo.className?uncap_first}Mapper.updateByPrimaryKey(${tempTableInfo.className?uncap_first});
    }
    
    @Override
    public int updateByPrimaryKeySelective(${tempTableInfo.className?cap_first}Entity ${tempTableInfo.className?uncap_first}) throws Exception{
        return ${tempTableInfo.className?uncap_first}Mapper.updateByPrimaryKeySelective(${tempTableInfo.className?uncap_first});
    }
    
}