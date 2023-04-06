package com.portfolio.Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements Serializable {

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

}
