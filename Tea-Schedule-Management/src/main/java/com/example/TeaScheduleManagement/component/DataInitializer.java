package com.example.TeaScheduleManagement.component;

import com.example.TeaScheduleManagement.entity.Day;
import com.example.TeaScheduleManagement.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DayRepository dayRepository;

    @Autowired
    public DataInitializer(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (dayRepository.count() == 0) {
            // Insert fixed day names into the database
            insertFixedDays();
        }
    }

    private void insertFixedDays() {
        List<String> fixedDayNames = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        for (String dayName : fixedDayNames) {
            Day day = new Day();
            day.setName(dayName);
            dayRepository.save(day);
        }
    }
}