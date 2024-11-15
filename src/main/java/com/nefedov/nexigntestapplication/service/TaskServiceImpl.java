package com.nefedov.nexigntestapplication.service;

import com.nefedov.nexigntestapplication.dto.TaskRequestModel;
import com.nefedov.nexigntestapplication.dto.TaskResponseModel;
import com.nefedov.nexigntestapplication.entity.Task;
import com.nefedov.nexigntestapplication.executor.TaskExecutorService;
import com.nefedov.nexigntestapplication.mapper.TaskMapper;
import com.nefedov.nexigntestapplication.repository.TaskRepository;
import com.nefedov.nexigntestapplication.utils.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ConcurrentModificationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskExecutorService taskExecutorService;

    @Override
    public TaskStatus getTaskStatus(long id) {
        return taskRepository.findById(id).orElseThrow().getStatus();
    }

    @Override
    public TaskResponseModel createTask(TaskRequestModel taskRequest) {
        Task task = taskRepository.save(TaskMapper.taskRequestToTask(taskRequest, TaskStatus.CREATED));
        TaskResponseModel taskResponseModel = TaskMapper.taskToTaskResponse(taskRepository.save(task));
        taskExecutorService.executeAsyncWithLock(task);
        return taskResponseModel;
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

    @Override
    public void remove(long id) throws ConcurrentModificationException {
        Task task = taskRepository.findById(id).orElseThrow();
        taskExecutorService.executeSyncWithLock(() -> taskRepository.delete(task), id);
    }
}
