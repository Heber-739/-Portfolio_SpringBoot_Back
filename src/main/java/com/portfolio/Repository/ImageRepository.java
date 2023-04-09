package com.portfolio.Repository;

import com.portfolio.Entity.Image;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    public Optional<Image> findByName(String name);

    public boolean existsByName(String name);
}
