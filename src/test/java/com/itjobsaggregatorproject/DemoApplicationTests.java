package com.itjobsaggregatorproject;

import com.itjobsaggregatorproject.parser.JobParser;
import com.itjobsaggregatorproject.service.JobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    JobParser jobParser;
    @Autowired
    JobService jobService;

    @Test
    public void contextLoads() {

    }
}
