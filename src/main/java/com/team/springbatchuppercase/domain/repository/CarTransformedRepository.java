package com.team.springbatchuppercase.domain.repository;

import com.team.springbatchuppercase.domain.model.CarTransformed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarTransformedRepository extends CrudRepository<CarTransformed,String> {
}
