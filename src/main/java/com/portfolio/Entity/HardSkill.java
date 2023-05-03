package com.portfolio.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hard_skill")
public class HardSkill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "percentage", columnDefinition = "TINYINT")
    private int percentage;

    @OneToOne(mappedBy = "skill", fetch = FetchType.EAGER)
    private Tag tag;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usser_username", referencedColumnName = "username", columnDefinition = "VARCHAR(16)")
    private Usser usser;

    public HardSkill() {
    }

    public HardSkill(int percentage) {
        this.percentage = percentage;
    }

    public int getId() {
        return id;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Usser getUsser() {
        return usser;
    }

    public void setUsser(Usser user) {
        this.usser = user;
    }

}
