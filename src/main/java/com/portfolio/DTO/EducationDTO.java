package com.portfolio.DTO;

import javax.validation.constraints.NotBlank;

public class EducationDTO {

    private int id;
    @NotBlank
    private String name;
    @NotBlank
    private String link;
    @NotBlank
    private boolean done;
    @NotBlank
    private String img;

    public EducationDTO() {
    }

    public EducationDTO(String name, String link, boolean done, String img) {
        this.name = name;
        this.link = link;
        this.done = done;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
