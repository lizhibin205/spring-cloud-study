package com.bytrees.message;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;

import java.util.Properties;

public class ProducerTest {
    @Test
    public void sendMessage() {
        Properties pros = new Properties();
        pros.put("bootstarp.servers", "127.0.0.1:9092");
        pros.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        pros.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        pros.put("acks", "-1");
        pros.put("retries", 3);
        pros.put("batch.size", 323840);
        pros.put("linger.ms", 10);
        pros.put("buffer.memory", 33554432);
        pros.put("max.block.ms", 3000);
        Producer<String, String> producer = new KafkaProducer<String, String>(pros);
        producer.send(new ProducerRecord<>("zhibin02", "from-java"));
        producer.close();
    }
}
