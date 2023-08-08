package com.example.springbatch.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "car")
@Getter
@Setter
public class Car {
    @Id
    @Column(name = "car_license_plate")
    private String carLicensePlate;

    @Column(name = "car_name")
    private String carName;
}
