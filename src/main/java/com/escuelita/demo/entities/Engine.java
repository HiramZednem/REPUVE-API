package com.escuelita.demo.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "Engines")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cylinder;

    @Column(nullable = false)
    private String engineType;

    @OneToOne(mappedBy = "engine")
    private Vehicle vehicle;

}
