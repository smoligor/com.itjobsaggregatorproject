package com.itjobsaggregatorproject.dao;

import com.itjobsaggregatorproject.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeDao extends MongoRepository<Employee, String> {
}
