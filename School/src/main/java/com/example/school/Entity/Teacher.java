package com.example.school.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "hometown")
    private String hometown;

    @Column(name = "gvcn")
    private boolean gvcn;


    public Teacher(String name, String hometown, boolean gvcn) {
        this.name = name;
        this.hometown = hometown;
        this.gvcn = gvcn;
    }
}
