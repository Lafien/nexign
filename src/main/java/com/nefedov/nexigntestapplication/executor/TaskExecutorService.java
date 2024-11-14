package com.nefedov.nexigntestapplication.executor;

import com.nefedov.nexigntestapplication.entity.Task;
import com.nefedov.nexigntestapplication.repository.TaskRepository;
import com.nefedov.nexigntestapplication.utils.TaskStatus;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskExecutorService {

    private final TaskRepository taskRepository;

    private ExecutorService executorService;

    @Value("${workers.pool}")
    private int workersPool;

    @PostConstruct
    public void init() {
        executorService = Executors.newFixedThreadPool(workersPool);
    }

    public void execute(Task task) {
        executorService.execute(() -> {
            try {
                task.setStatus(TaskStatus.IN_PROGRESS);
                taskRepository.save(task);

                log.info("started task execution: " + task + ", thread " + Thread.currentThread());
                Thread.sleep(task.getDuration() * 1000L);
                log.info("finished task execution: " + task + ", thread " + Thread.currentThread());

                task.setStatus(TaskStatus.COMPLETED);
                taskRepository.save(task);
            } catch (Exception e) {
                task.setStatus(TaskStatus.FAILED);
                taskRepository.save(task);
            }
        });
    }
}
