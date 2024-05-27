package com.example.part1.model;
import jakarta.persistence.*;

import lombok.*;


import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private boolean enabled;




    // One User, Many Roles: A single user can have multiple roles assigned to them. For example, a user might be an "admin" and an "editor" simultaneously.
    // One Role, Many Users: A single role can be assigned to multiple users. For example, the "editor" role might be assigned to several different users within your system.

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

}
