package com.nefedov.nexigntestapplication;

import com.nefedov.nexigntestapplication.dto.TaskResponseModel;
import com.nefedov.nexigntestapplication.service.TaskService;
import com.nefedov.nexigntestapplication.utils.TaskName;
import com.nefedov.nexigntestapplication.utils.TaskStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NexignTestApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TaskService taskService;

    @AfterEach
    void tearDown() {
        taskService.getAllTasks().clear();
    }

    @Test
    void handleGetAllTasks_ReturnsValidResponseEntity() throws Exception {
        //given
        MockHttpServletRequestBuilder requestBuilder = get("/api/task/all");

        String date = "2019-01-21T05:47:08.644";
        LocalDateTime localDateTime = LocalDateTime.parse(date);

        taskService.getAllTasks()
                .addAll(List.of(
                        new TaskResponseModel(1, TaskName.READING.getValue(), 5, TaskStatus.CREATED, localDateTime),
                        new TaskResponseModel(2, TaskName.WRITING.getValue(), 5, TaskStatus.IN_PROGRESS, localDateTime)));

        mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().json("""
                                [
                                	{
                                		"id": 1,
                                		"name": "reading",
                                		"duration": 5,
                                		"status": "created",
                                		"createDate": "2019-01-21T05:47:08.644"
                                	},
                                	{
                                		"id": 2,
                                		"name": "writing",
                                		"duration": 5,
                                		"status": "inProgress",
                                		"createDate": "2019-01-21T05:47:08.644"
                                	}
                                ]
                                """)
                );
    }

}
