package com.shubham.kafka.orderproducer.customserializer.partitioners;

import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

public class VIPPartitioners implements Partitioner {

	@Override
	public void configure(Map<String, ?> configs) {
	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		List<PartitionInfo> partitions = cluster.availablePartitionsForTopic(topic);
		if(((String)key).equals("Shubham")){
			return 7;
		}
		return (Math.abs(Utils.murmur2(keyBytes))% partitions.size() - 1);
	}

	@Override
	public void close() {

	}

}
