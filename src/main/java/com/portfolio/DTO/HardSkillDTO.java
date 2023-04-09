package com.portfolio.DTO;

import javax.validation.constraints.NotBlank;

public class HardSkillDTO {

    @NotBlank
    private String name;
    @NotBlank
    private int percentage;
    @NotBlank
    private ImageDTO img;

    public HardSkillDTO() {
    }

    public HardSkillDTO(String name, int percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public ImageDTO getImg() {
        return img;
    }

    public void setImg(ImageDTO img) {
        this.img = img;
    }

}
