package com.portfolio.Controller;

import com.portfolio.DTO.ImageDTOtest;
import com.portfolio.Entity.Image;
import com.portfolio.Security.Message;
import com.portfolio.Service.ImageService;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"https://heberportfolio.web.app", "*"})
@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ImageDTOtest img) {
        try {
            Image im = new Image();
            im.setName(img.getName());
            im.setType(img.getType());

            byte[] bytes = Base64.getEncoder().encode(img.getData().getBytes());
            byte[] decodedString = Base64.getDecoder().decode(new String(bytes).getBytes("UTF-8"));

            im.setData_img(decodedString);
            imageService.save(im);
            return new ResponseEntity(im, HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            return new ResponseEntity(new Message("Error: " + e), HttpStatus.BAD_REQUEST);
        }
    }

}
