package com.itjobsaggregatorproject.cache;

import com.itjobsaggregatorproject.entity.Job;
import com.itjobsaggregatorproject.service.JobService;
import lombok.Getter;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Getter
public class JobCache {
    @Autowired
    JobService jobService;

    ConcurrentHashSet<Job> cache;

    @PostConstruct
    public void init() {
        cache = new ConcurrentHashSet<>();
        cache.addAll(jobService.getAll());
    }

}
