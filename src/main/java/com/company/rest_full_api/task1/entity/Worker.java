package com.company.rest_full_api.task1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @OneToOne
    private Address address;
    @ManyToOne
    private Company company;

    public Worker(String name, String phoneNumber, Address address, Company company) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.company = company;
    }
}
