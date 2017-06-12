package com.itjobsaggregatorproject.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Company {
    @Id
    private String id;
    private String name;
    private String description;
    private String link;

}
