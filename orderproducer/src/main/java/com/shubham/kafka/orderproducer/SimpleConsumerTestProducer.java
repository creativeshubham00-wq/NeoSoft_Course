package com.shubham.kafka.orderproducer;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleConsumerTestProducer {

	public static void main(String[] args) throws Exception {

		Properties props = new Properties();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.23.154.13:9092");
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.IntegerSerializer");
		
		KafkaProducer<String, Integer> producer = new KafkaProducer<>(props);

		ProducerRecord<String, Integer> record = new ProducerRecord<>("SimpleConsumerTopic", "Mac Book Pro", 10);

		producer.send(record);

		System.out.println("Message Sent Successfully");

		producer.close();
	}
}