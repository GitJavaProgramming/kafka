package org.pp.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerListener {

    @KafkaListener(topicPartitions = {
            @TopicPartition(
                    topic = "test",
                    partitions = {"0"})
    })
    public void listen(ConsumerRecord<?, String> record, Acknowledgment ack) {
        log.info("topic:{}, partition:{}, offset:{}", record.topic(), record.partition(), record.offset());
//        ack.acknowledge();
    }
}
