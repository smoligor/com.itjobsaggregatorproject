package com.itjobsaggregatorproject.parser;

import com.itjobsaggregatorproject.entity.Job;
import com.itjobsaggregatorproject.service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParsingSchedulerImpl implements ParserScheduler {
    @Autowired
    JobsService jobsService;
    @Autowired
    JobsParser jobsParser;
    final int oneHour = 3600 * 1000;

    @Scheduled(fixedRate = oneHour) //every hour execution
    public void parseJobsFromWorkUaEveryHour() {

        boolean isFirstRoutine = true;
        System.out.println("Starting parsing work.ua routine...");

        if (jobsService.getAll().size() > 100) {
            isFirstRoutine = false;
        }
        List<Job> parsedJobs = jobsParser.parseJobs(isFirstRoutine);
        List<Job> persistedJobs = jobsService.getAll();
        parsedJobs.removeAll(persistedJobs);
        parsedJobs.forEach(jobsService::save);
        System.out.println("Parsing work.ua routine ended. " + parsedJobs.size() + " new jobs added.");
    }
}

