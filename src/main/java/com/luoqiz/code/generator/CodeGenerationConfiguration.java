package com.luoqiz.code.generator;

import java.util.ArrayList;

import com.luoqiz.code.entity.TableInfo;

import lombok.Data;

@Data
public class CodeGenerationConfiguration {

	String baseTargetProject = "src/main/java";

	// 数据库信息
	private TableInfo tempTableInfo;

	// 表信息
	private ArrayList<TableInfo> tableInfoList = new ArrayList<TableInfo>();

	private String tableMatch;

	// 设置当前作者
	private String author;

	// 生成包结构是否添加表名
	private boolean tablePackage = false;

	// swagger2配置
	private boolean swaggerEnable;

	// 是否使用lombok
	private boolean lombokEnable;
	// 使用lombok時在类上添加的注解
	private String[] lombokAnnotation;

	// 使用jpa方式在类上添加注解
	private boolean tableAnnotation;
	// 实体类项目路径
	private String entityTargetProject;
	// 实体类包路径
	private String entityTargetPackage;
	// mapper接口模板路径
	private String entityTemplatePath;

	// mapper接口继承的类
	private String mapperExtendsClass;
	// mapper接口项目路径
	private String mapperTargetProject;
	// mapper接口包路径
	private String mapperTargetPackage;
	// mapper接口模板路径
	private String mapperTemplatePath;

	// sqlMap项目路径
	private String sqlMapperTargetProject;
	// sqlMap包路径
	private String sqlMapperTargetPackage;
	// sqlMap接口模板路径
	private String sqlMapperTemplatePath;

	// service接口项目路径
	private String serviceTargetProject;
	// service接口包路径
	private String serviceTargetPackage;
	// service接口模板路径
	private String serviceTemplatePath;
	
	// service接口项目路径
	private String serviceImplTargetProject;
	// service接口实现类包路径
	private String serviceImplTargetPackage;
	// service接口实现类模板路径
	private String serviceImplTemplatePath;

	// controller项目路径
	private String controllerTargetProject;
	// controller包路径
	private String controllerTargetPackage;
	// controller模板路径
	private String controllerTemplatePath;
}
