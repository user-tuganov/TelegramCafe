package ru.tuganov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Role {
    @Id
    private Long id;
    private String name;
    @OneToOne(mappedBy = "role")
    @JsonIgnore
    private CafeEmployee cafeEmployee;
}
