package com.nefedov.nexigntestapplication.kafka;

import com.nefedov.nexigntestapplication.dto.TaskRequestModel;
import com.nefedov.nexigntestapplication.utils.TaskName;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class KafkaProducer {
    private final KafkaTemplate<String, TaskRequestModel> kafkaTemplate;
    private final static int maxDuration = 10;

    private final Random rnd = new Random();

    @Scheduled(fixedRate = 5000)
    private void sendMessage() {
        TaskRequestModel taskRequestModel = new TaskRequestModel();
        TaskName[] taskNames = TaskName.values();
        taskRequestModel.setName(taskNames[rnd.nextInt(taskNames.length)].getValue());
        taskRequestModel.setDuration((rnd.nextInt(maxDuration) + 1));

        kafkaTemplate.send("task-topic", taskRequestModel);
    }
}
