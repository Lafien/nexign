package com.nefedov.nexigntestapplication;

import com.nefedov.nexigntestapplication.controller.TaskController;
import com.nefedov.nexigntestapplication.dto.TaskResponseModel;
import com.nefedov.nexigntestapplication.service.TaskService;
import com.nefedov.nexigntestapplication.utils.TaskName;
import com.nefedov.nexigntestapplication.utils.TaskStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @Mock
    TaskService taskService;

    @InjectMocks
    TaskController taskController;

    private static final Random random = new Random();

    @Test
    @DisplayName("test getAllTask method")
    void getAllTasks_ReturnsValidResponse() {
        //given
        List<TaskResponseModel> tasks = List.of(
                new TaskResponseModel(random.nextLong(), TaskName.READING.getValue(), 5, TaskStatus.CREATED, LocalDateTime.now()),
                new TaskResponseModel(random.nextLong(), TaskName.WRITING.getValue(), 5, TaskStatus.IN_PROGRESS, LocalDateTime.now()),
                new TaskResponseModel(random.nextLong(), TaskName.WORKING.getValue(), 5, TaskStatus.COMPLETED, LocalDateTime.now()));
        doReturn(tasks).when(taskService).getAllTasks();

        //when
        ResponseEntity<List<TaskResponseModel>> responseEntity = taskController.getAllTasks();

        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tasks, responseEntity.getBody());
    }
}
