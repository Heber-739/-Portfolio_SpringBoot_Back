package com.portfolio.Service;

import com.portfolio.Entity.Usser;
import com.portfolio.Repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userR;

    public List<Usser> getAllUssers() {
        return userR.findAll();
    }

    public void saveUsser(Usser user) {
        userR.save(user);
    }

    public void deleteUsser(String id) {
        userR.deleteById(id);
    }

    public Usser findUsser(String username) {
        return userR.findByUsername(username).orElse(null);
    }

    public boolean existsByUsername(String username) {
        return userR.existsByUsername(username);
    }

    public boolean existsByLinkedin(String linkedin) {
        return userR.existsByLinkedin(linkedin);
    }

    public boolean existsByGithub(String github) {
        return userR.existsByGithub(github);
    }

    public Usser findByLinkedin(String linkedin) {
        return userR.findByLinkedin(linkedin).orElse(null);
    }

    public Usser findByGithub(String github) {
        return userR.findByGithub(github).orElse(null);
    }

}
