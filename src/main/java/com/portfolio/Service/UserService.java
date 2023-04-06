package com.portfolio.Service;

import com.portfolio.Entity.User;
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

    public List<User> getUsers() {
        return userR.findAll();
    }

    public void saveUser(User user) {
        userR.save(user);
    }

    public void deleteUser(String id) {
        userR.deleteById(id);
    }

    public User findUser(String username) {
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

    public User findByLinkedin(String linkedin) {
        return userR.findByLinkedin(linkedin).orElse(null);
    }

    public User findByGithub(String github) {
        return userR.findByGithub(github).orElse(null);
    }

}
