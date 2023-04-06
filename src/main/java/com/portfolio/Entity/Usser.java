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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class Usser implements Serializable {

    @Id
    @Column(name = "username", columnDefinition = "VARCHAR(16)")
    private String username;

    @Column(name = "name", columnDefinition = "VARCHAR(25)")
    private String name;

    @Column(name = "surname", columnDefinition = "VARCHAR(25)")
    private String surname;

    @Column(name = "age", columnDefinition = "TINYINT")
    private int age;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "linkedin", columnDefinition = "VARCHAR(225)")
    private String linkedin;

    @Column(name = "github", columnDefinition = "VARCHAR(225)")
    private String github;

    @OneToOne(mappedBy = "usser")
    private Image img;

    @JsonIgnore
    @OneToMany(mappedBy = "usser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Education>  = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<HardSkill> hskills = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SoftSkill> sskills = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Job> jobs = new HashSet<>();

}
