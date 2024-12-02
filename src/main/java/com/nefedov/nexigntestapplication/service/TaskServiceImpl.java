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

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskExecutorService taskExecutorService;

    private final TaskMapper taskMapper;

    @Override
    public TaskStatus getTaskStatus(long id) {
        return taskRepository.findById(id).orElseThrow().getStatus();
    }

    @Override
    public TaskResponseModel createTask(TaskRequestModel taskRequest) {
        Task task = taskRepository.save(taskMapper.taskRequestToTask(taskRequest, TaskStatus.CREATED));
        TaskResponseModel taskResponseModel = taskMapper.taskToTaskResponse(taskRepository.save(task));
        taskExecutorService.executeAsyncWithLock(task);
        return taskResponseModel;
    }

    @Override
    public TaskResponseModel getTaskById(long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        return taskMapper.taskToTaskResponse(task);
    }

    @Override
    public List<TaskResponseModel> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::taskToTaskResponse)
                .sorted(Comparator.comparingLong(TaskResponseModel::getId))
                .toList();
    }

    @Override
    public void remove(long id) throws ConcurrentModificationException {
        Task task = taskRepository.findById(id).orElseThrow();
        taskExecutorService.executeSyncWithLock(() -> taskRepository.delete(task), id);
    }
}
