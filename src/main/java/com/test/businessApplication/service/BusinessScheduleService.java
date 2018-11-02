package com.test.businessApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.businessApplication.model.BusinessSchedule;
import com.test.businessApplication.repository.BusinessSchdeduleRepository;

@Service
public class BusinessScheduleService  {

	@Autowired
	private BusinessSchdeduleRepository businessSchdeduleRepository;
	
	public List<BusinessSchedule> getBusinessSchedule(long businessId) {
		List<BusinessSchedule> businessSchedule =  businessSchdeduleRepository.findByBusinessId(businessId);
		 
		return businessSchedule;
	}
	
	public BusinessSchedule addBusinessSchedule(BusinessSchedule businessSchedule) {
		return businessSchdeduleRepository.save(businessSchedule);
	}
	 
}
