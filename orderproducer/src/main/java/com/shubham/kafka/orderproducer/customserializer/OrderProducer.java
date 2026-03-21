package com.shubham.kafka.orderproducer.customserializer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.shubham.kafka.orderproducer.customserializer.partitioners.VIPPartitioners;

public class OrderProducer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "172.23.154.13:9092");
		props.setProperty("key.serializer", StringSerializer.class.getName());
		props.setProperty("value.serializer", OrderSerializer.class.getName());
		props.setProperty("partitioner.class", VIPPartitioners.class.getName());

		KafkaProducer<String, Order> producer = new KafkaProducer<String, Order>(props);
		Order order = new Order();
		order.setCustomerName("Takesta");
		order.setProduct("iphone");
		order.setQuantity(1);
		ProducerRecord<String, Order> record = new ProducerRecord<String, Order>("OrderPartitionedTopic",
				order.getCustomerName(), order);
		try {
			producer.send(record);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}
}
