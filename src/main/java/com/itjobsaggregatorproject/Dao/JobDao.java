package com.itjobsaggregatorproject.Dao;

import com.itjobsaggregatorproject.Entity.Job;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface JobDao extends MongoRepository<Job, String> {

}
