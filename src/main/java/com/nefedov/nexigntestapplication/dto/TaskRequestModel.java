package com.nefedov.nexigntestapplication.dto;

import com.nefedov.nexigntestapplication.utils.TaskStatus;
import lombok.Data;

@Data
public class TaskRequestModel {
    private String name;
    private int duration;
    private TaskStatus taskStatus;
}
