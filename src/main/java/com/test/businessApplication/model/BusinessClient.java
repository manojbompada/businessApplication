package com.test.businessApplication.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="business_clients")
public class BusinessClient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="url")
	private String url;
	
	@Column(name="phone_number", nullable = false)
	private long phoneNumber;
	
	@OneToMany(mappedBy="business",fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<BusinessSchedule> businessSchedules = new HashSet<>();


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

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<BusinessSchedule> getBusinessSchedules() {
		return businessSchedules;
	}

	public void setBusinessSchedules(Set<BusinessSchedule> businessSchedules) {
		this.businessSchedules = businessSchedules;
	}

	@Override
	public String toString() {
		return "BusinessClient [id=" + id + ", name=" + name + ", url=" + url + ", phoneNumber=" + phoneNumber
				+ ", businessSchedules=" + businessSchedules + "]";
	}
	
	
}