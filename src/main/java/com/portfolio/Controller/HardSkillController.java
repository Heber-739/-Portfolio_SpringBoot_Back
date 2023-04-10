package com.portfolio.Controller;

import com.portfolio.DTO.HardSkillDTO;
import com.portfolio.Entity.HardSkill;
import com.portfolio.Entity.Image;
import com.portfolio.Entity.Tag;
import com.portfolio.Entity.Usser;
import com.portfolio.Security.Message;
import com.portfolio.Service.HardSkillService;
import com.portfolio.Service.ImageService;
import com.portfolio.Service.TagService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"https://heberportfolio.web.app", "*"})
@RestController
@RequestMapping("/hardskill")
public class HardSkillController {

    @Autowired
    UserService userService;

    @Autowired
    HardSkillService hsService;

    @Autowired
    TagService tagService;
    @Autowired
    ImageService imgService;

    @RequestMapping("/get")
    public ResponseEntity<List<HardSkill>> getDefault() {
        return new ResponseEntity(hsService.findAllByUsserUsername("Heber739"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/get/{id}")
    public ResponseEntity<List<HardSkill>> getAll(@PathVariable("id") String username) {
        return new ResponseEntity(hsService.findAllByUsserUsername(username), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create/{user_id}")
    public ResponseEntity<?> create(@PathVariable("user_id") String username, @RequestBody HardSkillDTO hsDto) {
        if (StringUtils.isBlank(hsDto.getTagDTO().getName())) {
            return new ResponseEntity(new Message("No se admiten campos en blanco"), HttpStatus.BAD_REQUEST);
        }
        List<String> hss_name = hsService.findAllByUsserUsername(username).stream().map(hs -> hs.getTag().getName()).collect(Collectors.toList());
        if (hss_name.contains(hsDto.getTagDTO().getName())) {
            return new ResponseEntity(new Message("El usuario ya posee este item"), HttpStatus.BAD_REQUEST);
        }
        Usser user = userService.findUsser(username);
        HardSkill hs = new HardSkill(hsDto.getPercentage());

        if (tagService.existsByName(hsDto.getTagDTO().getName())) {
            Tag tag = tagService.findByName(hsDto.getTagDTO().getName());
            hs.setTag(tag);
        } else {
            Image new_img = new Image(hsDto.getTagDTO().getName(), hsDto.getTagDTO().getImageDTO().getType(), hsDto.getTagDTO().getImageDTO().getBlobImg());
            imgService.save(new_img);
            Tag new_tag = new Tag(hsDto.getTagDTO().getName(), new_img);
            tagService.save(new_tag);
            hs.setTag(new_tag);
        }
        hsService.save(hs);
        user.addHardSkill(hs);
        return new ResponseEntity(hs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/{user_id}")
    public ResponseEntity<?> update(@PathVariable("user_id") String username, @RequestBody HardSkillDTO hsDto) {
        if (StringUtils.isBlank(hsDto.getTagDTO().getName())) {
            return new ResponseEntity(new Message("No se admiten campos en blanco"), HttpStatus.BAD_REQUEST);
        }
        List<String> hss = hsService.findAllByUsserUsername(username).stream().map(hs -> hs.getTag().getName()).collect(Collectors.toList());
        if (hss.contains(hsDto.getTagDTO().getName())) {
            return new ResponseEntity(new Message("El usuario ya posee el skill"), HttpStatus.BAD_REQUEST);
        }
        HardSkill hs = hsService.findByName(hsDto.getTagDTO().getName());
        if (tagService.existsByName(hsDto.getTagDTO().getName())) {
            Tag tag = tagService.findByName(hsDto.getTagDTO().getName());
            hs.setTag(tag);
        } else {
            Image new_img = new Image(hsDto.getTagDTO().getImageDTO().getName(), hsDto.getTagDTO().getImageDTO().getType(), hsDto.getTagDTO().getImageDTO().getBlobImg());
            imgService.save(new_img);
            Tag newTag = new Tag(hsDto.getTagDTO().getName(), new_img);
            tagService.save(newTag);
            hs.setTag(newTag);
        }
        hs.setPercentage(hsDto.getPercentage());
        hsService.save(hs);
        return new ResponseEntity(hs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete/{user_id}/{hs_id}")
    public ResponseEntity<Message> delete(@PathVariable("user_id") String username, @PathVariable("hs_id") int id) {
        List<Integer> ids = hsService.findAllByUsserUsername(username).stream().map(hs -> hs.getId()).collect(Collectors.toList());
        if (!ids.contains(id)) {
            return new ResponseEntity(new Message("No se puede eliminar el item"), HttpStatus.NOT_FOUND);
        }
        hsService.delete(id);
        return new ResponseEntity(new Message("Hard Skill eliminado"), HttpStatus.OK);
    }

}
