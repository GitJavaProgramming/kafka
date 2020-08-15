package org.pp.kafka;

import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerDemo {
    @SneakyThrows
    public static void main(String[] args) {
        boolean isAsync = (args.length == 0 || !args[0].trim().equalsIgnoreCase("sync"));
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.132:9092");
        props.put("client.id", "ProducerDemo");
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer producer = new KafkaProducer(props);
        String topic = "test";
        int messageNo = 1;
        while (true) {
            String messageStr = "Message_" + messageNo;
            long startTime = System.currentTimeMillis();
            if (isAsync) {
                producer.send(new ProducerRecord(topic, messageNo, messageStr), new DemoCallback(startTime, messageNo, messageStr));
            } else {
                try {
                    producer.send(new ProducerRecord(topic, messageNo, messageStr)).get();
                    System.out.println("Send message: (" + messageNo + ", " + messageStr + ")");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            ++messageNo;
            Thread.sleep(1000);
        }
    }

    private static class DemoCallback implements Callback {

        private final long startTime;
        private final int key;
        private final String message;

        public DemoCallback(long startTime, int key, String message) {
            this.startTime = startTime;
            this.key = key;
            this.message = message;
        }

        @Override
        public void onCompletion(RecordMetadata metadata, Exception exception) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (metadata != null) {
                System.out.println("message(" + key + ", " + message + ") sent to partition (" + metadata.partition() + "), " + "offset(" + metadata.offset() + ") in " + elapsedTime + "ms");
            } else {
                exception.printStackTrace();
            }
        }
    }
}