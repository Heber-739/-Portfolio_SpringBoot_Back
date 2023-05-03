package com.portfolio.DTO;

import com.portfolio.Entity.Image;
import javax.validation.constraints.NotNull;

public class TagDTO {

    @NotNull
    private String Name;
    @NotNull
    private Image img;

    private int ed_id;

    public TagDTO() {
    }

    public TagDTO(String Name, Image img) {
        this.Name = Name;
        this.img = img;
    }

    public String getName() {
        return Name;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getEd_id() {
        return ed_id;
    }

    public void setEd_id(int ed_id) {
        this.ed_id = ed_id;
    }

}
