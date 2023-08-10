package com.team.springbatchuppercase.domain.controller;

import com.team.springbatchuppercase.domain.service.CarService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobLauncher jobLauncher;

    private final Job job;

    private final CarService carService;

    public JobController(JobLauncher jobLauncher, Job job, CarService carService){
        this.jobLauncher = jobLauncher;
        this.job = job;
        this.carService = carService;
    }



    @PostMapping("/start")
    public void runJobs() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        this.carService.insertTestData();

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("timeTaken", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(job, jobParameters);
    }
}

