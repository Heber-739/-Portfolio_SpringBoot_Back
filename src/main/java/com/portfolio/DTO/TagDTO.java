package com.portfolio.DTO;

import javax.validation.constraints.NotNull;

public class TagDTO {

    @NotNull
    private String Name;
    @NotNull
    private String img;

    private int ed_id;

    public TagDTO() {
    }

    public TagDTO(String Name, String img) {
        this.Name = Name;
        this.img = img;
    }

    public String getName() {
        return Name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getEd_id() {
        return ed_id;
    }

    public void setEd_id(int ed_id) {
        this.ed_id = ed_id;
    }

}
