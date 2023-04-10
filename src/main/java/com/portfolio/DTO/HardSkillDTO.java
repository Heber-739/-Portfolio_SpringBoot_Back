package com.portfolio.DTO;

import javax.validation.constraints.NotBlank;

public class HardSkillDTO {

    private int id;
    @NotBlank
    private int percentage;
    @NotBlank
    private TagDTO tagDTO;

    public HardSkillDTO() {
    }

    public HardSkillDTO(int percentage) {
        this.percentage = percentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public TagDTO getTagDTO() {
        return tagDTO;
    }

    public void setTagDTO(TagDTO tagDTO) {
        this.tagDTO = tagDTO;
    }

}
