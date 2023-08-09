package com.team.springbatchuppercase.batch.config;


import com.team.springbatchuppercase.batch.listeners.JobCompletionNotificationListener;
import com.team.springbatchuppercase.batch.processors.CarProcessor;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarTransformedRepository carTransformedRepository;

    @Bean
    public RepositoryItemReader<Car> reader(){
        RepositoryItemReader<Car> reader = new RepositoryItemReader<>();
        reader.setRepository(carRepository);
        reader.setMethodName("findByAvailableTrue");

        Map<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("name", Sort.Direction.ASC);
        reader.setSort(sorts);
        return reader;
    }


    @Bean
    public RepositoryItemWriter<CarTransformed> writer(){
        RepositoryItemWriter<CarTransformed> writer = new RepositoryItemWriter<>();
        writer.setRepository(carTransformedRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public CarProcessor processor(){
        return new CarProcessor();
    }

    @Bean
    public Job convertCarToUppercaseJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener){
        return new JobBuilder("convertCarToUppercaseJob",jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager, RepositoryItemWriter<CarTransformed> writer){
        return new StepBuilder("step1",jobRepository)
                .<Car,CarTransformed> chunk(10,transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }


}
