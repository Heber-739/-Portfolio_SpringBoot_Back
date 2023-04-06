package com.portfolio.Service;

import com.portfolio.Entity.SoftSkill;
import com.portfolio.Repository.SoftSkillRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SoftSkillService {

    @Autowired
    SoftSkillRepository ssRepository;

    public List<SoftSkill> findAllByUsserUsername(String username) {
        return ssRepository.findAllByUsserUsername(username);
    }

    public void save(SoftSkill softSkill) {
        ssRepository.save(softSkill);
    }

    public void delete(int id) {
        ssRepository.deleteById(id);
    }
}
