package com.nefedov.nexigntestapplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Task request model")
public class TaskRequestModel {

    @NotBlank(message = "Task name should be filled")
    @Schema(description = "Task name", example = "Reading")
    private String name;

    @Positive(message = "Duration should be positive")
    @Schema(description = "Task duration in seconds, should be positive", example = "15")
    private int duration;
}
