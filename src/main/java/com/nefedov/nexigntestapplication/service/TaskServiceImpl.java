package com.nefedov.nexigntestapplication.service;

import com.nefedov.nexigntestapplication.dto.TaskRequestModel;
import com.nefedov.nexigntestapplication.dto.TaskResponseModel;
import com.nefedov.nexigntestapplication.entity.Task;
import com.nefedov.nexigntestapplication.mapper.TaskMapper;
import com.nefedov.nexigntestapplication.repository.TaskRepository;
import com.nefedov.nexigntestapplication.utils.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskStatus getTaskStatus(long id) {
        return taskRepository.findById(id).orElseThrow().getStatus();
    }

    @Override
    public TaskResponseModel createTask(TaskRequestModel taskRequest) {
        Task task = TaskMapper.taskRequestToTask(taskRequest, TaskStatus.CREATED);
        return TaskMapper.taskToTaskResponse(taskRepository.save(task));
    }

    @Override
    public TaskResponseModel getTaskById(long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        return TaskMapper.taskToTaskResponse(task);
    }

    @Override
    public List<TaskResponseModel> getAllTasks() {
        return taskRepository.findAll().stream().map(TaskMapper::taskToTaskResponse).toList();
    }
}
