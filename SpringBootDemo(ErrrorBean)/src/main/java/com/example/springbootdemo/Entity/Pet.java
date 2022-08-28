package com.example.springbootdemo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @Column(name = "name")
    private int name;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthdate;

    @Column(name = "type")
    private String type;
}
