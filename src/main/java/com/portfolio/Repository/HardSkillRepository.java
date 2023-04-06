package com.portfolio.Repository;

import com.portfolio.Entity.HardSkill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardSkillRepository extends JpaRepository<HardSkill, Integer> {

    public List<HardSkill> findAllByUserUsername(String username);
}
