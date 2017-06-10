package com.itjobsaggregatorproject;

import com.itjobsaggregatorproject.Parser.JobsParser;
import com.itjobsaggregatorproject.Service.JobsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    JobsParser jobsParser;
    @Autowired
    JobsService jobsService;

    @Test
    public void contextLoads() {

    }
}
