package com.portfolio.Service;

import com.portfolio.Entity.Job;
import com.portfolio.Repository.JobRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JobService {

    @Autowired
    JobRepository jobRepository;

    public List<Job> findAllByUsserUsername(String username) {
        return jobRepository.findAllByUsserUsername(username);
    }

    public Job findById(int id) {
        return jobRepository.findById(id).orElse(null);
    }

    public void save(Job workExperience) {
        jobRepository.save(workExperience);
    }

    public void delete(int id) {
        jobRepository.deleteById(id);
    }
}
