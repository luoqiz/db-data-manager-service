package com.luoqiz.code.test.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;

/**
 * @author luoqiz
 * @Date: 2018-12-29 17:28:35
 * 
 */
@ApiModel
@Data
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
}