package com.itjobsaggregatorproject.controller;

import com.itjobsaggregatorproject.cache.JobCache;
import com.itjobsaggregatorproject.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.stream.Collectors;

@Controller
public class ThymleafController  {
    @Autowired
    JobService jobService;

    @Autowired
    JobCache jobCache;

    @RequestMapping(value = "/vacancies", method = RequestMethod.GET)
    public String getJobs(Model model) {
        model.addAttribute("jobs", jobCache.getAll().stream().limit(25).collect(Collectors.toList()));
        return "vacancies";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        return "index";
    }
}
