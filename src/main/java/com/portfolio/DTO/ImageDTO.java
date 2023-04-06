package com.portfolio.DTO;

import javax.validation.constraints.NotNull;

public class ImageDTO {

    private int id;
    @NotNull
    private String name;
    @NotNull
    private String type;
    @NotNull
    private byte[] blobImg;

    public ImageDTO() {
    }

    public ImageDTO(String name, String type, byte[] blobImg) {
        this.name = name;
        this.type = type;
        this.blobImg = blobImg;
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

    public byte[] getBlobImg() {
        return blobImg;
    }

    public void setBlobImg(byte[] blobImg) {
        this.blobImg = blobImg;
    }

}
