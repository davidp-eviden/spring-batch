package com.team.springbatchuppercase.domain;

import com.team.springbatchuppercase.domain.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Transactional
    public void insertTestData(){
        this.carRepository.insertData();
    }
}
