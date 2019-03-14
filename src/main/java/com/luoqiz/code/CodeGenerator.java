package com.luoqiz.code;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.luoqiz.code.entity.ColumnInfo;
import com.luoqiz.code.entity.DatabaseInfo;
import com.luoqiz.code.entity.TableInfo;
import com.luoqiz.code.generator.CodeGenerationConfiguration;
import com.luoqiz.db.connect.DBmanager;
import com.luoqiz.db.connect.DbManagerFactory;
import com.luoqiz.db.util.RegexUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeGenerator {

	public static String encoding = "UTF-8";

	private CodeGenerationConfiguration cGCfg;

	public CodeGenerator(CodeGenerationConfiguration codeGenerationConfiguration) {
		this.cGCfg = codeGenerationConfiguration;
	}

	public static void main(String[] args) {

		CodeGenerationConfiguration cgdfg = new CodeGenerationConfiguration();
//		cgdfg.setTableMatch("^qrtz.*");
//		cgdfg.setTableMatch("event_record");
//		cgdfg.setTableMatch(".*");
		cgdfg.setTableMatch("event_opinion_evaluation");
		cgdfg.setAuthor("luoqiz");

		cgdfg.setEntityTargetPackage("com.boya.db.domain");
		cgdfg.setMapperTargetPackage("com.boya.db.mapper");
		cgdfg.setMapperExtendsClass("com.boya.db.config.MybatisMapper");
		cgdfg.setSqlMapperTargetProject("src/main/resources");
		cgdfg.setSqlMapperTargetPackage("sqlMapper/sys");
		cgdfg.setTableAnnotation(true);
		cgdfg.setServiceTargetPackage("com.luoqiz.code.test.service");
		cgdfg.setServiceImplTargetPackage("com.luoqiz.code.test.serviceImpl");
		cgdfg.setControllerTargetPackage("com.luoqiz.code.test.controller");

		cgdfg.setLombokEnable(true);
		cgdfg.setSwaggerEnable(true);

		// 数据库信息
		DatabaseInfo dbInfo = new DatabaseInfo();
		dbInfo.setDbAddr("180.76.237.66");
		dbInfo.setDbName("opnion_monitor");
		dbInfo.setDbPort(3306);
		dbInfo.setDbType("mysql");
		dbInfo.setDbUserName("root");
		dbInfo.setDbPassword("!qaz2wsX");

		CodeGenerator cg = new CodeGenerator(cgdfg);
		cg.generator(dbInfo);
	}

	public void generator(DatabaseInfo dbInfo) {
		DBmanager dbmanager = DbManagerFactory.getManager(dbInfo);
		Connection con = dbmanager.getConnect(dbInfo);
		log.info(cGCfg.getTableMatch());
		if (cGCfg.getTableMatch() != null) {
			// 获取所有的表
			List<TableInfo> tableInfoList = dbmanager.getTableList(con, dbInfo.getDbName());
			tableInfoList.forEach(tableInfo -> {
				boolean isMatcher = tableInfo.getTableName().matches(cGCfg.getTableMatch());
				log.info(tableInfo.getTableName() + " 匹配成功：" + isMatcher);
				// 字符串是否与正则表达式相匹配
				if (isMatcher) {
					List<ColumnInfo> result = dbmanager.getColumnByTable(con, dbInfo.getDbName(),
							tableInfo.getTableName());
					tableInfo.setColumnInfoList(result);
					cGCfg.getTableInfoList().add(tableInfo);
				}
			});
		}

		dbmanager.closeConnect(con);

		// 创建Freemarker配置实例
		Configuration cfg = new Configuration(Configuration.getVersion());
		try {
			// 项目路径
			String proBin = this.getClass().getResource("/").getFile();
			String packageName = "";
//			cfg.setWhitespaceStripping(true);
			cfg.setDirectoryForTemplateLoading(
					new File(proBin + "/" + packageName.replaceAll("\\.", "/") + "/com/luoqiz/code/template/"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (StringUtils.isNoneEmpty(cGCfg.getEntityTargetPackage())) {
			createEntity(cfg);
		}

		if (StringUtils.isNoneEmpty(cGCfg.getMapperTargetPackage())) {
			createMapper(cfg);
		}

		if (StringUtils.isNoneEmpty(cGCfg.getSqlMapperTargetPackage())) {
			createSqlMapper(cfg);
		}

		if (StringUtils.isNoneEmpty(cGCfg.getServiceTargetPackage())) {
			createService(cfg);
		}

		if (StringUtils.isNoneEmpty(cGCfg.getServiceImplTargetPackage())) {
			createServiceImpl(cfg);
		}

		if (StringUtils.isNoneEmpty(cGCfg.getControllerTargetPackage())) {
			createController(cfg);
		}
	}

	/**
	 * 生成controller类
	 * 
	 * @param cfg
	 * @return
	 */
	private boolean createController(Configuration cfg) {
		cGCfg.getTableInfoList().forEach(tableInfo -> {
			try {
				// 加载模板文件
				Template t = null;
				BufferedWriter rd = null;
				OutputStream out = null;
				t = cfg.getTemplate(cGCfg.getControllerTargetProject() == null ? "controller.ftl"
						: cGCfg.getControllerTemplatePath());
				// mapper接口类路径
				String pathString = (cGCfg.getControllerTargetProject() == null ? cGCfg.getBaseTargetProject()
						: cGCfg.getControllerTargetProject()) + "/"
						+ cGCfg.getControllerTargetPackage().replaceAll("\\.", "/");
				File pojoFile = new File(pathString);
				if (!pojoFile.exists()) {
					pojoFile.mkdirs();
				}
				File file = new File(pojoFile.getAbsolutePath() + "/" + tableInfo.getClassName() + "Controller.java");
				// 文件已存在则读取出来放到新建的文件中
				if (file.exists()) {
					Long filelength = file.length();
					byte[] filecontent = new byte[filelength.intValue()];
					FileInputStream in = new FileInputStream(file);
					in.read(filecontent);
					in.close();
					tableInfo.setTempStr(RegexUtils.twoStringBetweenGreed(new String(filecontent, encoding).toString(),
							"\\{", "\\}"));
				}
				out = new FileOutputStream(file);
				rd = new BufferedWriter(new OutputStreamWriter(out, encoding));
				cGCfg.setTempTableInfo(tableInfo);
				t.process(cGCfg, rd);
				rd.close();
				out.flush();
				out.close();
				log.info(tableInfo.getClassName() + "Controller.java 文件所在路径：" + file.getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}

		});

		return true;
	}

	/**
	 * 生成service接口实现类
	 * 
	 * @param cfg
	 * @return
	 */
	private boolean createServiceImpl(Configuration cfg) {
		cGCfg.getTableInfoList().forEach(tableInfo -> {
			try {
				// 加载模板文件
				Template t = null;
				BufferedWriter rd = null;
				OutputStream out = null;
				t = cfg.getTemplate(cGCfg.getServiceImplTargetProject() == null ? "serviceImpl.ftl"
						: cGCfg.getServiceImplTemplatePath());
				// mapper接口类路径
				String pathString = (cGCfg.getServiceImplTargetProject() == null ? cGCfg.getBaseTargetProject()
						: cGCfg.getServiceImplTargetProject()) + "/"
						+ cGCfg.getServiceImplTargetPackage().replaceAll("\\.", "/");
				File pojoFile = new File(pathString);
				if (!pojoFile.exists()) {
					pojoFile.mkdirs();
				}
				File file = new File(pojoFile.getAbsolutePath() + "/" + tableInfo.getClassName() + "ServiceImpl.java");
				// 文件已存在则读取出来放到新建的文件中
				if (file.exists()) {
					Long filelength = file.length();
					byte[] filecontent = new byte[filelength.intValue()];
					FileInputStream in = new FileInputStream(file);
					in.read(filecontent);
					in.close();
					tableInfo.setTempStr(RegexUtils.twoStringBetweenGreed(new String(filecontent, encoding).toString(),
							"\\{", "\\}"));
				}
				out = new FileOutputStream(file);
				rd = new BufferedWriter(new OutputStreamWriter(out, encoding));
				cGCfg.setTempTableInfo(tableInfo);
				t.process(cGCfg, rd);
				rd.close();
				out.flush();
				out.close();
				log.info(tableInfo.getClassName() + "ServiceImpl.java 实现类文件所在路径：" + file.getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}

		});

		return true;

	}

	/**
	 * 生成service接口类
	 * 
	 * @param cfg
	 * @return
	 */
	private boolean createService(Configuration cfg) {
		cGCfg.getTableInfoList().forEach(tableInfo -> {
			try {
				// 加载模板文件
				Template t = null;
				BufferedWriter rd = null;
				OutputStream out = null;
				t = cfg.getTemplate("service.ftl");
				// mapper接口类路径
				String pathString = (cGCfg.getServiceTargetProject() == null ? cGCfg.getBaseTargetProject()
						: cGCfg.getServiceTargetProject()) + "/"
						+ cGCfg.getServiceTargetPackage().replaceAll("\\.", "/");
				File pojoFile = new File(pathString);
				if (!pojoFile.exists()) {
					pojoFile.mkdirs();
				}
				File file = new File(pojoFile.getAbsolutePath() + "/" + tableInfo.getClassName() + "Service.java");
				// 文件已存在则读取出来放到新建的文件中
				if (file.exists()) {
					Long filelength = file.length();
					byte[] filecontent = new byte[filelength.intValue()];
					FileInputStream in = new FileInputStream(file);
					in.read(filecontent);
					in.close();
					tableInfo.setTempStr(RegexUtils.twoStringBetweenGreed(new String(filecontent, encoding).toString(),
							"\\{", "\\}"));
				}
				out = new FileOutputStream(file);
				rd = new BufferedWriter(new OutputStreamWriter(out, encoding));
				cGCfg.setTempTableInfo(tableInfo);
				t.process(cGCfg, rd);
				rd.close();
				out.flush();
				out.close();
				log.info("{}Service.java 接口文件所在路径：{}", tableInfo.getClassName(), file.getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}

		});

		return true;
	}

	/**
	 * 生成sqlMapper文件
	 * 
	 * @param cfg
	 * @return
	 */
	private boolean createSqlMapper(Configuration cfg) {
		cGCfg.getTableInfoList().forEach(tableInfo -> {
			try {
				// 加载模板文件
				Template t = null;
				BufferedWriter rd = null;
				OutputStream out = null;
				t = cfg.getTemplate("sqlMapper.ftl");
				// mapper接口类路径
				String pathString = (cGCfg.getSqlMapperTargetProject() == null ? cGCfg.getBaseTargetProject()
						: cGCfg.getSqlMapperTargetProject()) + "/"
						+ cGCfg.getSqlMapperTargetPackage().replaceAll("\\.", "/");
				File pojoFile = new File(pathString);
				if (!pojoFile.exists()) {
					pojoFile.mkdirs();
				}
				File file = new File(pojoFile.getAbsolutePath() + "/" + tableInfo.getClassName() + "Mapper.xml");
				// 文件已存在则读取出来放到新建的文件中
				if (file.exists()) {
					Long filelength = file.length();
					byte[] filecontent = new byte[filelength.intValue()];
					FileInputStream in = new FileInputStream(file);
					in.read(filecontent);
					in.close();
					tableInfo.setTempStr(RegexUtils.twoStringBetweenGreed(new String(filecontent, encoding).toString(),
							"resultMap>", "</mapper>"));
				}
				out = new FileOutputStream(file);
				rd = new BufferedWriter(new OutputStreamWriter(out, encoding));
				cGCfg.setTempTableInfo(tableInfo);
				t.process(cGCfg, rd);
				rd.close();
				out.flush();
				out.close();
				log.info("{}Mapper.xml 文件所在路径：{}", tableInfo.getClassName(), file.getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}

		});

		return true;

	}

	/**
	 * 生成mapper接口类
	 * 
	 * @param cfg
	 * @return
	 */
	private boolean createMapper(Configuration cfg) {
		cGCfg.getTableInfoList().forEach(tableInfo -> {
			try {
				// 加载模板文件
				Template t = null;
				BufferedWriter rd = null;
				OutputStream out = null;
				t = cfg.getTemplate("mapper.ftl");
				// mapper接口类路径
				String pathString = (cGCfg.getMapperTargetProject() == null ? cGCfg.getBaseTargetProject()
						: cGCfg.getMapperTargetProject()) + "/" + cGCfg.getMapperTargetPackage().replaceAll("\\.", "/");
				File pojoFile = new File(pathString);
				if (!pojoFile.exists()) {
					pojoFile.mkdirs();
				}
				File file = new File(pojoFile.getAbsolutePath() + "/" + tableInfo.getClassName() + "Mapper.java");
				// 文件已存在则读取出来放到新建的文件中
				if (file.exists()) {
					Long filelength = file.length();
					byte[] filecontent = new byte[filelength.intValue()];
					FileInputStream in = new FileInputStream(file);
					in.read(filecontent);
					in.close();
					tableInfo.setTempStr(RegexUtils.twoStringBetweenGreed(new String(filecontent, encoding).toString(),
							"\\{", "\\}"));
				}
				out = new FileOutputStream(file);
				rd = new BufferedWriter(new OutputStreamWriter(out, encoding));
				cGCfg.setTempTableInfo(tableInfo);
				t.process(cGCfg, rd);
				rd.close();
				out.flush();
				out.close();
				log.info("{}Mapper.java文件所在路径：{}", tableInfo.getClassName(), file.getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}

		});

		return true;
	}

	/**
	 * 生成实体类
	 * 
	 * @param cfg
	 * @return
	 */
	private boolean createEntity(Configuration cfg) {
		cGCfg.getTableInfoList().forEach(tableInfo -> {
			try {
				// 加载模板文件
				Template t = null;
				BufferedWriter rd = null;
				OutputStream out = null;
				t = cfg.getTemplate("entity.ftl");
				// 实体类路径
				String pathString = (cGCfg.getEntityTargetProject() == null ? cGCfg.getBaseTargetProject()
						: cGCfg.getEntityTargetProject()) + "/" + cGCfg.getEntityTargetPackage().replaceAll("\\.", "/");
				File pojoFile = new File(pathString);
				if (!pojoFile.exists()) {
					pojoFile.mkdirs();
				}
				File file = new File(pojoFile.getAbsolutePath() + "/" + tableInfo.getClassName() + ".java");
				out = new FileOutputStream(file);
				rd = new BufferedWriter(new OutputStreamWriter(out, encoding));
				cGCfg.setTempTableInfo(tableInfo);
				t.process(cGCfg, rd);
				rd.close();
				out.flush();
				out.close();
				log.info("{}.java 文件所在路径：{}", tableInfo.getClassName(), file.getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}

		});

		return true;
	}

}
