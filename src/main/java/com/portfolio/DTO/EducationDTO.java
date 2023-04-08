package com.portfolio.DTO;

import javax.validation.constraints.NotBlank;

public class EducationDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String link;
    @NotBlank
    private boolean finish;
    @NotBlank
    private ImageDTO img;

    public EducationDTO() {
    }

    public EducationDTO(String name, String link, boolean finish, ImageDTO img) {
        this.name = name;
        this.link = link;
        this.finish = finish;
        this.img = img;
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

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public ImageDTO getImg() {
        return img;
    }

    public void setImg(ImageDTO img) {
        this.img = img;
    }

}
