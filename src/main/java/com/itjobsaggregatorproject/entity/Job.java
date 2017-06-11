package com.itjobsaggregatorproject.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
public class Job {
    @Id
    String id;
    int index;
    String header;
    String description;
    String city;
    String country;
    Company company;
    String link;
    String contact;
    String contactNumber;
    String sendResumeLink;

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
