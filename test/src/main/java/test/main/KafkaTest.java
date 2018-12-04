package test.main;

import org.apache.kafka.clients.KafkaClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import test.TestSeivice;

import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.Future;

public class KafkaTest {
    public static void main(String[] args){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer producer = new KafkaProducer(props);
//        HashMap

//        ProducerRecord producerRecord = new ProducerRecord("kafka1", aa);
//        Future<RecordMetadata> future = producer.send(producerRecord);
        //System.out.println(future.isDone());

       // KafkaConsumer consumer = new KafkaConsumer(props);
       //ConsumerRecord consumerRecord = new ConsumerRecord("1");

        //consumer.subscribe();
        String aa = "aaallffff";

        System.out.println(1<<2);



    }
}
