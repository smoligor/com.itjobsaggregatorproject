package com.itjobsaggregatorproject.Controller;

import com.itjobsaggregatorproject.Entity.Job;
import com.itjobsaggregatorproject.Service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
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
    JobsService jobsService;

    @GetMapping("/all")
    @ResponseStatus(OK)
    public List<Job> getJobs() {
        return jobsService.getAll();
    }

    @GetMapping("/random")
    @ResponseStatus(OK)
    public Job getRandomJob() {
        List<Job> jobs = jobsService.getAll();
        return jobs.get(new Random().nextInt(jobs.size()));

    }
}
