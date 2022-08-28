package com.example.school.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "hometown")
    private String hometown;

    @Column(name = "gpa")
    private double gpa;

    public Student(String name, String hometown, double gpa) {
        this.name = name;
        this.hometown = hometown;
        this.gpa = gpa;
    }


}
