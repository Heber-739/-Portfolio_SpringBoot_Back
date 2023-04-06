package com.portfolio.Service;

import com.portfolio.Entity.Tag;
import com.portfolio.Repository.TagRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TagService {

    @Autowired
    TagRepository tagRepository;

    public List<Tag> findAllByEducationsId(int id) {
        return tagRepository.findAllByEducationsId(id);
    }

    public Optional<Tag> findByName(String name) {
        return tagRepository.findByName(name);
    }

    public boolean existsByName(String name) {
        return tagRepository.existsByName(name);
    }

    public void save(Tag tag) {
        tagRepository.save(tag);
    }

    public void delete(int id) {
        tagRepository.deleteById(id);
    }
}
