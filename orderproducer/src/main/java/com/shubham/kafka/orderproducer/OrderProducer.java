package com.shubham.kafka.orderproducer;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class OrderProducer {

	public static void main(String[] args) throws Exception {

		Properties props = new Properties();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.23.154.13:9092");
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.IntegerSerializer");
		props.setProperty(ProducerConfig.ACKS_CONFIG, "all");
		props.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG, "434353");
		props.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");
		props.setProperty(ProducerConfig.RETRIES_CONFIG, "2");
		props.setProperty(ProducerConfig.RETRY_BACKOFF_MAX_MS_CONFIG, "400");
		props.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "10244466");
		props.setProperty(ProducerConfig.LINGER_MS_CONFIG, "500");
		props.setProperty(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "200");
		props.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
		
		KafkaProducer<String, Integer> producer = new KafkaProducer<>(props);

		ProducerRecord<String, Integer> record = new ProducerRecord<>("OrderTopic", "Mac Book Pro", 10);

		producer.send(record).get(); // wait for acknowledgement

		System.out.println("Message Sent Successfully");

		producer.close();
	}
}