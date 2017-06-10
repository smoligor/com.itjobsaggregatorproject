package com.itjobsaggregatorproject.Parser;

import com.itjobsaggregatorproject.Entity.Job;
import com.itjobsaggregatorproject.Service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.Thread.sleep;

@Component
public class ParserStarterImpl implements ParserStarter {

    @Autowired
    JobsParser jobsParser;
    @Autowired
    JobsService jobsService;

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        while (true) {
            boolean isFirstRoutine = true;
            System.out.println("Starting parsing routine...");
            if (jobsService.getAll().size() > 100) {
                isFirstRoutine = false;

            }
            List<Job> parsedJobs = jobsParser.parseJobs(isFirstRoutine);
            List<Job> persistedJobs = jobsService.getAll();
            parsedJobs.removeAll(persistedJobs);
            parsedJobs.forEach(jobsService::save);
            System.out.println("Parsing routine ended. " + parsedJobs.size() + " new jobs added.");
            try {
                int threeHoursInMs = 10800000;
                sleep(threeHoursInMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
