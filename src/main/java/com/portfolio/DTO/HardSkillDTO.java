package com.portfolio.DTO;

import com.portfolio.Entity.Tag;
import javax.validation.constraints.NotBlank;

public class HardSkillDTO {

    private int id;
    @NotBlank
    private int percentage;
    @NotBlank
    private Tag tag;

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

    public Tag getTag() {
        return tag;
    }

    public void setTagDTO(Tag tag) {
        this.tag = tag;
    }

}
