package com.portfolio.DTO;

import javax.validation.constraints.NotNull;

public class ImageDTOtest {

    private int id;
    @NotNull
    private String name;
    @NotNull
    private String type;
    @NotNull
    private byte[] data;

    public ImageDTOtest() {
    }

    public ImageDTOtest(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
