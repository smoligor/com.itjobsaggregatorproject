package com.itjobsaggregatorproject.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Company {
    @Id
    String id;
    String name;
    String description;
    String link;

}
