package com.portfolio.Controller;

import com.portfolio.Entity.HardSkill;
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
        return new ResponseEntity(hsService.getByUsser("Heber739"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/get/{id}")
    public ResponseEntity<List<HardSkill>> getAll(@PathVariable("id") String username) {
        return new ResponseEntity(hsService.getByUsser(username), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create/{user_id}")
    public ResponseEntity<?> create(@PathVariable("user_id") String username, @RequestBody DtoHardSkills dtoHardSkill) {
        if (StringUtils.isBlank(dtoHardSkill.getName())) {
            return new ResponseEntity<Message>(new Message("Revise el campo en blanco"), HttpStatus.BAD_REQUEST);
        }
        if ((dtoHardSkill.getPercentage() % 5 != 0) || (dtoHardSkill.getPercentage() > 100)) {
            return new ResponseEntity<Message>(new Message("No se acepta el porcentage inresado, solo multiplos de 5"), HttpStatus.BAD_REQUEST);
        }
        if (!implementUserService.existsByUsername(username)) {
            return new ResponseEntity<Message>(new Message("No se pudo encontrar el usuario vinculado"), HttpStatus.BAD_REQUEST);
        }
        List<String> hss_name = sHardSkill.findAllByUsersUsername(username).stream().map(hs -> hs.getName()).collect(Collectors.toList());
        if (hss_name.contains(dtoHardSkill.getName())) {
            return new ResponseEntity<Message>(new Message("El usuario ya posee este item"), HttpStatus.BAD_REQUEST);
        }
        User user = implementUserService.findUser(username);
        List<HardSkill> hss = sHardSkill.findAllByName(dtoHardSkill.getName()).stream()
                .filter(hs -> hs.getPercentage() == dtoHardSkill.getPercentage()).collect(Collectors.toList());
        if (!hss.isEmpty()) {
            HardSkill hardS = hss.get(0);
            this.add(hardS.getId(), user.getUsername());
            return new ResponseEntity<HardSkill>(hardS, HttpStatus.OK);
        }
        HardSkill hardSk = new HardSkill(dtoHardSkill.getName(), dtoHardSkill.getPercentage(), dtoHardSkill.getImg(), dtoHardSkill.getType_img());
        sHardSkill.save(hardSk);
        this.add(hardSk.getId(), user.getUsername());
        return new ResponseEntity<HardSkill>(hardSk, HttpStatus.OK);
    }
}
