package com.luoqiz.code.test.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import com.luoqiz.code.test.entity.QrtzCalendarsEntity;
import com.luoqiz.code.test.service.QrtzCalendarsService;
import com.luoqiz.code.test.mapper.QrtzCalendarsMapper;

/**
 * @author luoqiz
 * @Date: 2018-12-29 11:58:56
 * 
 */
@Service
public class QrtzCalendarsServiceImpl implements QrtzCalendarsService{

    @Autowired
    private QrtzCalendarsMapper qrtzCalendarsMapper;
    
    @Override 
    public int saveEntity(QrtzCalendarsEntity qrtzCalendars) throws Exception{
        return qrtzCalendarsMapper.insert(qrtzCalendars);
    }
    
    @Override
    public int saveEntitySelective(QrtzCalendarsEntity qrtzCalendarsEntity) throws Exception{
        return qrtzCalendarsMapper.insertSelective(qrtzCalendarsEntity);
    }
    
    @Override
    public int saveListEntity(List<QrtzCalendarsEntity> qrtzCalendarsList) throws Exception{
        return qrtzCalendarsMapper.insertList(qrtzCalendarsList);
    }
    
    @Override
    public int countEntity(QrtzCalendarsEntity qrtzCalendarsList) throws Exception{
        return qrtzCalendarsMapper.selectCount(qrtzCalendarsList);
    }
    
    @Override
    public int countEntityByExample(Example QrtzCalendarsEntityExample) throws Exception{
        return qrtzCalendarsMapper.selectCountByExample(QrtzCalendarsEntityExample);
    }

	@Override
    public QrtzCalendarsEntity findByPrimaryKey(String schedName , String calendarName) throws Exception{
    
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("schedName", schedName);
		map.put("calendarName", calendarName);
        return qrtzCalendarsMapper.selectByPrimaryKey(map);
    }
    
    @Override
    public List<QrtzCalendarsEntity> findListEntityByExample(QrtzCalendarsEntity qrtzCalendars, int offset, int limit) throws Exception{
        Example qrtzCalendarsExample = new Example(QrtzCalendarsEntity.class);
        RowBounds rowBounds = new RowBounds(offset, limit);
        return qrtzCalendarsMapper.selectByExampleAndRowBounds(qrtzCalendarsExample, rowBounds);
    }
    
    @Override
    public List<QrtzCalendarsEntity> findListEntity(QrtzCalendarsEntity qrtzCalendars, int offset, int limit) throws Exception{
        RowBounds rowBounds = new RowBounds(offset, limit);
        return qrtzCalendarsMapper.selectByRowBounds(qrtzCalendars, rowBounds);
    }
    
    @Override
    public int removeEntityByPrimaryKey(String schedName , String calendarName) throws Exception{
    
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("String", schedName);
		map.put("String", calendarName);
        return qrtzCalendarsMapper.deleteByPrimaryKey(map);
    }
    
	@Override
    public int removeListByPrimaryKey(List<Map<String,Object>> list) throws Exception{
        Example example = new Example(QrtzCalendarsEntity.class); 
        //Criteria cc = example.createCriteria();
        //cc.andIn("schedName", schedNameList);
        return qrtzCalendarsMapper.deleteByExample(example);
    }
    
    @Override
    public int removeByExample(Example QrtzCalendarsEntityExample) throws Exception{
        return qrtzCalendarsMapper.deleteByExample(QrtzCalendarsEntityExample);
    }
    
    @Override
    public int updateByPrimaryKey(QrtzCalendarsEntity qrtzCalendars) throws Exception{
        return qrtzCalendarsMapper.updateByPrimaryKey(qrtzCalendars);
    }
    
    @Override
    public int updateByPrimaryKeySelective(QrtzCalendarsEntity qrtzCalendars) throws Exception{
        return qrtzCalendarsMapper.updateByPrimaryKeySelective(qrtzCalendars);
    }
    
    
 
}