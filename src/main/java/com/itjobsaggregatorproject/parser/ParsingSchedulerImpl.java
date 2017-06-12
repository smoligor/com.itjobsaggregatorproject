package com.itjobsaggregatorproject.parser;

import com.itjobsaggregatorproject.cache.JobCache;
import com.itjobsaggregatorproject.entity.Job;
import com.itjobsaggregatorproject.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParsingSchedulerImpl implements ParserScheduler {
    @Autowired
    private JobService jobService;
    @Autowired
    private JobParser jobsParser;
    @Autowired
    private JobCache jobCache;
    final int oneHour = 3600 * 1000;

    @Scheduled(fixedRate = oneHour) //every hour execution
    public void parseJobsFromWorkUaEveryHour() {

        boolean isFirstRoutine = true;
        System.out.println("Starting parsing work.ua routine...");

        if (jobService.getAll().size() > 100) {
            isFirstRoutine = false;
        }
        jobCache.getCache().addAll(jobService.getAll());
        List<Job> parsedJobs = jobsParser.parseJobs(isFirstRoutine);
        List<Job> persistedJobs = jobService.getAll();
        parsedJobs.removeAll(persistedJobs);
        jobCache.getCache().addAll(parsedJobs);
        parsedJobs.forEach(jobService::save);
        System.out.println("Parsing work.ua routine ended. " + parsedJobs.size() + " new jobs added.");
    }
}

