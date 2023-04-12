package com.portfolio.Controller;

import com.portfolio.DTO.SoftSkillDTO;
import com.portfolio.Entity.SoftSkill;
import com.portfolio.Entity.Usser;
import com.portfolio.Security.Message;
import com.portfolio.Service.SoftSkillService;
import com.portfolio.Service.UsserService;
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
@RequestMapping("/softSkill")
public class SoftSkillController {

    @Autowired
    SoftSkillService ssService;
    @Autowired
    UsserService userService;

    @GetMapping("/get")
    public ResponseEntity<List<SoftSkill>> getDefault() {
        return new ResponseEntity(ssService.findAllByUsserUsername("Heber739"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/{id}")
    public ResponseEntity<List<SoftSkill>> getByUsser(@PathVariable("id") String username) {
        return new ResponseEntity(ssService.findAllByUsserUsername(username), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create/{user_id}")
    public ResponseEntity<?> create(@PathVariable("user_id") String username, @RequestBody SoftSkillDTO ssDto) {
        if (StringUtils.isBlank(ssDto.getName()) || StringUtils.isBlank(ssDto.getDescription())) {
            return new ResponseEntity(new Message("No se admiten campos en blanco"), HttpStatus.BAD_REQUEST);
        }
        List<String> ss_name = ssService.findAllByUsserUsername(username).stream().map(hs -> hs.getName()).collect(Collectors.toList());
        if (ss_name.contains(ssDto.getName())) {
            return new ResponseEntity(new Message("El usuario ya posee este item"), HttpStatus.BAD_REQUEST);
        }
        Usser user = userService.findUsser(username);
        SoftSkill softSk = new SoftSkill(ssDto.getName(), ssDto.getDescription());
        ssService.save(softSk);
        user.addSoftSkill(softSk);
        return new ResponseEntity(softSk, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/{user_id}")
    public ResponseEntity<?> update(@PathVariable("user_id") String username, @RequestBody SoftSkillDTO ssDto) {
        if (StringUtils.isBlank(ssDto.getName()) || StringUtils.isBlank(ssDto.getDescription())) {
            return new ResponseEntity(new Message("No se admiten campos en blanco"), HttpStatus.BAD_REQUEST);
        }
        SoftSkill softSkill = ssService.findById(ssDto.getId());
        softSkill.setName(ssDto.getName());
        softSkill.setDescription(ssDto.getDescription());
        ssService.save(softSkill);
        return new ResponseEntity(softSkill, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete/{user_id}/{ss_id}")
    public ResponseEntity<Message> delete(@PathVariable("user_id") String username, @PathVariable("ss_id") int id) {
        List<Integer> ids = ssService.findAllByUsserUsername(username).stream().map(ss -> ss.getId()).collect(Collectors.toList());
        if (!ids.contains(id)) {
            return new ResponseEntity(new Message("No se puede eliminar este item"), HttpStatus.BAD_REQUEST);
        }
        ssService.delete(id);
        return new ResponseEntity(new Message("Soft skill eliminado"), HttpStatus.OK);
    }

}
