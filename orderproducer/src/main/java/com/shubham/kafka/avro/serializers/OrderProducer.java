package com.shubham.kafka.avro.serializers;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.shubham.kafka.avro.Order;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class OrderProducer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "172.23.154.13:9092");
		props.setProperty("key.serializer", KafkaAvroSerializer.class.getName());
		props.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
		props.setProperty("schema.registry.url", "http://172.23.154.13:8081");

		KafkaProducer<String, Order> producer = new KafkaProducer<String, Order>(props);
		Order order = new Order("Shubham", "iphone", 3);
		ProducerRecord<String, Order> record = new ProducerRecord<String, Order>("OrderAvroTopic",
				order.getCustomerName().toString(), order);
		try {
			producer.send(record);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}
}
