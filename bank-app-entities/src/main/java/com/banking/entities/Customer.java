package com.banking.entities;

import com.banking.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "customer_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Customer extends BaseEntity<Long> {

    @Column(name = "customer_number", nullable = false, unique = true)
    private String customerNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "is_active")
    private boolean isActive = true;
} 