package com.portfolio.Repository;

import com.portfolio.Entity.Job;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

    List<Job> findAllByUsserUsername(String username);
}
