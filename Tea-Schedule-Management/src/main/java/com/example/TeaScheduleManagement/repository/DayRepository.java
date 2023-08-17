package com.example.TeaScheduleManagement.repository;

import com.example.TeaScheduleManagement.entity.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayRepository extends JpaRepository<Day,Long> {
    Day findByName(String dayName);
}
