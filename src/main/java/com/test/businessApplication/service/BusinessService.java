package com.test.businessApplication.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.businessApplication.Exception.BusinessNotFoundException;
import com.test.businessApplication.dto.BusinessClientDTO;
import com.test.businessApplication.dto.BusinessScheduleDTO;
import com.test.businessApplication.model.BusinessClient;
import com.test.businessApplication.model.BusinessSchedule;
import com.test.businessApplication.model.BusinessSchedulePK;
import com.test.businessApplication.repository.BusinessRepository;
import com.test.businessApplication.repository.BusinessSchdeduleRepository;
import com.test.businessApplication.repository.WeekDayRepository;

@Service
public class BusinessService {
	
	@Autowired
	private BusinessRepository businessRepository;
	
	@Autowired
	private BusinessSchdeduleRepository businessSchdeduleRepository;
	
	@Autowired
	private WeekDayRepository weekDayRepository;

	public List<BusinessClientDTO> getAllBusinesses(Integer day, String openTime, String closeTime) {
		
		List<BusinessClientDTO> businessClientsDto = new ArrayList<>();
		List<BusinessClient> filteredBusinessClients = new ArrayList<>();
		ModelMapper mapper = new ModelMapper();
		
		List<BusinessClient> businessClients = businessRepository.findAll();
		
		if (day != null && day > 0 || openTime != null && !"".equals(openTime) || closeTime != null && !"".equals(closeTime)) {
			
			if (day!=null && day > 0) {
				for(BusinessClient businessClient: businessClients) {
					Set<BusinessSchedule> filteredList = new HashSet<>();
					for(BusinessSchedule businessSchedule: businessClient.getBusinessSchedules()) {
							if(businessSchedule.getDayOfTheWeek().getDayId() == day.intValue() && (businessSchedule.getOpenTime() > 0 || businessSchedule.getCloseTime()>0)) {
								filteredList.add(businessSchedule);
							}
						}
					businessClient.setBusinessSchedules(filteredList);
				}
			}
			
			if (openTime != null && !"".equals(openTime))  {
				for(BusinessClient businessClient: businessClients) {
					Set<BusinessSchedule> filteredList = new HashSet<>();
					for(BusinessSchedule businessSchedule: businessClient.getBusinessSchedules()) {
							if(businessSchedule.getOpenTime() <= Long.valueOf(openTime) && businessSchedule.getCloseTime() >= Long.valueOf(openTime)) {
								filteredList.add(businessSchedule);
							}
					}
					businessClient.setBusinessSchedules(filteredList);
				}
			}
			
			if (closeTime != null && !"".equals(closeTime))  {
				for(BusinessClient businessClient: businessClients) {
					Set<BusinessSchedule> filteredList = new HashSet<>();
					for(BusinessSchedule businessSchedule: businessClient.getBusinessSchedules()) {
							if(businessSchedule.getCloseTime() >= Long.valueOf(closeTime) && businessSchedule.getOpenTime() <= Long.valueOf(closeTime)) {
								filteredList.add(businessSchedule);
							}
					}
					businessClient.setBusinessSchedules(filteredList);
				}
			}
			
			for(BusinessClient businessClient: businessClients) {
				if (businessClient.getBusinessSchedules() != null && !businessClient.getBusinessSchedules().isEmpty()) {
					filteredBusinessClients.add(businessClient);
				}
			}
			
			businessClientsDto = mapper.map(filteredBusinessClients, new TypeToken<List<BusinessClientDTO>>() {}.getType());
		} else {
			businessClientsDto = mapper.map(businessClients, new TypeToken<List<BusinessClientDTO>>() {}.getType());
		}
		
		return businessClientsDto;
	
	}
	
	public BusinessClientDTO getBusiness(long id) throws BusinessNotFoundException {
		
		Optional<BusinessClient> businessClient =  businessRepository.findById(id);
		if (!businessClient.isPresent()) {
			throw new BusinessNotFoundException(id);
		}
		BusinessClient business = businessClient.get();
		ModelMapper mapper = new ModelMapper();
		BusinessClientDTO businessClientDto = new BusinessClientDTO();
		
		businessClientDto = mapper.map(business, new TypeToken<BusinessClientDTO>() {}.getType());
		return businessClientDto;
				 
	}
	 
