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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hard_skill")
public class HardSkill implements Serializable {

    @Id
    @Column(name = "percentage", columnDefinition = "TINYINT")
    private int percentage;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "hard_skill_tag", joinColumns = @JoinColumn(name = "hard_skill_percentage", referencedColumnName = "percentage"), inverseJoinColumns = @JoinColumn(name = "tag_name", referencedColumnName = "name"))
    private Set<Tag> tags = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "hard_skill_usser", joinColumns = @JoinColumn(name = "hard_skill_percentage", referencedColumnName = "percentage"), inverseJoinColumns = @JoinColumn(name = "usser_username", referencedColumnName = "username"))
    private Set<Usser> ussers = new HashSet<>();

    public HardSkill() {
    }

    public HardSkill(int percentage) {
        this.percentage = percentage;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Usser> getUssers() {
        return ussers;
    }

    public void setUssers(Set<Usser> ussers) {
        this.ussers = ussers;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getSkills().add(this);
    }

}
