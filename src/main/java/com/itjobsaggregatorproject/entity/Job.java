package com.itjobsaggregatorproject.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Job {
    @Id
    private String id;
    private int index;
    private String header;
    private String description;
    private String city;
    private String country;
    private String salary;
    private Company company;
    private String link;
    private String contact;
    private String contactNumber;
    private String sendResumeLink;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (!header.equals(job.header)) return false;
        return description.equals(job.description);

    }

    @Override
    public int hashCode() {
        int result = header.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
