package com.itjobsaggregatorproject.parser;

import com.itjobsaggregatorproject.entity.Job;
import com.itjobsaggregatorproject.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParsingSchedulerImpl implements ParserScheduler {
    @Autowired
    JobService jobService;
    @Autowired
    JobParser jobParser;
    final int oneHour = 3600 * 1000;

    @Scheduled(fixedRate = oneHour) //every hour execution
    public void parseJobsFromWorkUaEveryHour() {

        boolean isFirstRoutine = true;
        System.out.println("Starting parsing work.ua routine...");

        if (jobService.getAll().size() > 100) {
            isFirstRoutine = false;
        }
        List<Job> parsedJobs = jobParser.parseJobs(isFirstRoutine);
        List<Job> persistedJobs = jobService.getAll();
        parsedJobs.removeAll(persistedJobs);
        parsedJobs.forEach(jobService::save);
        System.out.println("Parsing work.ua routine ended. " + parsedJobs.size() + " new jobs added.");
    }
}

