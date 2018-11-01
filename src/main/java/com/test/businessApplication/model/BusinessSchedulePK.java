package com.test.businessApplication.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BusinessSchedulePK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name="business_id", insertable=false, updatable=false)
	private Long businessId;
	
	@Column(name="week_day_id", insertable=false, updatable=false)
	private Integer weekDayId;

	public BusinessSchedulePK() {
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public Integer getWeekDayId() {
		return weekDayId;
	}

	public void setWeekDayId(Integer weekDayId) {
		this.weekDayId = weekDayId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((businessId == null) ? 0 : businessId.hashCode());
		result = prime * result + ((weekDayId == null) ? 0 : weekDayId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusinessSchedulePK other = (BusinessSchedulePK) obj;
		if (businessId == null) {
			if (other.businessId != null)
				return false;
		} else if (!businessId.equals(other.businessId))
			return false;
		if (weekDayId == null) {
			if (other.weekDayId != null)
				return false;
		} else if (!weekDayId.equals(other.weekDayId))
			return false;
		return true;
	}

}

