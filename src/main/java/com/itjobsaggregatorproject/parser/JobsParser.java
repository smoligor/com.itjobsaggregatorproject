package com.itjobsaggregatorproject.parser;

import com.itjobsaggregatorproject.entity.Job;

import java.util.List;

public interface JobsParser {

    List<Job> parseJobs(boolean isFirstParsingRoutine);
}