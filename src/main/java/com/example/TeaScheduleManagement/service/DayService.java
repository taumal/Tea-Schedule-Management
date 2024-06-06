package com.example.TeaScheduleManagement.service;

import com.example.TeaScheduleManagement.entity.Day;
import com.example.TeaScheduleManagement.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;
import java.util.Calendar;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class DayService {
    private final DayRepository dayRepository;

    @Autowired
    public DayService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    public List<Day> listAllDays(){
        List<Day> teaSchedule = dayRepository.findAll();
        LocalDate currentDate = LocalDate.now();

        for (Day day :
                teaSchedule) {
            switch (day.getName()) {
                case "Monday" -> day.setToday(currentDate.with(DayOfWeek.MONDAY).getDayOfMonth());
                case "Tuesday" -> day.setToday(currentDate.with(DayOfWeek.TUESDAY).getDayOfMonth());
                case "Wednesday" -> day.setToday(currentDate.with(DayOfWeek.WEDNESDAY).getDayOfMonth());
                case "Thursday" -> day.setToday(currentDate.with(DayOfWeek.THURSDAY).getDayOfMonth());
                case "Friday" -> day.setToday(currentDate.with(DayOfWeek.FRIDAY).getDayOfMonth());
            }
        }

        return teaSchedule;
    }
    public void save(Day day) {
        dayRepository.save(day);
    }
    public void delete(Day day) {
        dayRepository.delete(day);
    }
    public Day findByDayName(String day){
        return dayRepository.findByName(day);
    }

    public Day get(Long id) {
        Optional<Day> optionalUser = dayRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        else {
            return null;
        }
    }

//    public void run(String... args) throws Exception {
//        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
//        List<Day> teaSchedule = dayRepository.findAll();
//        LocalDate currentDate = LocalDate.now();
//        scheduledExecutorService.scheduleAtFixedRate(() -> {
//            for (Day day : teaSchedule) {
//                switch (day.getName()) {
//                    case "Monday" -> day.setToday(currentDate.with(DayOfWeek.MONDAY));
//                    case "Tuesday" -> day.setToday(currentDate.with(DayOfWeek.TUESDAY));
//                    case "Wednesday" -> day.setToday(currentDate.with(DayOfWeek.WEDNESDAY));
//                    case "Thursday" -> day.setToday(currentDate.with(DayOfWeek.THURSDAY));
//                    case "Friday" -> day.setToday(currentDate.with(DayOfWeek.FRIDAY));
//                }
//                dayRepository.save(day);
//            }
//        }, 0, 5, TimeUnit.MINUTES);
//    }
}
