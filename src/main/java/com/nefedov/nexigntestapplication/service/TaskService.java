package com.nefedov.nexigntestapplication.service;

import com.nefedov.nexigntestapplication.dto.TaskRequestModel;
import com.nefedov.nexigntestapplication.dto.TaskResponseModel;
import com.nefedov.nexigntestapplication.utils.TaskStatus;

import java.util.ConcurrentModificationException;
import java.util.List;

public interface TaskService {

    TaskStatus getTaskStatus(long id);

    TaskResponseModel createTask(TaskRequestModel taskRequest);

    TaskResponseModel getTaskById(long id);

    List<TaskResponseModel> getAllTasks();

    void remove(long id) throws ConcurrentModificationException;
}
