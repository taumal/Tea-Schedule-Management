package com.example.TeaScheduleManagement;

import com.example.TeaScheduleManagement.entity.Day;
import com.example.TeaScheduleManagement.repository.DayRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TeaScheduleManagementApplicationTests {

    private final DayRepository dayRepository;

    @Autowired
    TeaScheduleManagementApplicationTests(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void save() {
//        Day day = new Day(5L,"Friday","Babu","Parvez");
//        dayRepository.save(day);
    }
}
