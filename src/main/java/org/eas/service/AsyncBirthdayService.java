package org.eas.service;

import org.eas.dto.DaysToBirthdayLeft;
import org.eas.exception.JobExecutionException;
import org.eas.exception.JobInProgressException;
import org.eas.exception.NoSuchJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author eas
 */
@Service
public class AsyncBirthdayService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncBirthdayService.class);

    @Autowired
    private PersonService personService;

    private ConcurrentMap<String, Future<List<DaysToBirthdayLeft>>> map = new ConcurrentHashMap<>();

    @Value("${async.birthday.service.thread.count}")
    private int threadCount;

    @Value("${async.birthday.service.test.delay.millis}")
    private int testJobDelay;

    private ExecutorService executorService;

    @PostConstruct
    private void postConstruct() {
        executorService = Executors.newFixedThreadPool(threadCount);
    }

    /**
     * @return jobId
     */
    public String createJob(Integer month) {
        logger.info("createJob(month={})", month);
        Future<List<DaysToBirthdayLeft>> future = executorService.submit(() -> {
            Thread.sleep(testJobDelay);
            LocalDate now = LocalDate.now();
            return personService.getDaysToBirthdayLeft(now, month != null ? month : now.getMonthValue());
        });
        String jobId = UUID.randomUUID().toString();
        map.put(jobId, future);
        return jobId;
    }

    public boolean isJobExist(String jobId) {
        logger.info("isJobExist(jobId={})", jobId);
        return map.containsKey(jobId);
    }

    public boolean isJobFinished(String jobId) throws NoSuchJobException {
        logger.info("isJobFinished(jobId={})", jobId);
        Future<List<DaysToBirthdayLeft>> future = map.get(jobId);
        if (future == null) {
            throw new NoSuchJobException();
        }
        return future.isDone();
    }

    public List<DaysToBirthdayLeft> getJobResult(String jobId) throws NoSuchJobException, JobInProgressException, JobExecutionException {
        logger.info("getJobResult(jobId={})", jobId);
        Future<List<DaysToBirthdayLeft>> future = map.get(jobId);
        if (future == null) {
            throw new NoSuchJobException();
        }
        if (!future.isDone()) {
            throw new JobInProgressException();
        }
        try {
            return future.get();
        } catch (InterruptedException e) {
            logger.error("error", e);
            throw new JobExecutionException();
        } catch (ExecutionException e) {
            logger.error("error", e);
            throw new JobExecutionException();
        }
    }

}
