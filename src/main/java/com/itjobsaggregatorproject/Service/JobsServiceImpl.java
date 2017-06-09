package com.itjobsaggregatorproject.Service;

import com.itjobsaggregatorproject.Dao.JobDao;
import com.itjobsaggregatorproject.Entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JobsServiceImpl implements JobsService {
    @Autowired
    JobDao jobDao;

    @Transactional
    @Override
    public Optional<Job> getById(String id) {

        return jobDao.findById(id);
    }

    @Transactional
    @Override
    public void save(Job job) {

        jobDao.save(job);
    }

    @Transactional
    @Override
    public void update(Job job) {
        jobDao.save(job);
    }

    @Transactional
    @Override
    public void delete(Job job) {
        jobDao.delete(job);

    }

    @Transactional
    @Override
    public List<Job> getAll() {
        return jobDao.findAll();
    }
}
