package com.test.businessApplication.dto;

import java.util.Set;


public class BusinessClientDTO {
	private Long id;
	private String name;
	private String url;
	private Long phoneNumber;
	private Set<BusinessScheduleDTO> businessSchedules;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Set<BusinessScheduleDTO> getBusinessSchedules() {
		return businessSchedules;
	}
	public void setBusinessSchedules(Set<BusinessScheduleDTO> businessSchedules) {
		this.businessSchedules = businessSchedules;
	}
	@Override
	public String toString() {
		return "BusinessClientDTO [id=" + id + ", name=" + name + ", url=" + url + ", phoneNumber=" + phoneNumber
				+ ", businessSchedules=" + businessSchedules + "]";
	}
	
	
}
