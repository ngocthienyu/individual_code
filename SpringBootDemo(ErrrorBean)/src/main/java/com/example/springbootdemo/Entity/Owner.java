package com.example.springbootdemo.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
@Data
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthdate;

    @Column(name = "address")
    @NotEmpty
    private String address;

    @Column(name = "telephone")
    @NotEmpty
    @Digits(fraction = 0, integer =10)
    private String telephone;

    @OneToMany(mappedBy = "owner")
    List<Pet> pets = new ArrayList<>();


    public Owner(String name, LocalDate birthdate, String address, String telephone, List<Pet> pets) {
        this.name = name;
        this.birthdate = birthdate;
        this.address = address;
        this.telephone = telephone;
        this.pets = pets;
    }

}
