package com.team.springbatchuppercase.batch.processors;


import com.team.springbatchuppercase.domain.model.Car;
import com.team.springbatchuppercase.domain.model.CarTransformed;
import com.team.springbatchuppercase.domain.repository.CarRepository;
import com.team.springbatchuppercase.domain.repository.CarTransformedRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class CarProcessor implements ItemProcessor<Car, CarTransformed> {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarTransformedRepository carTransformedRepository;


    public CarProcessor(){
        super();
    }

    @Override
    public CarTransformed process(Car item) throws Exception {

        item.setName("Civic");
        item.setLicensePlate("5678sbc");

        carRepository.save(item);

        final String licensePlate = item.getLicensePlate().toUpperCase();
        final String name = item.getName().toUpperCase();

        CarTransformed itemTransformed = new CarTransformed(licensePlate, name);

        //carTransformedRepository.save(itemTransformed);

        return itemTransformed;
    }
}
