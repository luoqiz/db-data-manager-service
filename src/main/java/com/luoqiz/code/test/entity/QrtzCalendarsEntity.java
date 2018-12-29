package com.luoqiz.code.test.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;

/**
 * @author luoqiz
 * @Date: 2018-12-29 11:58:56
 * 
 */
@ApiModel
@Table(name = "qrtz_calendars")
public class QrtzCalendarsEntity {
	
	@ApiModelProperty(value="",position = 1)	
	@Id
	@Column(name = "SCHED_NAME")
	private String schedName;	
	
	@ApiModelProperty(value="",position = 2)	
	@Id
	@Column(name = "CALENDAR_NAME")
	private String calendarName;	
	
	@ApiModelProperty(value="",position = 3)	
	@Column(name = "CALENDAR")
	private String calendar;	
	
	public void setSchedName(String schedName) {
         this.schedName = schedName == null ? null : schedName.trim();
    }
    
	public String getSchedName(){
		return schedName;
	}
	
	public void setCalendarName(String calendarName) {
         this.calendarName = calendarName == null ? null : calendarName.trim();
    }
    
	public String getCalendarName(){
		return calendarName;
	}
	
	public void setCalendar(String calendar) {
         this.calendar = calendar == null ? null : calendar.trim();
    }
    
	public String getCalendar(){
		return calendar;
	}
}