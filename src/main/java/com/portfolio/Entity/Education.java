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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "education")
public class Education implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(name = "link", columnDefinition = "VARCHAR(200)")
    private String link;

    @Column(name = "done", columnDefinition = "BIT")
    private boolean done;

    @OneToOne(mappedBy = "education", cascade = CascadeType.ALL)
    private Image img;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "education_tag", joinColumns = @JoinColumn(name = "education_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tag_name", referencedColumnName = "name"))
    private Set<Tag> tags = new HashSet<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usser_username", referencedColumnName = "username")
    private Usser usser;

    public Education() {
    }

    public Education(String name, String link, boolean done) {
        this.name = name;
        this.link = link;
        this.done = done;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
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

    public void setUsser(Usser usser) {
        this.usser = usser;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getEducations().add(this);
    }

    public void removeTag(String name) {
        Tag tag = this.tags.stream().filter(t -> t.getName() == name).findFirst().orElse(null);
        if (tag != null) {
            this.tags.remove(tag);
            tag.getEducations().remove(this);
        }
    }

}
