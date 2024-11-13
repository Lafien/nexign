package com.nefedov.nexigntestapplication.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TaskStatus {
    CREATED("created"),
    IN_PROGRESS("inProgress"),
    COMPLETED("completed");

    @JsonValue
    private final String value;

    TaskStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public TaskStatus fromString(String value) {
        return Arrays.stream(values()).filter(val -> val.value.equals(value)).findFirst().orElse(null);
    }
}
