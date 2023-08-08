package com.example.springbatch.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "car_uppercase")
@Getter
@Setter
public class CarUppercase {
    @Id
    @Column(name = "car_license_plate")
    private String carLicensePlate;

    @Column(name = "car_name")
    private String carName;
}
