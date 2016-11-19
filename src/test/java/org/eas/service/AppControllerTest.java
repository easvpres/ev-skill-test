package org.eas.service;

import org.eas.AppConfig;
import org.eas.dto.DaysToBirthdayLeftResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.eas.dto.DaysToBirthdayLeftResponse.JobStatus.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
public class AppControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getAccount() throws Exception {
        String jobId = mockMvc.perform(post("/birthday/start").param("month", "12").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(get("/birthday/result").param("jobId", jobId).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("jobStatus").value(IN_PROGRESS.toString()))
                .andExpect(jsonPath("data").isEmpty());

        mockMvc.perform(get("/birthday/result").param("jobId", "123").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("jobStatus").value(JOB_NOT_FOUND.toString()));

        Thread.sleep(10000);

        mockMvc.perform(get("/birthday/result").param("jobId", jobId).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("jobStatus").value(DONE.toString()))
                .andExpect(jsonPath("data.length()").value(2))
                .andExpect(jsonPath("data[0].personnName").value("Alexander"))
                .andExpect(jsonPath("data[0].daysLeft").value(23))
                .andExpect(jsonPath("data[1].personnName").value("Sergey"))
                .andExpect(jsonPath("data[1].daysLeft").value(35));
    }

}