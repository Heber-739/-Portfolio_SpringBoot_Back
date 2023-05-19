package com.portfolio.Repository;

import com.portfolio.Entity.Usser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsserRepository extends JpaRepository<Usser, String> {

    public boolean existsByLinkedin(String linkedin);

    public boolean existsByGithub(String github);

    public Optional<Usser> findByLinkedin(String linkedin);

    public Optional<Usser> findByGithub(String github);

    public boolean existsByUsername(String username);

    public Optional<Usser> findByUsername(String username);

    public void deleteByUsername(String username);

}
