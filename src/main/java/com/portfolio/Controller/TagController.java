package com.portfolio.Controller;

import com.portfolio.DTO.TagDTO;
import com.portfolio.Entity.Education;
import com.portfolio.Entity.Tag;
import com.portfolio.Security.Message;
import com.portfolio.Service.EducationService;
import com.portfolio.Service.TagService;
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
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagService tagService;
    @Autowired
    EducationService edService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/listAll")
    public ResponseEntity<List<Tag>> getAll() {
        return new ResponseEntity(tagService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/list/{education_id}")
    public ResponseEntity<List<Tag>> listAll(@PathVariable("education_id") int id) {
        return new ResponseEntity(edService.findById(id).getTags(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/add/{ed_id}/{tag_id}")
    public ResponseEntity<?> add(@PathVariable("ed_id") int ed_id, @PathVariable("tag_id") String tag_name) {
        List<String> tags = edService.findById(ed_id).getTags().stream().map(t -> t.getName()).collect(Collectors.toList());
        if (tags.contains(tag_name)) {
            return new ResponseEntity(new Message("La educación ya posee el item"), HttpStatus.BAD_REQUEST);
        }
        Education ed = edService.findById(ed_id);
        ed.addTag(tagService.findByName(tag_name));
        edService.save(ed);
        return new ResponseEntity(new Message("Contenido agregado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/remove/{ed_id}/{tag_id}")
    public ResponseEntity<Message> remove(@PathVariable("ed_id") int ed_id, @PathVariable("tag_id") String tag_name) {
        Education ed = edService.findById(ed_id);
        ed.removeTag(tag_name);
        edService.save(ed);
        return new ResponseEntity(new Message("Contenido removido"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create/{ed_id}")
    public ResponseEntity<?> create(@RequestBody TagDTO tagDto) {
        if (StringUtils.isBlank(tagDto.getName())) {
            return new ResponseEntity(new Message("No se admiten campos vacíos"), HttpStatus.BAD_REQUEST);
        }
        if (tagService.existsById(tagDto.getName())) {
            return new ResponseEntity(new Message("Ya existe este item"), HttpStatus.BAD_REQUEST);
        }
        List<String> tags = edService.findById(tagDto.getEd_id()).getTags().stream().map(t -> t.getName()).collect(Collectors.toList());
        if (tags.contains(tagDto.getName())) {
            return new ResponseEntity(new Message("La educación ya posee este contenido"), HttpStatus.BAD_REQUEST);
        }
        Tag tag = new Tag(tagDto.getName(), tagDto.getImg());
        tagService.save(tag);
        Education ed = edService.findById(tagDto.getEd_id());
        this.add(ed.getId(), tag.getName());
        return new ResponseEntity(tag, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable("id") String name, @RequestBody TagDTO tagDto) {
        if (StringUtils.isBlank(tagDto.getName())) {
            return new ResponseEntity(new Message("No se admiten campos vacíos"), HttpStatus.BAD_REQUEST);
        }
        if (tagService.existsById(tagDto.getName()) && name != tagDto.getName()) {
            return new ResponseEntity(new Message("Ya existe el item"), HttpStatus.BAD_REQUEST);
        }
        Tag tag = tagService.findByName(name);
        tag.setName(tagDto.getName());
        tagService.save(tag);
        return new ResponseEntity(tag, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete(@PathVariable("id") String id) {
        tagService.delete(id);
        return new ResponseEntity(new Message("Contenido eliminado"), HttpStatus.OK);
    }

}
