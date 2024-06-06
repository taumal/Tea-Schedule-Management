package com.example.TeaScheduleManagement.component;

import com.example.TeaScheduleManagement.entity.Day;
import com.example.TeaScheduleManagement.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
           /* List<Day> teaSchedule = dayRepository.findAll();
            LocalDate currentDate = LocalDate.now();*/

//        for (Day day :
//                teaSchedule) {
//            switch (day.getName()) {
//                case "Monday" -> day.setToday(currentDate.with(DayOfWeek.MONDAY));
//                case "Tuesday" -> day.setToday(currentDate.with(DayOfWeek.TUESDAY));
//                case "Wednesday" -> day.setToday(currentDate.with(DayOfWeek.WEDNESDAY));
//                case "Thursday" -> day.setToday(currentDate.with(DayOfWeek.THURSDAY));
//                case "Friday" -> day.setToday(currentDate.with(DayOfWeek.FRIDAY));
//            }
//        }
        }
        /*ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        List<Day> teaSchedule = dayRepository.findAll();
        LocalDate currentDate = LocalDate.now();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            for (Day day : teaSchedule) {
                switch (day.getName()) {
                    case "Monday" -> day.setToday(currentDate.with(DayOfWeek.MONDAY));
                    case "Tuesday" -> day.setToday(currentDate.with(DayOfWeek.TUESDAY));
                    case "Wednesday" -> day.setToday(currentDate.with(DayOfWeek.WEDNESDAY));
                    case "Thursday" -> day.setToday(currentDate.with(DayOfWeek.THURSDAY));
                    case "Friday" -> day.setToday(currentDate.with(DayOfWeek.FRIDAY));
                }
                dayRepository.save(day);
            }
        }, 0, 24, TimeUnit.HOURS);*/
    }

    private void insertFixedDays() {
        List<String> fixedDayNames = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        for (String dayName : fixedDayNames) {
            Day day = new Day();
            day.setName(dayName);
            dayRepository.saveAndFlush(day);
        }
    }
}