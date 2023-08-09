package com.team.springbatchuppercase.batch.processors;


import com.team.springbatchuppercase.domain.model.Car;
import com.team.springbatchuppercase.domain.model.CarTransformed;
import org.springframework.batch.item.ItemProcessor;
import com.team.springbatchuppercase.batch.config.BatchConfig;

/**
 * Replaced by inline method in the {@link BatchConfig } class
 */
public class CarProcessor implements ItemProcessor<Car, CarTransformed> {

    @Override
    public CarTransformed process(Car item) throws Exception {
        return new CarTransformed(item.getLicensePlate().toUpperCase(),item.getName().toUpperCase(), item.getPrice(),item.getAvailable());
    }
}
