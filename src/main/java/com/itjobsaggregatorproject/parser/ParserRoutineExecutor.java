package com.itjobsaggregatorproject.parser;

import com.itjobsaggregatorproject.cache.JobCache;
import com.itjobsaggregatorproject.entity.Job;
import com.itjobsaggregatorproject.service.JobService;

import java.util.List;
import java.util.stream.Collectors;

public class ParserRoutineExecutor {

    public void parse(JobService jobService, JobCache jobCache, JobParser jobParser) {
        System.out.println(jobService.toString());
        //reflection is used for automatic inserting class name into stdout
        boolean isFirstRoutine = true;
        String parserSimpleName = jobParser.getClass().getSimpleName();
        String websiteString = "";
        if (parserSimpleName.equals("HhUaJobParserImpl")) {
            websiteString = "hh.ua";
        }
        if (parserSimpleName.equals("WorkUaJobParserImpl")) {
            websiteString = "work.ua";
        }
        String finalLinkWebSiteLink = websiteString;
        System.out.println("Starting parsing " + parserSimpleName + " routine...");
       /*this logic counts how many jobs from particular site are in database, if many only fraction of
        links will be parsed*/
        if (jobService.getAll().stream()
                .map(Job::getLink).filter(link -> link.contains(finalLinkWebSiteLink))
                .collect(Collectors.toList()).size() > 100) {
            isFirstRoutine = false;
        }
        System.out.println(isFirstRoutine + " is first routine " + parserSimpleName);
        jobCache.addAll(jobService.getAll());
        List<Job> parsedJobs = jobParser.parseJobs(isFirstRoutine);
        List<Job> persistedJobs = jobService.getAll();
        parsedJobs.removeAll(persistedJobs);
        jobCache.addAll(parsedJobs);
        parsedJobs.forEach(jobService::save);
        System.out.println("Parsing " + jobParser.getClass().getSimpleName() + " routine ended. " + parsedJobs.size()
                + " new jobs added.");
    }


}
