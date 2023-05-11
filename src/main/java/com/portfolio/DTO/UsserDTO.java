package com.portfolio.DTO;

import javax.validation.constraints.NotNull;

public class UsserDTO {

    @NotNull
    private String username;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private int age;
    @NotNull
    private String description;
    @NotNull
    private String linkedin;
    @NotNull
    private String github;
    @NotNull
    private String img;

    public UsserDTO() {
    }

    public UsserDTO(String username, String name, String surname, int age, String description, String linkedin, String github, String img) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.description = description;
        this.linkedin = linkedin;
        this.github = github;
        this.img = img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
