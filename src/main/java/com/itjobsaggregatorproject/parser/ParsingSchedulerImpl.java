package com.itjobsaggregatorproject.parser;

import com.itjobsaggregatorproject.cache.JobCache;
import com.itjobsaggregatorproject.service.JobService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ParsingSchedulerImpl implements ParserScheduler {
    @Qualifier("hhUa")
    @Autowired
    JobParser hhUaJobParser;
    @Qualifier("workUa")
    @Autowired
    JobParser workUaJobParser;

    @Autowired
    JobService jobService;
    @Autowired
    JobCache jobCache;
    private final int oneHour = 3600 * 1000;

    private void deleteAutdatedJobs() {
        WebDriver driver = new HtmlUnitDriver();

    }

    @Scheduled(fixedRate = oneHour) //every hour execution
    public void parseJobsEveryHour() {
        //creating two runnable instances  with different parser implementations
        // and running two threads that parse jobs
        Runnable hhUaRunnable = () -> new ParserRoutineExecutor().parse(jobService, jobCache, hhUaJobParser);
        Runnable workUaRunnable = () -> new ParserRoutineExecutor().parse(jobService, jobCache, workUaJobParser);
        new Thread(workUaRunnable).start();
        new Thread(hhUaRunnable).start();
    }


}

