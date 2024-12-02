package com.nefedov.nexigntestapplication.mapper;

import com.nefedov.nexigntestapplication.dto.TaskRequestModel;
import com.nefedov.nexigntestapplication.dto.TaskResponseModel;
import com.nefedov.nexigntestapplication.entity.Task;
import com.nefedov.nexigntestapplication.utils.TaskStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    TaskResponseModel taskToTaskResponse(Task task);

    @Mapping(source = "taskStatus", target = "status")
    Task taskRequestToTask(TaskRequestModel taskRequestModel, TaskStatus taskStatus);
}
