package com.itjobsaggregatorproject.Parser;

import com.itjobsaggregatorproject.Entity.Job;

import java.util.List;

public interface JobsParser {

    List<Job> parseJobs(boolean isFirstParsingRoutine);
}