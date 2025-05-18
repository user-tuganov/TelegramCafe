package ru.tuganov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Role {
    @Id
    private Long id;
    private String name;
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<CafeEmployee> cafeEmployee;
}
