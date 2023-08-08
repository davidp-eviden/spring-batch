package com.team.springbatchuppercase.domain.repository;

import com.team.springbatchuppercase.domain.model.Car;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car,String> {
}
