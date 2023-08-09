package com.team.springbatchuppercase.domain.repository;

import com.team.springbatchuppercase.domain.model.Car;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car,String> {
    List<Car> findByAvailableTrue();
}
