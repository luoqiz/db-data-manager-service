package ${controllerTargetPackage};

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ${entityTargetPackage}.${tempTableInfo.className?cap_first};
import ${serviceTargetPackage}.${tempTableInfo.className?cap_first}Service;
import com.luoqiz.db.config.ParamException;

<#if swaggerEnable==true>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>

<#if swaggerEnable==true>
@Api(tags = "${tempTableInfo.tableComment}")
</#if>
@RestController
@RequestMapping("/${tempTableInfo.className?uncap_first}")
public class ${tempTableInfo.className}Controller{

    @Autowired
    private ${tempTableInfo.className?cap_first}Service ${tempTableInfo.className?uncap_first}Service;
    
    <#if swaggerEnable==true>
	@ApiOperation(value = "全数据添加", notes = "对所有字段处理添加记录")
	</#if>
    @PostMapping(value = "/save")
    public int save${tempTableInfo.className}(@RequestBody ${tempTableInfo.className} ${tempTableInfo.className?uncap_first}) throws Exception {
    <#list tempTableInfo.columnInfoList as columnInfo>
	<#if columnInfo["nullValue"]==true>
		<#if columnInfo["javaColumnType"] != 'boolean'>
		if(${tempTableInfo.className?uncap_first}.get${columnInfo["javaColumnName"]?cap_first}() == null){
			throw new ParamException("${columnInfo["dbColumnName"]}", "${columnInfo["comment"]}不能为null");
		}
		</#if>
	</#if>
	</#list>
        return ${tempTableInfo.className?uncap_first}Service.saveEntity(${tempTableInfo.className?uncap_first});
    }
    
    <#if swaggerEnable==true>
	@ApiOperation(value = "部分数据添加", notes = "只对不为null的字段处理添加记录")
	</#if>
    @PostMapping(value = "/saveSelective")
    public int saveSelective(@RequestBody ${tempTableInfo.className} ${tempTableInfo.className?uncap_first}) throws Exception {
    <#list tempTableInfo.columnInfoList as columnInfo>
	<#if columnInfo["nullValue"]==true>
		<#if columnInfo["javaColumnType"] != 'boolean'>
		if(${tempTableInfo.className?uncap_first}.get${columnInfo["javaColumnName"]?cap_first}() == null){
			throw new ParamException("${columnInfo["dbColumnName"]}", "${columnInfo["comment"]}不能为null");
		}
		</#if>
	</#if>
	</#list>
        return ${tempTableInfo.className?uncap_first}Service.saveEntitySelective(${tempTableInfo.className?uncap_first});
    }
    
	<#if swaggerEnable==true>
	@ApiOperation(value = "全数据更新", notes = "根据主键更新一条记录（所有字段更新）")
	</#if>
    @PutMapping(value = "/update")
    public int updateAllFiled(@RequestBody ${tempTableInfo.className} ${tempTableInfo.className?uncap_first}) throws Exception{
        return ${tempTableInfo.className?uncap_first}Service.updateByPrimaryKey(${tempTableInfo.className?uncap_first});
    }
    
    <#if swaggerEnable==true>
	@ApiOperation(value = "部分数据更新", notes = "根据主键更新一条记录（所有不为null的字段更新）")
	</#if>
    @PatchMapping(value = "/update")
    public int updateFiled(@RequestBody ${tempTableInfo.className} ${tempTableInfo.className?uncap_first}) throws Exception{
        return ${tempTableInfo.className?uncap_first}Service.updateByPrimaryKeySelective(${tempTableInfo.className?uncap_first});
    }
    
    <#if swaggerEnable==true>
	@ApiOperation(value = "获取特定记录", notes = "根据主键获取单条记录")
	</#if>
   	@GetMapping(value = "/")
   	public ${tempTableInfo.className?cap_first} getByPrimaryKey(<@compress single_line=true>
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
    	return ${tempTableInfo.className?uncap_first}Service.findByPrimaryKey(<@compress single_line=true>
	<#-- 判断是否是第一个主键来决定是否添加逗号 -->
	<#assign index=0 />
	<#list tempTableInfo.columnInfoList as columnInfo>
		<#if columnInfo["primayKey"]==true>
		<#if index != 0>, </#if>
		${columnInfo["javaColumnName"]}
		</#if>
		<#assign index=index+1 />
	</#list>
    </@compress>);
   	}
	
    <#if swaggerEnable==true>
	@ApiOperation(value = "获取多条记录", notes = "根据条件获取多条记录")
	</#if>
    @GetMapping(value = "/list")
    public Map<String,Object> listEntity(${tempTableInfo.className} ${tempTableInfo.className?uncap_first}, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "20") int pageSize) throws Exception {
        if (pageNum < 1) {
			pageNum = 1;
		}
		if (pageSize < 1) {
			pageSize = 20;
		}
        int total = ${tempTableInfo.className?uncap_first}Service.countEntity(${tempTableInfo.className?uncap_first});
        List<${tempTableInfo.className}> rows = ${tempTableInfo.className?uncap_first}Service.findListEntity(${tempTableInfo.className?uncap_first}, (pageNum - 1) * pageSize, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", rows);
		return map;
    }
    
	<#if swaggerEnable==true>
	@ApiOperation(value = "删除特定数据", notes = "根据主键删除记录")
	</#if>
    @DeleteMapping(value = "/remove")
    public int removeByPrimaryKey(<@compress single_line=true>
	<#-- 判断是否是第一个主键来决定是否添加逗号 -->
	<#assign index=0 />
	<#list tempTableInfo.columnInfoList as columnInfo>
		<#if columnInfo["primayKey"]==true>
		<#if index != 0>, </#if>
		${columnInfo["javaColumnType"]} ${columnInfo["javaColumnName"]}
		</#if>
		<#assign index=index+1 />
	</#list>
    </@compress>) throws Exception {
        return ${tempTableInfo.className?uncap_first}Service.removeEntityByPrimaryKey(<@compress single_line=true>
	<#-- 判断是否是第一个主键来决定是否添加逗号 -->
	<#assign index=0 />
	<#list tempTableInfo.columnInfoList as columnInfo>
		<#if columnInfo["primayKey"]==true>
		<#if index != 0>, </#if>
		${columnInfo["javaColumnName"]}
		</#if>
		<#assign index=index+1 />
	</#list>
    </@compress>);
    }
    
}