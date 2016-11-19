package org.eas.dto;

import java.util.Collections;
import java.util.List;

/**
 * @author eas
 */
public class DaysToBirthdayLeftResponse {

    public enum JobStatus {
        DONE,
        IN_PROGRESS,
        JOB_NOT_FOUND,
        FAILED
    }

    private JobStatus jobStatus;

    private List<DaysToBirthdayLeft> data = Collections.emptyList();

    public DaysToBirthdayLeftResponse() {
    }

    public DaysToBirthdayLeftResponse(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }



    public List<DaysToBirthdayLeft> getData() {
        return data;
    }

    public void setData(List<DaysToBirthdayLeft> data) {
        this.data = data;
    }
}
