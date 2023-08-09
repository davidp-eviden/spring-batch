package com.team.springbatchuppercase.batch.config;


import com.team.springbatchuppercase.batch.listeners.JobCompletionNotificationListener;
import com.team.springbatchuppercase.domain.model.Car;
import com.team.springbatchuppercase.domain.model.CarTransformed;
import com.team.springbatchuppercase.domain.repository.CarRepository;
import com.team.springbatchuppercase.domain.repository.CarTransformedRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Collections;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarTransformedRepository carTransformedRepository;


    @Bean
    public RepositoryItemReader<Car> reader() {
        return new RepositoryItemReaderBuilder<Car>()
                .name("start")
                .repository(carRepository)
                .methodName("findAll")
                .sorts(Collections.singletonMap("name", Sort.Direction.ASC))
                .build();
    }


    @Bean
    public RepositoryItemWriter<CarTransformed> writer() {
        return new RepositoryItemWriterBuilder<CarTransformed>()
                .repository(carTransformedRepository)
                .methodName("save")
                .build();
    }

    @Bean
    public Job convertCarToUppercaseJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("convertCarToUppercaseJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
                .<Car, CarTransformed>chunk(10, transactionManager)
                .reader(reader())
                .processor(car -> new CarTransformed(car.getLicensePlate().toUpperCase(), car.getName().toUpperCase(), car.getPrice(), car.getAvailable()))
                .writer(writer())
                .build();
    }


}
