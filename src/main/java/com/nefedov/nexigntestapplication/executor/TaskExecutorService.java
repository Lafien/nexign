package com.nefedov.nexigntestapplication.executor;

import com.nefedov.nexigntestapplication.entity.Task;
import com.nefedov.nexigntestapplication.repository.TaskRepository;
import com.nefedov.nexigntestapplication.utils.LockerUtil;
import com.nefedov.nexigntestapplication.utils.TaskStatus;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ConcurrentModificationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskExecutorService {

    private final RedissonClient redissonClient;

    private final TaskRepository taskRepository;

    private ExecutorService executorService;

    @Value("${workers.pool}")
    private int workersPool;

    @PostConstruct
    private void init() {
        executorService = Executors.newFixedThreadPool(workersPool);
    }

    public void executeAsyncWithLock(Task task) {
        RLock rLock = redissonClient.getLock(LockerUtil.getLockerName(task.getId()));
        executorService.execute(() -> {
            if (rLock.tryLock()) {
                log.info("lock was acquired for taskId: " + task.getId() + ", thread: " + Thread.currentThread());
                try {
                    task.setStatus(TaskStatus.IN_PROGRESS);
                    taskRepository.save(task);

                    Thread.sleep(task.getDuration() * 1000L);

                    task.setStatus(TaskStatus.COMPLETED);
                    taskRepository.save(task);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    task.setStatus(TaskStatus.FAILED);
                    taskRepository.save(task);
                } finally {
                    rLock.unlock();
                    log.info("Lock was released for taskId: " + task.getId() + ", thread: " + Thread.currentThread());
                }
            } else {
                throw new ConcurrentModificationException("Task with id " + task.getId() + " is in progress");
            }
        });
    }

    public void executeSyncWithLock(Runnable runnable, long taskId) throws ConcurrentModificationException {
        RLock rLock = redissonClient.getLock(LockerUtil.getLockerName(taskId));
        if (rLock.tryLock()) {
            try {
                runnable.run();
            } finally {
                rLock.unlock();
            }
        } else {
            throw new ConcurrentModificationException("Task with id " + taskId + " is in progress");
        }
    }
}
