package com.nefedov.nexigntestapplication.kafka;

import com.nefedov.nexigntestapplication.dto.TaskRequestModel;
import com.nefedov.nexigntestapplication.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final TaskService taskService;

    @KafkaListener(topics = "task-topic", groupId = "task-group")
    public void listen(TaskRequestModel message) {
        log.info("Received message: " + message);
        taskService.createTask(message);
    }
}
