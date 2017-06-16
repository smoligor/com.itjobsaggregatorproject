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
    public volatile static int threadsCompletedTheirTask = 0;

    private void deleteAutdatedJobs() {
        WebDriver driver = new HtmlUnitDriver();
    }

    @Scheduled(fixedRate = oneHour) //every hour execution
    public void parseJobsEveryHour() {
        //creating two runnable instances  with different parser implementations
        // and running two threads that parse jobs
        // Runnable hhUaRunnable = () -> new ParserRoutineExecutor().parse(jobService, jobCache, hhUaJobParser);
        //Runnable workUaRunnable = () -> new ParserRoutineExecutor().parse(jobService, jobCache, workUaJobParser);
        //  new Thread(workUaRunnable).start();
        //  new Thread(hhUaRunnable).start();
//        while (threadsCompletedTheirTask < 2) {
//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        List<Job> jobs = new ArrayList<>(jobCache.getAll());
//        System.out.println(jobs.size() + "size before deletion");
//        for (int i = 0; i < jobs.size(); i++) {
//            jobs.get(i);
//            for (int j = 0; j < jobs.size(); j++) {
//                if (i != j) {
//                    String firstDescription = jobs.get(i).getDescription();
//                    String secondDescription = jobs.get(j).getDescription();
//                    if (StringUtils.getLevenshteinDistance(firstDescription, secondDescription) > 20) {
//                        jobs.remove(jobs.get(j));
//                    }
//                }
//            }
//
//        }
//        System.out.println(jobs.size() + "size after deletion");
        jobCache.addAll(jobService.getAll());
    }

}


