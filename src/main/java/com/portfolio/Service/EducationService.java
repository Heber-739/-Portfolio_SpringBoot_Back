package com.portfolio.Service;

import com.portfolio.Entity.Education;
import com.portfolio.Repository.EducationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EducationService {

    @Autowired
    EducationRepository edRepository;

    public List<Education> getAll() {
        return edRepository.findAll();
    }

    public List<Education> findAllByUserUsername(String username) {
        return edRepository.findAllByUsserUsername(username);
    }

    public Education findByLink(String link) {
        return edRepository.findByLink(link).orElse(null);
    }

    public boolean existsByLink(String link) {
        return edRepository.existsByLink(link);
    }

    public void save(Education education) {
        edRepository.save(education);
    }

    public void delete(int id) {
        edRepository.deleteById(id);
    }

}
