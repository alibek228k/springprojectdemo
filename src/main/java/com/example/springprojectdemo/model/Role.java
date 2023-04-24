package com.example.springprojectdemo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "auth_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "auth_role_id")
    private Long id;

    @Column(name = "role_name")
    private String role;

    @Column(name = "role_desc")
    private String desc;


}
