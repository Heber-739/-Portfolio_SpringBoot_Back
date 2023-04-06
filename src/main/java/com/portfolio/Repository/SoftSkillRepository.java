package com.portfolio.Repository;

import com.portfolio.Entity.SoftSkill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftSkillRepository extends JpaRepository<SoftSkill, Integer> {

    public boolean existsByName(String name);

    public List<SoftSkill> findAllByUserUsername(String username);
}
