package com.codegym.demo_spring_jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "VARCHAR(200)", nullable = false)
    private String name;

    @Column(name = "gender")
    private int gender;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassCG classCG;

    @OneToOne
    @JoinColumn(name = "username", unique = true)
    private Jame jame;
}
