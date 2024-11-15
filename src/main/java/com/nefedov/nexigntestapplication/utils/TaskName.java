package com.nefedov.nexigntestapplication.utils;

import lombok.Getter;

/**
 * For random task name in kafka producer
 */
@Getter
public enum TaskName {
    READING("reading"),
    WORKING("working"),
    WRITING("writing"),
    CREATING("creating");

    private final String value;

    TaskName(String value) {
        this.value = value;
    }
}
