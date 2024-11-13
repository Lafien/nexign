package com.nefedov.nexigntestapplication.controller;

import com.nefedov.nexigntestapplication.dto.TaskRequestModel;
import com.nefedov.nexigntestapplication.dto.TaskResponseModel;
import com.nefedov.nexigntestapplication.service.TaskService;
import com.nefedov.nexigntestapplication.utils.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}/status")
    public ResponseEntity<TaskStatus> getTaskStatusById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(taskService.getTaskStatus(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseModel> getTaskById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public TaskResponseModel registerTask(@RequestBody TaskRequestModel taskRequest) {
        return taskService.createTask(taskRequest);
    }
}
