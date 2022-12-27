package com.spring.model;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name="users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;


    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="age")
    private String age;

    @Column(name="address")
    private String address;

    @Column(name="active")
    private int active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_roles",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> roles;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_authorities",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="auth_id")}
    )
    private Set<Authorities> authorities;
}
