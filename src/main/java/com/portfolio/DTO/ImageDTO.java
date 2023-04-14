package com.portfolio.DTO;

import javax.validation.constraints.NotNull;

public class ImageDTO {

    private int id;
    @NotNull
    private String name;
    @NotNull
    private String type;
    @NotNull
    private byte[] data_img;

    public ImageDTO() {
    }

    public ImageDTO(String name, String type, byte[] base64) {
        this.name = name;
        this.type = type;
        this.data_img = base64;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData_img() {
        return data_img;
    }

    public void setData_img(byte[] data_img) {
        this.data_img = data_img;
    }

}
