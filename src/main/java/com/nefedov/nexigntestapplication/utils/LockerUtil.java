package com.nefedov.nexigntestapplication.utils;

public class LockerUtil {

    private static final String LOCKER_PREFIX = "task-lock-";

    public static String getLockerName(long taskId) {
        return LOCKER_PREFIX + taskId;
    }
}
