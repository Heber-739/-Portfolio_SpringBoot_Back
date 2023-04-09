package com.portfolio.Controller;

import com.portfolio.DTO.HardSkillDTO;
import com.portfolio.Entity.HardSkill;
import com.portfolio.Entity.Image;
import com.portfolio.Entity.Usser;
import com.portfolio.Security.Message;
import com.portfolio.Service.HardSkillService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    ImageService imageService;

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
        if (StringUtils.isBlank(hsDto.getName())) {
            return new ResponseEntity(new Message("Revise el campo en blanco"), HttpStatus.BAD_REQUEST);
        }
        List<String> hss_name = hsService.findAllByUsserUsername(username).stream().map(hs -> hs.getName()).collect(Collectors.toList());
        if (hss_name.contains(hsDto.getName())) {
            return new ResponseEntity(new Message("El usuario ya posee este item"), HttpStatus.BAD_REQUEST);
        }
        Usser user = userService.findUsser(username);
        HardSkill hs = new HardSkill(hsDto.getName(), hsDto.getPercentage());
        if (imageService.existsByName(hsDto.getImg().getName())) {
            Image img = imageService.findByName(hsDto.getImg().getName());
            hs.setImg(img);
        } else {
            Image new_img = new Image(hsDto.getImg().getName(), hsDto.getImg().getType(), hsDto.getImg().getBlobImg());
            hs.setImg(new_img);
        }
        hsService.save(hs);
        user.addHardSkill(hs);
        return new ResponseEntity(hs, HttpStatus.OK);
    }
}
