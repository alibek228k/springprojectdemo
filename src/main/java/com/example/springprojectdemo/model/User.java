package com.example.springprojectdemo.model;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @NotNull(message="First name is compulsory")
    @Column(name = "first_name")
    private String first_name;

    @NotNull(message="Last name is compulsory")
    @Column(name = "last_name")
    private String last_name;

    @NotNull(message="Email is compulsory")
    @Email(message = "Email is invalid")
    @Column(name = "email")
    private String email;

    @NotNull(message="Password is compulsory")
    @Length(min = 5, message = "Password should be at least 5 characters")
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private String status;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
    private Set<Role> roles;


}
