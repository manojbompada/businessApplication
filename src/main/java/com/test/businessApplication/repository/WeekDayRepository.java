package com.test.businessApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.test.businessApplication.model.WeekDay;

public interface WeekDayRepository extends JpaRepository<WeekDay, Integer> {

}
