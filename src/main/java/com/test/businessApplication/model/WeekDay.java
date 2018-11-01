package com.test.businessApplication.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="days_of_the_week")
public class WeekDay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="day_id")
	private Integer dayId;
	
	@Column(name="day_name")
	private String dayName;

	public Integer getDayId() {
		return dayId;
	}

	public void setDayId(Integer dayId) {
		this.dayId = dayId;
	}

	public String getDayName() {
		return dayName;
	}

	@Override
	public String toString() {
		return "WeekDay [dayId=" + dayId + ", dayName=" + dayName + "]";
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
	}


}

