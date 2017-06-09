package com.itjobsaggregatorproject.Service;

import com.itjobsaggregatorproject.Entity.Job;

import java.util.List;
import java.util.Optional;

public interface JobsService {

    public Optional<Job> getById(String id);

    public void save(Job job);

    public void update(Job job);

    public void delete(Job job);

    public List<Job> getAll();

}