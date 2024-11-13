package com.nefedov.nexigntestapplication.repository;

import com.nefedov.nexigntestapplication.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
