package com.nefedov.nexigntestapplication.entity;

import com.nefedov.nexigntestapplication.utils.TaskStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int duration;

    private TaskStatus status;

    public Task(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }
}
