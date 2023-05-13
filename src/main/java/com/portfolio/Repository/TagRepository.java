package com.portfolio.Repository;

import com.portfolio.Entity.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {

    public List<Tag> findAllByEducationsId(int id);
}
