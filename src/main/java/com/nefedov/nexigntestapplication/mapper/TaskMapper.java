package com.nefedov.nexigntestapplication.mapper;

import com.nefedov.nexigntestapplication.dto.TaskRequestModel;
import com.nefedov.nexigntestapplication.dto.TaskResponseModel;
import com.nefedov.nexigntestapplication.entity.Task;
import com.nefedov.nexigntestapplication.utils.TaskStatus;

public class TaskMapper {

    public static TaskResponseModel taskToTaskResponse(Task task) {
        return TaskResponseModel.builder()
                .id(task.getId())
                .name(task.getName())
                .duration(task.getDuration())
                .status(task.getStatus())
                .build();
    }

    public static Task taskRequestToTask(TaskRequestModel taskRequestModel) {
        return new Task(taskRequestModel.getName(), taskRequestModel.getDuration());
    }

    public static Task taskRequestToTask(TaskRequestModel taskRequestModel, TaskStatus status) {
        Task task = taskRequestToTask(taskRequestModel);
        task.setStatus(status);
        return task;
    }
}
