package com.itjobsaggregatorproject;

import com.itjobsaggregatorproject.cache.JobCache;
import com.itjobsaggregatorproject.entity.Job;
import com.itjobsaggregatorproject.parser.JobParser;
import com.itjobsaggregatorproject.service.JobService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Qualifier("workUa")
    @Autowired
    JobParser jobParser;
    @Autowired
    JobService jobService;
    @Autowired
    JobCache jobCache;

    @Test
    public void contextLoads() {
//        try {
//            sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }



    }
}
