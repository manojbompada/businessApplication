package com.test.businessApplication.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.businessApplication.Exception.BusinessNotFoundException;
import com.test.businessApplication.dto.BusinessClientDTO;
import com.test.businessApplication.service.BusinessService;

@RestController
@RequestMapping("/api/v0")
public class BusinessController {

	@Autowired
	private BusinessService businessService;
	
	private static final Logger logger = LogManager.getLogger(BusinessService.class);
	
//	private String returnSuccess = "{ \"response\" : \"Success\", \"businessId\" : %d }";
//	private String returnError = "{ \"error\" : \" %s \" }";
	
	
	/**
	 * api for creating a business
	 * @param businessClient
	 * @return
	 */
	@PostMapping("/businesses")
	public ResponseEntity<BusinessClientDTO> createBusiness(@Valid @RequestBody BusinessClientDTO businessClient) {
		try {
			BusinessClientDTO business = businessService.addBusiness(businessClient);
			return new ResponseEntity<BusinessClientDTO>(business, HttpStatus.OK);
		} catch (Exception e) {
			logger.log(Level.ERROR, e.getMessage(), e);
			return new ResponseEntity<BusinessClientDTO>(new BusinessClientDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * api to get the list of all businesses and filter businesses as per time slot
	 * @param dayId
	 * @param openTime
	 * @param closeTime
	 * @return
	 */
	@GetMapping("/businesses")
	public ResponseEntity<List<BusinessClientDTO>> getAllBusinesses(
			@RequestParam (value = "dayId", required = false) Integer dayId,
			@RequestParam(value = "openTime",  required = false) String openTime,
			@RequestParam(value = "closeTime", required = false) String closeTime) {
		
		try {
			List<BusinessClientDTO> businessList = businessService.getAllBusinesses(dayId,openTime,closeTime);
			return new ResponseEntity<List<BusinessClientDTO>>(businessList, HttpStatus.OK);
		} catch (Exception e) {
			logger.log(Level.ERROR, e.getMessage(), e);
			return new ResponseEntity<List<BusinessClientDTO>>(new ArrayList<BusinessClientDTO>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * api to get the details of a business
	 * @param businessId
	 * @return
	 */
	@GetMapping("/businesses/{id}")
	public ResponseEntity<BusinessClientDTO> getBusinessById(@PathVariable(value="id") long businessId) {
		try {
			BusinessClientDTO business = businessService.getBusiness(businessId);
			return new ResponseEntity<BusinessClientDTO>(business, HttpStatus.OK);
		} catch (Exception e) {
			logger.log(Level.ERROR, e.getMessage(), e);
			return new ResponseEntity<BusinessClientDTO>(new BusinessClientDTO(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * api for updating business details
	 * @param businessId
	 * @param businessClient
	 * @return
	 */
	@PutMapping("/businesses/{id}")
	public ResponseEntity<BusinessClientDTO> updateBusiness(
			@PathVariable(value="id") long businessId,
			@Valid @RequestBody BusinessClientDTO businessClient) {
		try {
			BusinessClientDTO updatedBusiness = businessService.updateBusiness(businessId, businessClient);
			return new ResponseEntity<BusinessClientDTO>(updatedBusiness, HttpStatus.OK);
		} catch (BusinessNotFoundException e) {
			logger.log(Level.ERROR, e.getMessage(), e);
			return new ResponseEntity<BusinessClientDTO>(new BusinessClientDTO(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.log(Level.ERROR, e.getMessage(), e);
			return new ResponseEntity<BusinessClientDTO>(new BusinessClientDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	
}
