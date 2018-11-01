package com.luoqiz.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luoqiz.db.http.ApiServiceBase;
import com.luoqiz.db.http.HttpResponseBase;
import com.luoqiz.db.model.DbInfo;
import com.luoqiz.db.service.DbService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="数据库操作")
@RestController
@RequestMapping(value="/db")
public class DbController {

	@Autowired
	private DbService dbService;
	
	@ApiOperation(value = "获取表")
	@GetMapping(value="/getTableList")
	private HttpResponseBase getTableList(DbInfo dbInfo) {
		System.out.println(dbInfo.toString());
		return ApiServiceBase.setSuccess(this.dbService.findTableList(dbInfo));
	}
	
	@ApiOperation(value="获取表的列")
	@GetMapping(value="/getColumnByTable")
	private HttpResponseBase getColumnByTable(DbInfo dbInfo) {
		System.out.println(dbInfo.toString());
		return ApiServiceBase.setSuccess(this.dbService.findColumnByTable(dbInfo));
	}
	
	@ApiOperation(value="获取表的数据")
	@GetMapping(value="/getDataByDbInfo")
	private HttpResponseBase getDataByDbInfo(DbInfo dbInfo) {
		System.out.println(dbInfo.toString());
		return ApiServiceBase.setSuccess(this.dbService.findDataByDbInfo(dbInfo));
	}
}
