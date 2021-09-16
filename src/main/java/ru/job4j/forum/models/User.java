package ru.job4j.forum.models;

import java.util.Set;

public class User {
    private int id;
    private String username;
    private String password;
    private boolean active;
    private Set<Role> roles;
}
