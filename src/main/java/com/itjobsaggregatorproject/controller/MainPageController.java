package com.itjobsaggregatorproject.controller;

import com.itjobsaggregatorproject.entity.Job;
import com.itjobsaggregatorproject.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/jobs")
public class MainPageController {
    @Autowired
    JobService jobService;
    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseStatus(OK)
    public List<Job> getJobs() {
        return jobService.getAll();

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/random")
    @ResponseStatus(OK)
    public Job getRandomJob() {
        List<Job> jobs = jobService.getAll();
        return jobs.get(new Random().nextInt(jobs.size()));
    }
}
