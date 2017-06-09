package com.itjobsaggregatorproject.Dao;

import com.itjobsaggregatorproject.Entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeDao extends MongoRepository<Employee, String> {
}
