package com.banking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.banking.core.entities.BaseEntity;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity<Long> {
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column
    private String description;
} 