package com.nefedov.nexigntestapplication.controller;

import com.nefedov.nexigntestapplication.dto.TaskRequestModel;
import com.nefedov.nexigntestapplication.dto.TaskResponseModel;
import com.nefedov.nexigntestapplication.service.TaskService;
import com.nefedov.nexigntestapplication.utils.TaskStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
@Tag(name = "TaskController", description = "Task operations")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}/status")
    @Operation(summary = "Get task status by id")
    public ResponseEntity<TaskStatus> getTaskStatusById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(taskService.getTaskStatus(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task by id")
    public ResponseEntity<TaskResponseModel> getTaskById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    @Operation(summary = "Register task")
    public TaskResponseModel registerTask(@RequestBody
                                          @Parameter(description = "Name should be filled, duration (in seconds) should be positive")
                                          @Valid TaskRequestModel taskRequest) {
        return taskService.createTask(taskRequest);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all tasks")
    public List<TaskResponseModel> getAllTasks() {
        return taskService.getAllTasks();
    }
}
