package com.shree.batch.config.person;

import com.shree.batch.dao.entity.PersonEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private final JdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution){
        if(jobExecution.getStatus()== BatchStatus.COMPLETED){
            jdbcTemplate.query("SELECT first_name,last_name FROM people",
                    (rs,row)->new PersonEntity(rs.getString(1),
                            rs.getString(2))
                    ).forEach(personEntity ->  log.info("Found <" + personEntity + "> in the database."));
        }
    }
}
