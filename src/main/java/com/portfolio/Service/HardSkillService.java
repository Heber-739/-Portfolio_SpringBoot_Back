package com.portfolio.Service;

import com.portfolio.Entity.HardSkill;
import com.portfolio.Repository.HardSkillRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HardSkillService {

    @Autowired
    HardSkillRepository hsR;

    public List<HardSkill> getByUsser(String username) {
        return hsR.findAllByUsserUsername(username);
    }

    public void save(HardSkill hs) {
        hsR.save(hs);
    }

    public void delete(int id) {
        hsR.deleteById(id);
    }

    public boolean existsById(int id) {
        return hsR.existsById(id);
    }

}
