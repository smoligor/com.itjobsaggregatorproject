package com.itjobsaggregatorproject.dao;

import com.itjobsaggregatorproject.entity.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobDao extends MongoRepository<Job, String> {

}
