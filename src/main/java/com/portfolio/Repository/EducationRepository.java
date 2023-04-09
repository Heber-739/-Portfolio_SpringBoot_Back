package com.portfolio.Repository;

import com.portfolio.Entity.Education;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {

    public List<Education> findAllByUsserUsername(String username);

    public Optional<Education> findByLink(String link);

    public boolean existsByLink(String link);

    public boolean existsById(int id);
}
