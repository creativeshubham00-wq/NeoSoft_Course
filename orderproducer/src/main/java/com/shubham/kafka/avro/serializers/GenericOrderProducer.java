package com.shubham.kafka.avro.serializers;

import java.util.Properties;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Parser;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class GenericOrderProducer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "172.23.154.13:9092");
		props.setProperty("key.serializer", KafkaAvroSerializer.class.getName());
		props.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
		props.setProperty("schema.registry.url", "http://172.23.154.13:8081");

		KafkaProducer<String, GenericRecord> producer = new KafkaProducer<String, GenericRecord>(props);
		Parser parser = new Schema.Parser();
		Schema schema = parser.parse("""
			   {
				    "namespace":"com.shubham.kafka.avro",
				    "type":"record",
				    "name":"Order",
				    "fields": [
				        {"name":"customerName","type":"string"},
				        {"name":"product","type":"string"},
				        {"name":"quantity","type":"int"}
				    ]
				}
				""");

		GenericRecord order = new GenericData.Record(schema);
		order.put("customerName", "Shubham");
		order.put("product", "Mac Book Pro");
		order.put("quantity", 100);

		ProducerRecord<String, GenericRecord> record = new ProducerRecord<String, GenericRecord>("OrderAvroGRTopic",
				order.get("customerName").toString(), order);
		try {
			producer.send(record);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}
}
