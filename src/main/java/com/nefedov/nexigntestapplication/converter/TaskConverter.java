package com.nefedov.nexigntestapplication.converter;

import com.nefedov.nexigntestapplication.dto.TaskRequestModel;
import com.nefedov.nexigntestapplication.dto.TaskResponseModel;
import com.nefedov.nexigntestapplication.entity.Task;
import com.nefedov.nexigntestapplication.utils.TaskStatus;
import org.springframework.stereotype.Service;

@Service
public class TaskConverter {

    public TaskResponseModel taskToResponseTask(Task task) {
        return TaskResponseModel.builder()
                .id(task.getId())
                .name(task.getName())
                .duration(task.getDuration())
                .status(task.getStatus())
                .build();
    }

    public Task requestTaskToTask(TaskRequestModel taskRequestModel) {
        return new Task(taskRequestModel.getName(), taskRequestModel.getDuration());
    }

    public Task requestTaskToTask(TaskRequestModel taskRequestModel, TaskStatus status) {
        Task task = requestTaskToTask(taskRequestModel);
        task.setStatus(status);
        return task;
    }
}
