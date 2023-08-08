package com.team.springbatchuppercase.domain.repository;

import com.team.springbatchuppercase.domain.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, String> {
}
