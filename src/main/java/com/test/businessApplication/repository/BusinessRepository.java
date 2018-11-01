package com.test.businessApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.businessApplication.model.BusinessClient;

public interface BusinessRepository extends JpaRepository<BusinessClient, Long>  {

}
