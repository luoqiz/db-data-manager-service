package ${entityTargetPackage};

<#if swaggerEnable==true>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>

<#-- 定义是否引入时间类型变量 -->
<#assign dateutil=false />
<#list tempTableInfo.columnInfoList as columnInfo>
<#if columnInfo["javaColumnType"]== 'Date'>
	<#assign dateutil=true />
</#if>
</#list>

<#if dateutil== true>
import java.util.Date;
</#if>


<#if lombokEnable==true>
import lombok.Data;
</#if>
<#if tableAnnotation==true >
import javax.persistence.*;
</#if>

/**
 * <#if author??>@author ${author}</#if>
 * @Date: ${.now}
 * 
 */
<#if swaggerEnable==true>
@ApiModel
</#if>
<#if lombokEnable==true>
@Data
</#if>
<#if tableAnnotation==true>
@Table(name = "${tempTableInfo.tableName}")
</#if>
public class ${tempTableInfo.className?cap_first} {
	<#list tempTableInfo.columnInfoList as columnInfo>
	
	<#if columnInfo["comment"]?? && columnInfo["comment"] != "">
	/**
	 * ${columnInfo["comment"]}
	 */	
	</#if>
	<#if swaggerEnable==true>
	@ApiModelProperty(value="${columnInfo["comment"]}",position = ${columnInfo["fieldNum"]})	
	</#if>
	<#if tableAnnotation==true >
	<#if columnInfo["primayKey"]==true>
	@Id
	</#if>
	@Column(name = "${columnInfo["dbColumnName"]}")
	</#if>
	private ${columnInfo["javaColumnType"]?cap_first} ${columnInfo["javaColumnName"]};	
	</#list>
	<#if lombokEnable!=true>
	<#list tempTableInfo.columnInfoList as columnInfo>
	
	public void set${columnInfo["javaColumnName"]?cap_first}(${columnInfo["javaColumnType"]} ${columnInfo["javaColumnName"]}) {
        <#if columnInfo["javaColumnType"]=='String'>
         this.${columnInfo["javaColumnName"]} = ${columnInfo["javaColumnName"]} == null ? null : ${columnInfo["javaColumnName"]}.trim();
        <#else>
        this.${columnInfo["javaColumnName"]} = ${columnInfo["javaColumnName"]};
        </#if>
    }
    
	public ${columnInfo["javaColumnType"]} get${columnInfo["javaColumnName"]?cap_first}(){
		return ${columnInfo["javaColumnName"]};
	}
	</#list>
	</#if>
}