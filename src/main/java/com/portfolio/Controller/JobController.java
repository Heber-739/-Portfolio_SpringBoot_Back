package com.portfolio.Controller;

import com.portfolio.DTO.JobDTO;
import com.portfolio.Entity.Job;
import com.portfolio.Entity.Usser;
import com.portfolio.Security.Message;
import com.portfolio.Service.JobService;
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
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobService jobService;
    @Autowired
    UsserService userService;

    @GetMapping("/get")
    public ResponseEntity<List<Job>> getDefault() {
        return new ResponseEntity(jobService.findAllByUsserUsername("Heber739"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> list(@PathVariable("id") String username) {
        return new ResponseEntity<>(jobService.findAllByUsserUsername(username), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create/{user_id}")
    public ResponseEntity<?> create(@PathVariable("user_id") String username, @RequestBody JobDTO jobDto) {
        if (StringUtils.isBlank(jobDto.getName()) || StringUtils.isBlank(jobDto.getDescription())) {
            return new ResponseEntity(new Message("No se admiten campos vacíos"), HttpStatus.BAD_REQUEST);
        }
        List<String> jobs = jobService.findAllByUsserUsername(username).stream().map(exp -> exp.getName()).collect(Collectors.toList());
        if (jobs.contains(jobDto.getName())) {
            return new ResponseEntity(new Message("El usuario ya posee este item"), HttpStatus.BAD_REQUEST);
        }
        Usser user = userService.findUsser(username);
        Job job = new Job(jobDto.getName(), jobDto.getDescription());
        jobService.save(job);
        user.addJob(job);
        return new ResponseEntity(job, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/{user_id}/{job_id}")
    public ResponseEntity<Message> update(@PathVariable("user_id") String username, @PathVariable("job_id") int jobId, @RequestBody JobDTO jobDto) {
        if (StringUtils.isBlank(jobDto.getName()) || StringUtils.isBlank(jobDto.getDescription())) {
            return new ResponseEntity(new Message("No se admiten campos vacíos"), HttpStatus.BAD_REQUEST);
        }
        List<Integer> ids = jobService.findAllByUsserUsername(username).stream().map(i -> i.getId()).collect(Collectors.toList());
        if (!ids.contains(jobId)) {
            return new ResponseEntity(new Message("No se encuentra la experiencia"), HttpStatus.NOT_FOUND);
        }
        Job job = jobService.findById(jobId);
        job.setName(jobDto.getName());
        job.setDescription((jobDto.getDescription()));
        jobService.save(job);
        userService.findUsser(username).addJob(job);
        return new ResponseEntity(job, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete(@PathVariable("id") int id) {
        jobService.delete(id);
        return new ResponseEntity(new Message("Experiencia eliminada"), HttpStatus.OK);
    }

}
