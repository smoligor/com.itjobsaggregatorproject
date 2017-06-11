package com.itjobsaggregatorproject.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Employee {
    @Id
    String id;
    String name;
    String surname;


}
