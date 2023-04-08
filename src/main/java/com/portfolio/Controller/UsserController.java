package com.portfolio.Controller;

import com.portfolio.DTO.UsserDTO;
import com.portfolio.Entity.Image;
import com.portfolio.Entity.Usser;
import com.portfolio.Security.Message;
import com.portfolio.Service.ImageService;
import com.portfolio.Service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"https://heberportfolio.web.app", "*"})
@RestController
@RequestMapping("/user")
public class UsserController {

    @Autowired
    UserService userService;
    @Autowired
    ImageService imageService;

    @GetMapping("/get")
    public Usser getDefaultUsser() {
        return userService.findUsser("Heber739");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/{id}")
    public Usser getUsser(@PathVariable("id") String id) {
        return userService.findUsser(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public List<Usser> getAllUsser() {
        return userService.getAllUssers();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<?> createUsser(@RequestBody UsserDTO usser) {
        if (userService.existsByGithub(usser.getGithub())) {
            return new ResponseEntity(new Message("El Github ingresado ya se encuentra en uso"), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByLinkedin(usser.getLinkedin())) {
            return new ResponseEntity(new Message("El linkedin ingresado ya se encuentra en uso"), HttpStatus.BAD_REQUEST);
        }
        Image img = new Image(usser.getImg().getName(), usser.getImg().getType(), usser.getImg().getBlobImg());
        imageService.save(img);

        Usser user = new Usser(usser.getUsername(), usser.getName(),
                usser.getSurname(), usser.getAge(), usser.getDescription(),
                usser.getLinkedin(), usser.getGithub());
        user.setImg(img);
        userService.saveUsser(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deleteUsser(@PathVariable("id") String id) {
        userService.deleteUsser(id);
        return new ResponseEntity(new Message("Usuario eliminado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/edith")
    public ResponseEntity<?> edithUsser(@RequestBody UsserDTO usser) {
        if (userService.existsByGithub(usser.getGithub())) {
            Usser usergh = userService.findByGithub(usser.getGithub());
            if (usser.getUsername() != usergh.getUsername()) {
                return new ResponseEntity(new Message("El Github ingresado ya se encuentra en uso"), HttpStatus.BAD_REQUEST);
            }
        }
        if (userService.existsByLinkedin(usser.getLinkedin())) {
            Usser userlk = userService.findByLinkedin(usser.getLinkedin());
            if (usser.getUsername() != userlk.getUsername()) {
                return new ResponseEntity(new Message("El Linkedin ingresado ya se encuentra en uso"), HttpStatus.BAD_REQUEST);
            }
        }
        Usser user = userService.findUsser(usser.getUsername());
        if (usser.getImg().getId() < 1) {
            Image img = new Image(usser.getImg().getName(), usser.getImg().getType(), usser.getImg().getBlobImg());
            imageService.save(img);
            user.setImg(img);
        }
        user.setName(usser.getName());
        user.setSurname(usser.getSurname());
        user.setAge(usser.getAge());
        user.setDescription(usser.getDescription());
        user.setLinkedin(usser.getLinkedin());
        user.setGithub(usser.getGithub());
        userService.saveUsser(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }
}
