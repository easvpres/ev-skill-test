package org.eas.controller;

import org.eas.dto.DaysToBirthdayLeftResponse;
import org.eas.dto.DaysToBirthdayLeftResponse.JobStatus;
import org.eas.exception.JobExecutionException;
import org.eas.service.AsyncBirthdayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/birthday")
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private AsyncBirthdayService asyncBirthdayService;

    @PostMapping("/start")
    @ResponseBody
    public String start(@RequestParam(required = false) Integer month) {
        logger.info("start(month={})", month);
        return asyncBirthdayService.createJob(month);
    }

    @GetMapping(path = "/result", produces = "application/json")
    @ResponseBody()
    public DaysToBirthdayLeftResponse result(@RequestParam String jobId) {
        logger.info("result(jobId={})", jobId);
        DaysToBirthdayLeftResponse response = new DaysToBirthdayLeftResponse();
        if (!asyncBirthdayService.isJobExist(jobId)) {
            logger.info("job={} not found", jobId);
            response.setJobStatus(JobStatus.JOB_NOT_FOUND);

        } else if (!asyncBirthdayService.isJobFinished(jobId)) {
            logger.info("job={} in progress", jobId);
            response.setJobStatus(JobStatus.IN_PROGRESS);

        } else {
            try {
                response.setData(asyncBirthdayService.getJobResult(jobId));
                response.setJobStatus(JobStatus.DONE);
            } catch (JobExecutionException e) {
                logger.error("job={} error", jobId, e);
                response.setJobStatus(JobStatus.FAILED);
            } catch (Exception e) {
                logger.error("job={} error", jobId, e);
                response.setJobStatus(JobStatus.FAILED);
            }
        }
        return response;
    }
}