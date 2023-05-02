package com.portfolio.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag implements Serializable {

    @Id
    @Column(name = "name", columnDefinition = "VARCHAR(150)", unique = true)
    private String name;

    @OneToOne
    @JoinColumn(name = "img_id", referencedColumnName = "id", columnDefinition = "INT")
    private Image img;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private Set<Education> educations = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<HardSkill> skills = new HashSet<>();

    public Tag() {
    }

    public Tag(String name, Image img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Education> getEducations() {
        return educations;
    }

    public void setEducations(Set<Education> educations) {
        this.educations = educations;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Set<HardSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<HardSkill> skills) {
        this.skills = skills;
    }

}