	@Transactional
	public BusinessClientDTO updateBusiness(long id, BusinessClientDTO businessClient) throws BusinessNotFoundException {
		
		if ("".equals(businessClient.getName())
				|| businessClient.getPhoneNumber() == null
				|| businessClient.getBusinessSchedules().isEmpty()) {
				throw new IllegalArgumentException ();
			}
		
		Optional<BusinessClient> business =  businessRepository.findById(id);
		if (!business.isPresent()) {
			throw new BusinessNotFoundException(id);
		}
		BusinessClient clientBusiness = business.get();
		clientBusiness.setName(businessClient.getName());
		clientBusiness.setName(businessClient.getName());
		clientBusiness.setPhoneNumber(businessClient.getPhoneNumber());
		clientBusiness.setUrl(businessClient.getUrl());
		
		clientBusiness = businessRepository.save(clientBusiness);
		
		Set<BusinessScheduleDTO> businessDTOSchedules = businessClient.getBusinessSchedules();
		for(BusinessScheduleDTO schedule: businessDTOSchedules) {
			BusinessSchedule businessSchedule = new BusinessSchedule();
			BusinessSchedulePK businessSchedulePK = new BusinessSchedulePK();
			businessSchedulePK.setBusinessId(clientBusiness.getId());
			businessSchedulePK.setWeekDayId(schedule.getDayId());
			businessSchedule.setScheduleId(businessSchedulePK);
			businessSchedule.setBusiness(clientBusiness);
			businessSchedule.setDayOfTheWeek(weekDayRepository.getOne(schedule.getDayId()));
			businessSchedule.setOpenTime(schedule.getOpenTime() != null && !"".equals(schedule.getOpenTime())? Long.valueOf(schedule.getOpenTime()):0);
			businessSchedule.setCloseTime(schedule.getCloseTime() != null && !"".equals(schedule.getCloseTime())? Long.valueOf(schedule.getCloseTime()):0);
			// saving BusinessSchedule object
			businessSchedule = businessSchdeduleRepository.save(businessSchedule);
		}
		return businessClient;	
	}
	
	public void deleteBusiness(BusinessClient business) {
		businessRepository.delete(business);
	}

	@Transactional
	public BusinessClientDTO addBusiness(BusinessClientDTO businessClient) {
			
		if ("".equals(businessClient.getName())
			|| businessClient.getPhoneNumber() == null
			|| businessClient.getBusinessSchedules().isEmpty()) {
			throw new IllegalArgumentException ();
		}
		
		BusinessClient business = new BusinessClient();
		business.setName(businessClient.getName());
		business.setPhoneNumber(businessClient.getPhoneNumber());
		business.setUrl(businessClient.getUrl());
		// saving BusinessClient object
		business = businessRepository.save(business);
		businessClient.setId(business.getId());
		
		Set<BusinessScheduleDTO> businessDTOSchedules = businessClient.getBusinessSchedules();
		
		if (!businessDTOSchedules.isEmpty()) {
			// save schedules for business
			for(BusinessScheduleDTO schedule: businessDTOSchedules) {
				BusinessSchedule businessSchedule = new BusinessSchedule();
				BusinessSchedulePK businessSchedulePK = new BusinessSchedulePK();
				businessSchedulePK.setBusinessId(business.getId());
				businessSchedulePK.setWeekDayId(schedule.getDayId());
				businessSchedule.setScheduleId(businessSchedulePK);
				businessSchedule.setBusiness(business);
				businessSchedule.setDayOfTheWeek(weekDayRepository.getOne(schedule.getDayId()));
				businessSchedule.setOpenTime(schedule.getOpenTime() != null && !"".equals(schedule.getOpenTime())? Long.valueOf(schedule.getOpenTime()):0);
				businessSchedule.setCloseTime(schedule.getCloseTime() != null && !"".equals(schedule.getCloseTime())? Long.valueOf(schedule.getCloseTime()):0);
				// saving BusinessSchedule object
				businessSchedule = businessSchdeduleRepository.save(businessSchedule);
			}
		}
		return businessClient;
		
	}

}

