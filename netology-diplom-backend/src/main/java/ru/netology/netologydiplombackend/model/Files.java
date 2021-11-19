package ru.netology.netologydiplombackend.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "files")
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;
}
