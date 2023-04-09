package com.portfolio.DTO;

import javax.validation.constraints.NotNull;

public class SoftSkillDTO {

    private int id;
    @NotNull
    private String name;

    @NotNull
    private String description;

    public SoftSkillDTO() {
    }

    public SoftSkillDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
