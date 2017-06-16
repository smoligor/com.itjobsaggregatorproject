package com.itjobsaggregatorproject.cache;

import com.itjobsaggregatorproject.entity.Job;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JobCache {

    private ConcurrentHashSet<Job> cache;

    @PostConstruct
    public void init() {
        cache = new ConcurrentHashSet<>();
    }

    public synchronized void addToCache(Job job) {
        cache.add(job);
    }

    public synchronized void deleteFromCache(Job job) {
        if (cache.contains(job)) {
            cache.remove(job);
        }
    }

    public synchronized void addAll(List<Job> jobs) {
        cache.addAll(jobs);
    }

    public Job getById(String id) {
        Optional<Job> jobOptional = cache.stream().filter(e -> e.getId().equals(id)).findFirst();
        if (jobOptional.isPresent()) {
            return jobOptional.get();
        } else return new Job();
    }

    public List<Job> getAll() {
        return new ArrayList<>(cache);
    }

}
