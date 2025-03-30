package ru.tuganov.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class CafeEmployee {
    @Id
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @OneToOne
    private Role role;
    @ManyToOne
    private CafeAddress cafeAddress;
}
