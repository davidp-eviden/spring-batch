package com.team.springbatchuppercase.domain.repository;

import com.team.springbatchuppercase.domain.model.Car;
import com.team.springbatchuppercase.domain.model.CarTransformed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarTransformedRepository extends JpaRepository<CarTransformed, String> {
}
