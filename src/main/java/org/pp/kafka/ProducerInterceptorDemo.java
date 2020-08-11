package org.pp.kafka;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class ProducerInterceptorDemo implements ProducerInterceptor<Integer, String> {

    @Override
    public ProducerRecord<Integer, String> onSend(ProducerRecord<Integer, String> record) {
        if (record.key() % 2 == 0) {
            return record;
        }
        return null;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (metadata != null && "test".equalsIgnoreCase(metadata.topic()) && metadata.partition() == 0) {
            System.out.println(metadata.topic());
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
