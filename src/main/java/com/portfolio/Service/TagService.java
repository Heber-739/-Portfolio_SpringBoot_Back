package com.portfolio.Service;

import com.portfolio.Entity.Tag;
import com.portfolio.Repository.TagRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TagService {

    @Autowired
    TagRepository tagRepository;

    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    public Tag findByName(String name) {
        return tagRepository.findById(name).orElse(null);
    }

    public boolean existsById(String name) {
        return tagRepository.existsById(name);
    }

    public void save(Tag tag) {
        tagRepository.save(tag);
    }

    public void delete(String id) {
        tagRepository.deleteById(id);
    }
}
