package com.luoqiz.code.test.service;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.entity.Example;
import com.luoqiz.code.test.entity.QrtzCalendarsEntity;

/**
 * @author luoqiz
 * @Date: 2018-12-29 11:58:56
 * 
 */
public interface QrtzCalendarsService {

    /**
     * @Title: saveQrtzCalendarsEntity 
     * @Description: 添加一条记录(操作所有字段)
     * @param QrtzCalendarsEntity
     * @return int
     * @throws Exception
     */
    int saveEntity(QrtzCalendarsEntity qrtzCalendars) throws Exception;

    /**
     * @Title: saveQrtzCalendarsEntitySelective      
     * @Description: 添加一条记录(只操作不为空的字段)
     * @param QrtzCalendarsEntity
     * @return int 
     * @throws Exception
     */
    int saveEntitySelective(QrtzCalendarsEntity qrtzCalendarsEntity) throws Exception;
    
    /**
     * @Title: saveListQrtzCalendarsEntity      
     * @Description: 批量添加记录(只操作不为空的字段)
     * @param QrtzCalendarsEntity
     * @return int 
     * @throws Exception
     */
    int saveListEntity(List<QrtzCalendarsEntity> qrtzCalendarsList) throws Exception;
    
    /**
     * @Title: countQrtzCalendarsEntityByExample      
     * @Description: 根据条件获取记录总数
     * @param QrtzCalendarsEntity
     * @return int
     * @throws Exception 
     */
    int countEntity(QrtzCalendarsEntity qrtzCalendarsEntity) throws Exception;
    
    /**
     * @Title: countQrtzCalendarsEntityByExample      
     * @Description: 根据条件获取记录总数
     * @param QrtzCalendarsEntity
     * @return int
     * @throws Exception 
     */
    int countEntityByExample(Example qrtzCalendarsEntityExample) throws Exception;
    
    /**
     * @Title: findByPrimaryKey  
     * @Description: 根据主键获取单条记录
     * @param QrtzCalendarsEntity
     * @return QrtzCalendarsEntity
     * @throws Exception
     */
    QrtzCalendarsEntity findByPrimaryKey(String schedName , String calendarName) throws Exception;
    
    /**
     * @Title: findListEntityByExample      
     * @Description: 根据条件获取记录
     * @param QrtzCalendarsEntityExample
     * @return List<QrtzCalendarsEntity> 
     * @throws Exception 
     */
    List<QrtzCalendarsEntity> findListEntityByExample(QrtzCalendarsEntity qrtzCalendars, int offset, int limit) throws Exception;
    
    /**
     * @Title: findListEntityByExample      
     * @Description: 根据条件获取记录
     * @param QrtzCalendarsEntityExample
     * @return List<QrtzCalendarsEntity> 
     * @throws Exception 
     */
    List<QrtzCalendarsEntity> findListEntity(QrtzCalendarsEntity qrtzCalendars, int offset, int limit) throws Exception;
    
    /**
     * @Title: removeQrtzCalendarsEntityByPrimaryKey     
     * @Description: 根据主键删除一条记录 
     * @param QrtzCalendarsEntity
     * @return int 
     * @throws Exception
     */
    int removeEntityByPrimaryKey(String schedName , String calendarName) throws Exception;
    
	/**
     * @Title: removeListByPrimaryKey
     * @Description: 根据主键批量删除记录 
     * @param QrtzCalendarsEntityArray
     * @return int 
     * @throws Exception
     */
    int removeListByPrimaryKey(List<Map<String,Object>> list) throws Exception;
    
    /**
     * @Title: removeByExample
     * @Description: 根据主键批量删除记录 
     * @param QrtzCalendarsEntityArray
     * @return int 
     * @throws Exception
     */
    int removeByExample(Example QrtzCalendarsEntityExample) throws Exception;
    
    /**
     * @Title: updateByPrimaryKey     
     * @Description: 根据主键更新一条记录（所有字段更新）
     * @param QrtzCalendarsEntity
     * @return int
     * @throws Exception
     */
    int updateByPrimaryKey(QrtzCalendarsEntity qrtzCalendars) throws Exception;
    
    /**
     * @Title: updateByPrimaryKeySelective     
     * @Description: 根据主键更新一条记录（不为空 的字段更新）
     * @param QrtzCalendarsEntity
     * @return int
     * @throws Exception
     */
    int updateByPrimaryKeySelective(QrtzCalendarsEntity qrtzCalendars) throws Exception;
    
}