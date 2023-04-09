package com.portfolio.Controller;

import com.portfolio.DTO.MailDTO;
import com.portfolio.Security.Message;
import com.portfolio.Service.MailerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"https://heberportfolio.web.app", "*"})
@RestController
public class MailController {

    @Autowired
    MailerService mailService;

    @PostMapping("/sendMail")
    public ResponseEntity<Message> sendMail(@RequestBody MailDTO mail) {
        if (StringUtils.isBlank(mail.getName()) || StringUtils.isBlank(mail.getMail()) || StringUtils.isEmpty(mail.getMessage())) {
            return new ResponseEntity(new Message("Revise el campo vacío y vuelva a intentarlo"), HttpStatus.BAD_REQUEST);
        }
        String message = "Nombre: " + mail.getName() + "\n\nMail: " + mail.getMail() + "\n\nMensaje:\n\n              " + mail.getMessage();
        mailService.sendMail(mail.getMail(), message);
        return new ResponseEntity(new Message("Mail enviado correctamente"), HttpStatus.OK);
    }
}
