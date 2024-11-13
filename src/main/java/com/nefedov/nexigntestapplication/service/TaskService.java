package com.nefedov.nexigntestapplication.service;

import com.nefedov.nexigntestapplication.dto.TaskRequestModel;
import com.nefedov.nexigntestapplication.dto.TaskResponseModel;
import com.nefedov.nexigntestapplication.utils.TaskStatus;

public interface TaskService {

    TaskStatus getTaskStatus(long id);

    TaskResponseModel createTask(TaskRequestModel taskRequest);

    TaskResponseModel getTaskById(long id);
}