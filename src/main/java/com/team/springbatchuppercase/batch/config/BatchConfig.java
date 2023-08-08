package com.team.springbatchuppercase.batch.config;

import com.team.springbatchuppercase.batch.processors.CarProcessor;
import com.team.springbatchuppercase.domain.model.Car;
import com.team.springbatchuppercase.domain.model.CarTransformed;
import com.team.springbatchuppercase.domain.repository.CarRepository;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {

    @Bean
    public FlatFileItemReader<Car> reader() {
        return new FlatFileItemReaderBuilder<Car>()
                .name("carItemReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .delimited()
                .names(new String[]{"car_license_plate", "car_name"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Car>() {{
                    setTargetType(Car.class);
                }})
                .build();
    }

    @Bean
    public CarProcessor processor() {
        return new CarProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<CarTransformed> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<CarTransformed>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO car_uppercase (car_license_plate, car_name) VALUES (:licensePlate, :name)")
                .dataSource(dataSource)
                .build();
    }
}
