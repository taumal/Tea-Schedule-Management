package com.example.TeaScheduleManagement.controller;

import com.example.TeaScheduleManagement.entity.Day;
import com.example.TeaScheduleManagement.service.DayService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DayController {
    private final DayService dayService;

    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @Autowired
    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    @RequestMapping("/")
    public String mainPage(Model model) {
        List<Day> dayList = dayService.listAllDays();
        model.addAttribute("days", dayList);
        model.addAttribute("formTitle", "Tea");
        return "schedulePage";
    }

    @PostMapping("/update")
    public String updateSchedule(Day day, RedirectAttributes redirectAttributes) {
        String message = null;
        boolean conflict = false;
        if (day.getMorningName().equals(day.getEveningName())) {
            message = "Cannot be on the same day";
            redirectAttributes.addFlashAttribute("Message", message);
            conflict=true;
        }
        else {
            List<String> dayNames = List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
            String currentDayName = day.getName();
//            Day currentDay = dayService.findByDayName(currentDayName);
            String nextDayName = dayNames.get((dayNames.indexOf(currentDayName) + 1) % dayNames.size());
            Day nextDay = dayService.findByDayName(nextDayName);
            String previousDayName = dayNames.get((dayNames.indexOf(currentDayName) - 1 + dayNames.size()) % dayNames.size());
            Day previousDay = dayService.findByDayName(previousDayName);
            if (nextDay.getMorningName() != null && day.getEveningName().equals(nextDay.getMorningName())) {
                message = "Cannot use the same name from " + currentDayName + " evening to " + nextDayName + " morning";
                conflict=true;
            } else if (previousDay.getEveningName() != null && day.getMorningName().equals(previousDay.getEveningName())) {
                message = "Cannot use the same name from " + previousDayName + " evening to " + currentDayName + " morning";
                conflict=true;
            }
            redirectAttributes.addFlashAttribute("Message",message);
        }
        if (!conflict){
            dayService.save(day);
            message = "Entry Successful";
            redirectAttributes.addFlashAttribute("MessageOK",message);
        }
        return "redirect:/";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/sign-out")
    public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        // .. perform logout
        this.logoutHandler.logout(request, response, authentication);
        redirectAttributes.addFlashAttribute("logout", "You have been logged out");
        return "redirect:/";
    }
}

