package com.portfolio.Controller;

import com.portfolio.DTO.EducationDTO;
import com.portfolio.Entity.Education;
import com.portfolio.Entity.Image;
import com.portfolio.Entity.Usser;
import com.portfolio.Security.Message;
import com.portfolio.Service.EducationService;
import com.portfolio.Service.ImageService;
import com.portfolio.Service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/education")
public class EducationController {

    @Autowired
    EducationService edService;

    @Autowired
    ImageService imageService;

    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("getAll")
    public ResponseEntity<List<Education>> getAll() {
        return new ResponseEntity(edService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Education>> getDefault() {
        return new ResponseEntity(edService.findAllByUserUsername("Heber739"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/{id}")
    public ResponseEntity<List<Education>> getByUsername(@PathVariable("id") String username) {
        return new ResponseEntity(edService.findAllByUserUsername(username), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create/{user_id}")
    public ResponseEntity<?> create(@PathVariable("user_id") String username, @RequestBody EducationDTO ed) {
        if (StringUtils.isBlank(ed.getName()) || StringUtils.isBlank(ed.getLink())) {
            return new ResponseEntity(new Message("Revise el campo vacío y vuelva a intentarlo"), HttpStatus.BAD_REQUEST);
        }
        List<String> edus = edService.findAllByUserUsername(username).stream().map(edu -> edu.getName()).collect(Collectors.toList());
        if (edus.contains(ed.getName())) {
            return new ResponseEntity(new Message("El usuario ya posee la educación"), HttpStatus.BAD_REQUEST);
        }
        Image img = new Image(ed.getImg().getName(), ed.getImg().getType(), ed.getImg().getBlobImg());
        imageService.save(img);
        Usser user = userService.findUsser(username);
        Education education = new Education(ed.getName(), ed.getLink(), ed.isDone());
        education.setImg(img);
        edService.save(education);
        user.addEducation(education);
        return new ResponseEntity(education, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete/{user_id}/{ed_id}")
    public ResponseEntity<Message> delete(@PathVariable("user_id") String username, @PathVariable("ed_id") int id) {
        List<Integer> ids = userService.findUsser(username).getEducations().stream().map(ed -> ed.getId()).collect(Collectors.toList());
        if (!ids.contains(id)) {
            return new ResponseEntity(new Message("No es posible eliminar esta educación"), HttpStatus.BAD_REQUEST);
        }
        edService.delete(id);
        return new ResponseEntity(new Message("Educación eliminada correctamente"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/{user_id}/{ed_id}")
    public ResponseEntity<?> update(@PathVariable("user_id") String username, @RequestBody EducationDTO edDto) {
        if (StringUtils.isBlank(edDto.getName()) || StringUtils.isBlank(edDto.getLink())) {
            return new ResponseEntity(new Message("Revise el campo en blanco"), HttpStatus.BAD_REQUEST);
        }
        if (!userService.existsByUsername(username)) {
            return new ResponseEntity(new Message("La educación ingresada no se puede vincular a un usuario inexistente"), HttpStatus.BAD_REQUEST);
        }
        List<String> eds = edService.findAllByUserUsername(username).stream().map(ed -> ed.getName()).collect(Collectors.toList());
        if (eds.contains(edDto.getName())) {
            return new ResponseEntity(new Message("El usuario ya posee esta educación"), HttpStatus.BAD_REQUEST);
        }
        Education ed = edService.findById(edDto.getId());
        if (edDto.getImg().getId() < 1) {
            Image img = new Image(edDto.getImg().getName(), edDto.getImg().getType(), edDto.getImg().getBlobImg());
            imageService.save(img);
            ed.setImg(img);
        }
        ed.setName(edDto.getName());
        ed.setName(edDto.getLink());
        ed.setDone(edDto.isDone());
        edService.save(ed);
        return new ResponseEntity(ed, HttpStatus.OK);
    }

}
