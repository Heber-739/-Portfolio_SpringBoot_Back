package com.portfolio.Repository;

import com.portfolio.Entity.Tag;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {

    public boolean existsByName(String name);

    public Optional<Tag> findByName(String name);

}
