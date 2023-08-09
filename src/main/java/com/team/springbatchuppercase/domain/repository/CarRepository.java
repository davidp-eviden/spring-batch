package com.team.springbatchuppercase.domain.repository;

import com.team.springbatchuppercase.domain.model.Car;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, String> {


    @Query(value =
            "INSERT INTO car " +
                    "values " +
                    "('aa1',1,'honda civic',150.25)," +
                    "('aa2',1,'tesla',100.99)," +
                    "('aa3',1,'seat ibiza',255.99)", nativeQuery = true)
    @Modifying
    void insertData();
}
