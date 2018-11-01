package com.test.businessApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.businessApplication.model.BusinessSchedule;
import com.test.businessApplication.model.BusinessSchedulePK;

public interface BusinessSchdeduleRepository extends JpaRepository<BusinessSchedule, BusinessSchedulePK> {

	List<BusinessSchedule> findByBusinessId(long id);

}
