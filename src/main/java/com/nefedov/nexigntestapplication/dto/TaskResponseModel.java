package com.nefedov.nexigntestapplication.dto;

import com.nefedov.nexigntestapplication.utils.TaskStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TaskResponseModel {
    private long id;
    private String name;
    private int duration;
    private TaskStatus status;
}
