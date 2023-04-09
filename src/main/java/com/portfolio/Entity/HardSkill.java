package com.portfolio.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hard_skill")
public class HardSkill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(name = "percentage", columnDefinition = "TINYINT")
    private int percentage;

    @ManyToOne
    @JoinColumn(name = "img_id", referencedColumnName = "id", columnDefinition = "INT")
    private Image img;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usser_username", referencedColumnName = "username", columnDefinition = "VARCHAR(16)")
    private Usser usser;

    public HardSkill() {
    }

    public HardSkill(String name, int percentage) {
        this.name = name;
        this.percentage = percentage;
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

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Usser getUsser() {
        return usser;
    }

    public void setUsser(Usser user) {
        this.usser = user;
    }

}
