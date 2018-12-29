package com.luoqiz.code.test.controller;

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

import com.luoqiz.code.test.entity.QrtzCalendarsEntity;
import com.luoqiz.code.test.service.QrtzCalendarsService;
import com.luoqiz.db.config.ParamException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api()
@RestController
@RequestMapping("/qrtzCalendars")
public class QrtzCalendarsController{

    @Autowired
    private QrtzCalendarsService qrtzCalendarsService;
    
	@ApiOperation(value = "全数据添加", notes = "对所有字段处理添加记录")
    @PostMapping(value = "/save")
    public int saveQrtzCalendarsEntity(@RequestBody QrtzCalendarsEntity qrtzCalendars) throws Exception {
		if(qrtzCalendars.getCalendar() == null){
			throw new ParamException("CALENDAR", "不能为null");
		}
        return qrtzCalendarsService.saveEntity(qrtzCalendars);
    }
    
	@ApiOperation(value = "部分数据添加", notes = "只对不为null的字段处理添加记录")
    @PostMapping(value = "/saveSelective")
    public int saveSelective(QrtzCalendarsEntity qrtzCalendars) throws Exception {
		if(qrtzCalendars.getCalendar() == null){
			throw new ParamException("CALENDAR", "不能为null");
		}
        return qrtzCalendarsService.saveEntitySelective(qrtzCalendars);
    }
    
	@ApiOperation(value = "全数据更新", notes = "根据主键更新一条记录（所有字段更新）")
    @PutMapping(value = "/update")
    public int updateAllFiled(QrtzCalendarsEntity qrtzCalendars) throws Exception{
        return qrtzCalendarsService.updateByPrimaryKey(qrtzCalendars);
    }
    
	@ApiOperation(value = "部分数据更新", notes = "根据主键更新一条记录（所有不为null的字段更新）")
    @PatchMapping(value = "/update")
    public int updateFiled(QrtzCalendarsEntity qrtzCalendars) throws Exception{
        return qrtzCalendarsService.updateByPrimaryKeySelective(qrtzCalendars);
    }
    
	@ApiOperation(value = "获取特定记录", notes = "根据主键获取单条记录")
   	@GetMapping(value = "/")
   	public QrtzCalendarsEntity getByPrimaryKey(String schedName , String calendarName) throws Exception{
    	return qrtzCalendarsService.findByPrimaryKey(schedName , calendarName);
   	}
	
	@ApiOperation(value = "获取多条记录", notes = "根据条件获取多条记录")
    @GetMapping(value = "/list")
    public Map<String,Object> listEntity(QrtzCalendarsEntity qrtzCalendars, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "20") int pageSize) throws Exception {
        if (pageNum < 1) {
			pageNum = 1;
		}
		if (pageSize < 1) {
			pageSize = 20;
		}
        int total = qrtzCalendarsService.countEntity(qrtzCalendars);
        List<QrtzCalendarsEntity> rows = qrtzCalendarsService.findListEntity(qrtzCalendars, (pageNum - 1) * pageSize, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", rows);
		return map;
    }
    
	@ApiOperation(value = "删除特定数据", notes = "根据主键删除记录")
    @DeleteMapping(value = "/remove")
    public int removeByPrimaryKey(String schedName , String calendarName) throws Exception {
        return qrtzCalendarsService.removeEntityByPrimaryKey(schedName , calendarName);
    }
    
}