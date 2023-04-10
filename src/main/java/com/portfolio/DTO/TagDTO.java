package com.portfolio.DTO;

import javax.validation.constraints.NotNull;

public class TagDTO {

    @NotNull
    private String Name;
    @NotNull
    private ImageDTO imageDTO;

    private int ed_id;

    public TagDTO() {
    }

    public TagDTO(String Name, ImageDTO dto) {
        this.Name = Name;
        this.imageDTO = dto;
    }

    public String getName() {
        return Name;
    }

    public ImageDTO getImageDTO() {
        return imageDTO;
    }

    public void setImageDTO(ImageDTO imageDTO) {
        this.imageDTO = imageDTO;
    }

    public int getEd_id() {
        return ed_id;
    }

    public void setEd_id(int ed_id) {
        this.ed_id = ed_id;
    }

}
