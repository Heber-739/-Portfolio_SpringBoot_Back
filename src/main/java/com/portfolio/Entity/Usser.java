package com.portfolio.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usser")
public class Usser implements Serializable {

    @Id
    @Column(name = "username", columnDefinition = "VARCHAR(16)")
    private String username;

    @Column(name = "name", columnDefinition = "VARCHAR(25)")
    private String name;

    @Column(name = "surname", columnDefinition = "VARCHAR(25)")
    private String surname;

    @Column(name = "age", columnDefinition = "TINYINT")
    private int age;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "linkedin", columnDefinition = "VARCHAR(225)")
    private String linkedin;

    @Column(name = "github", columnDefinition = "VARCHAR(225)")
    private String github;

    @OneToOne(mappedBy = "usser")
    private Image img;

    @JsonIgnore
    @OneToMany(mappedBy = "usser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Education> educations = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<HardSkill> hskills = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SoftSkill> sskills = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Job> jobs = new HashSet<>();

    public Usser() {
    }

    public Usser(String username, String name, String surname, int age, String description, String linkedin, String github) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.description = description;
        this.linkedin = linkedin;
        this.github = github;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Set<Education> getEducations() {
        return educations;
    }

    public void setEducations(Set<Education> educations) {
        this.educations = educations;
    }

    public Set<HardSkill> getHskills() {
        return hskills;
    }

    public void setHskills(Set<HardSkill> hskills) {
        this.hskills = hskills;
    }

    public Set<SoftSkill> getSskills() {
        return sskills;
    }

    public void setSskills(Set<SoftSkill> sskills) {
        this.sskills = sskills;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

}
