package com.nefedov.nexigntestapplication.service;

import com.nefedov.nexigntestapplication.converter.TaskConverter;
import com.nefedov.nexigntestapplication.dto.TaskRequestModel;
import com.nefedov.nexigntestapplication.dto.TaskResponseModel;
import com.nefedov.nexigntestapplication.entity.Task;
import com.nefedov.nexigntestapplication.repository.TaskRepository;
import com.nefedov.nexigntestapplication.utils.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;

    @Override
    public TaskStatus getTaskStatus(long id) {
        return taskRepository.findById(id).orElseThrow().getStatus();
    }

    @Override
    public TaskResponseModel createTask(TaskRequestModel taskRequest) {
        Task task = taskConverter.requestTaskToTask(taskRequest, TaskStatus.CREATED);
        return taskConverter.taskToResponseTask(taskRepository.save(task));
    }

    @Override
    public TaskResponseModel getTaskById(long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        return taskConverter.taskToResponseTask(task);
    }
}
