package com.test.businessApplication;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.businessApplication.dto.BusinessClientDTO;
import com.test.businessApplication.dto.BusinessScheduleDTO;
import com.test.businessApplication.model.BusinessClient;
import com.test.businessApplication.model.BusinessSchedule;
import com.test.businessApplication.model.BusinessSchedulePK;
import com.test.businessApplication.model.WeekDay;
import com.test.businessApplication.repository.BusinessRepository;
import com.test.businessApplication.repository.BusinessSchdeduleRepository;
import com.test.businessApplication.repository.WeekDayRepository;
import com.test.businessApplication.service.BusinessService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BusinessApplicationTests {

	@Mock
	BusinessRepository businessRepository;
	@Mock
	BusinessSchdeduleRepository businessSchdeduleRepository;
	@Mock
	WeekDayRepository weekDayRepository;
	
	@InjectMocks
	BusinessService businessService;
	
	List<BusinessClient> businessClients = null;
	BusinessClientDTO business = null;
	
	@Before
	public void inti() {
		businessClients = new ArrayList<>();
		BusinessClient businessClient = new BusinessClient();
		businessClient.setId(1200L);
		businessClient.setName("business100");
		businessClient.setPhoneNumber(9746578345L);
		businessClient.setUrl("http://www.business100.com");
		
		Set<BusinessSchedule> businessSchedules = new HashSet<>();
		
		BusinessSchedule businessSchedule = new BusinessSchedule();
		BusinessSchedulePK businessSchedulePK = new BusinessSchedulePK();
		businessSchedulePK.setBusinessId(1200L);
		businessSchedulePK.setWeekDayId(1);
		businessSchedule.setScheduleId(businessSchedulePK);
		businessSchedule.setBusiness(businessClient);
		WeekDay weekDay = new WeekDay();
		weekDay.setDayId(1);
		weekDay.setDayName("Monday");
		businessSchedule.setDayOfTheWeek(weekDay);
		businessSchedule.setOpenTime(9L);
		businessSchedule.setCloseTime(17L);
		businessSchedules.add(businessSchedule);
		
		businessSchedule = new BusinessSchedule();
		businessSchedulePK = new BusinessSchedulePK();
		businessSchedulePK.setBusinessId(1200L);
		businessSchedulePK.setWeekDayId(2);
		businessSchedule.setScheduleId(businessSchedulePK);
		businessSchedule.setBusiness(businessClient);
		weekDay = new WeekDay();
		weekDay.setDayId(2);
		weekDay.setDayName("Tuesday");
		businessSchedule.setDayOfTheWeek(weekDay);
		businessSchedule.setOpenTime(0L);
		businessSchedule.setCloseTime(0L);
		businessSchedules.add(businessSchedule);
		
		businessSchedule = new BusinessSchedule();
		businessSchedulePK = new BusinessSchedulePK();
		businessSchedulePK.setBusinessId(1200L);
		businessSchedulePK.setWeekDayId(3);
		businessSchedule.setScheduleId(businessSchedulePK);
		businessSchedule.setBusiness(businessClient);
		weekDay = new WeekDay();
		weekDay.setDayId(3);
		weekDay.setDayName("Wednesday");
		businessSchedule.setDayOfTheWeek(weekDay);
		businessSchedule.setOpenTime(11L);
		businessSchedule.setCloseTime(20L);
		businessSchedules.add(businessSchedule);
		
		businessSchedule = new BusinessSchedule();
		businessSchedulePK = new BusinessSchedulePK();
		businessSchedulePK.setBusinessId(1200L);
		businessSchedulePK.setWeekDayId(7);
		businessSchedule.setScheduleId(businessSchedulePK);
		businessSchedule.setBusiness(businessClient);
		weekDay = new WeekDay();
		weekDay.setDayId(7);
		weekDay.setDayName("Sunday");
		businessSchedule.setDayOfTheWeek(weekDay);
		businessSchedule.setOpenTime(3L);
		businessSchedule.setCloseTime(15L);
		businessSchedules.add(businessSchedule);
		businessClient.setBusinessSchedules(businessSchedules);
		
		businessClients.add(businessClient);
		//================================================
		
		businessClient = new BusinessClient();
		businessClient.setId(1201L);
		businessClient.setName("business200");
		businessClient.setPhoneNumber(9746577845L);
		businessClient.setUrl("http://www.business200.com");
		
		businessSchedules = new HashSet<>();
		
		businessSchedule = new BusinessSchedule();
		businessSchedulePK = new BusinessSchedulePK();
		businessSchedulePK.setBusinessId(1201L);
		businessSchedulePK.setWeekDayId(3);
		businessSchedule.setScheduleId(businessSchedulePK);
		businessSchedule.setBusiness(businessClient);
		weekDay = new WeekDay();
		weekDay.setDayId(3);
		weekDay.setDayName("Wednesday");
		businessSchedule.setDayOfTheWeek(weekDay);
		businessSchedule.setOpenTime(12L);
		businessSchedule.setCloseTime(21L);
		businessSchedules.add(businessSchedule);
		
		businessSchedule = new BusinessSchedule();
		businessSchedulePK = new BusinessSchedulePK();
		businessSchedulePK.setBusinessId(1201L);
		businessSchedulePK.setWeekDayId(4);
		businessSchedule.setScheduleId(businessSchedulePK);
		businessSchedule.setBusiness(businessClient);
		weekDay = new WeekDay();
		weekDay.setDayId(4);
		weekDay.setDayName("Thursday");
		businessSchedule.setDayOfTheWeek(weekDay);
		businessSchedule.setOpenTime(12L);
		businessSchedule.setCloseTime(21L);
		businessSchedules.add(businessSchedule);
		
		businessSchedule = new BusinessSchedule();
		businessSchedulePK = new BusinessSchedulePK();
		businessSchedulePK.setBusinessId(1201L);
		businessSchedulePK.setWeekDayId(5);
		businessSchedule.setScheduleId(businessSchedulePK);
		businessSchedule.setBusiness(businessClient);
		weekDay = new WeekDay();
		weekDay.setDayId(5);
		weekDay.setDayName("Friday");
		businessSchedule.setDayOfTheWeek(weekDay);
		businessSchedule.setOpenTime(0L);
		businessSchedule.setCloseTime(0L);
		businessSchedules.add(businessSchedule);
		
		businessSchedule = new BusinessSchedule();
		businessSchedulePK = new BusinessSchedulePK();
		businessSchedulePK.setBusinessId(1201L);
		businessSchedulePK.setWeekDayId(7);
		businessSchedule.setScheduleId(businessSchedulePK);
		businessSchedule.setBusiness(businessClient);
		weekDay = new WeekDay();
		weekDay.setDayId(7);
		weekDay.setDayName("Sunday");
		businessSchedule.setDayOfTheWeek(weekDay);
		businessSchedule.setOpenTime(3L);
		businessSchedule.setCloseTime(15L);
		businessSchedules.add(businessSchedule);
		businessClient.setBusinessSchedules(businessSchedules);
		
		businessClients.add(businessClient);
		
		//================================================================================
		business = new BusinessClientDTO();
		business.setId(12011L);
		business.setName("business300");
		business.setName("business300");
		business.setPhoneNumber(7463748576L);
		business.setUrl("https://www.business300.com");
		Set<BusinessScheduleDTO> schedules = new HashSet<>();
		
		BusinessScheduleDTO scheduleDTO = new BusinessScheduleDTO();
		scheduleDTO.setDayId(1);
		scheduleDTO.setOpenTime("9");
		scheduleDTO.setCloseTime("17");
		schedules.add(scheduleDTO);
		scheduleDTO.setDayId(2);
		scheduleDTO.setOpenTime("3");
		scheduleDTO.setCloseTime("14");
		schedules.add(scheduleDTO);
		business.setBusinessSchedules(schedules);
		
	}
	
	@Test
	public void getBusinessList1() {
		Mockito.when(businessRepository.findAll()).thenReturn(businessClients);
		
		List<BusinessClientDTO> businessClientDTOs = businessService.getAllBusinesses(7,"","");
		assertEquals(businessClientDTOs.size(), 2);
		
	}
	
	@Test
	public void getBusinessList2() {
		Mockito.when(businessRepository.findAll()).thenReturn(businessClients);
		
		List<BusinessClientDTO> businessClientDTOs = businessService.getAllBusinesses(1,"9","15");
		assertEquals(businessClientDTOs.size(), 1);
		
	}
	
	@Test
	public void getBusinessList3() {
		Mockito.when(businessRepository.findAll()).thenReturn(businessClients);
		
		List<BusinessClientDTO> businessClientDTOs = businessService.getAllBusinesses(3,"11","");
		assertEquals(businessClientDTOs.size(), 1);
		
	}
	
	@Test
	public void getBusinessList4() {
		Mockito.when(businessRepository.findAll()).thenReturn(businessClients);
		
		List<BusinessClientDTO> businessClientDTOs = businessService.getAllBusinesses(3,"","21");
		assertEquals(businessClientDTOs.size(), 1);
		
	}
	
	@Test
	public void addBusiness() {
		when(businessRepository.save(any(BusinessClient.class))).thenReturn(new BusinessClient());
		when(businessSchdeduleRepository.save(any(BusinessSchedule.class))).thenReturn(new BusinessSchedule());
		when(weekDayRepository.getOne(any(Integer.class))).thenReturn(new WeekDay());

        assertThat(businessService.addBusiness(business), is(BusinessClientDTO.class));
		
	}
	

}
