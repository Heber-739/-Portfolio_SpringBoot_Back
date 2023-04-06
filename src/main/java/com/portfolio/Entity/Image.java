package com.portfolio.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(name = "type", columnDefinition = "VARCHAR(25)")
    private String type;

    @Column(name = "blobImg", columnDefinition = "MEDIUMBLOB")
    private byte[] blobImg;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "img", cascade = CascadeType.ALL)
    private Set<HardSkill> skills = new HashSet<>();

    public Image(String name, String type, byte[] blobImg) {
        this.name = name;
        this.type = type;
        this.blobImg = blobImg;
    }

    public Image() {
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
