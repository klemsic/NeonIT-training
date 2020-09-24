package cz.neonit.klemsa.training.service;

import cz.neonit.klemsa.training.domain.communication.CommunicationStatistic;
import cz.neonit.klemsa.training.domain.communication.CommunicationStatisticDeserializer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;


import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MetricsCallKafkaConsumer {
    private final static String TOPIC = "METRICS_CALL";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";


    /**
     *
     * @return
     */
    private static Consumer<Long, CommunicationStatistic> createConsumer() {
        final Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CommunicationStatisticDeserializer.class.getName());
        //props.put(ConsumerConfig.GROUP_ID_CONFIG, "MetricsCallKafkaProducer");

        final Consumer<Long, CommunicationStatistic> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(TOPIC));

        return consumer;
    }

    /**
     *
     * @throws InterruptedException
     */
    static void runConsumer() throws InterruptedException {
        ExecutorService ex = Executors.newSingleThreadExecutor();


        final Consumer<Long, CommunicationStatistic> consumer = createConsumer();

        final int giveUp = 100;
        int noRecordsCount = 0;

        while (true) {
            final ConsumerRecords<Long, CommunicationStatistic> consumerRecords = consumer.poll(1000);

            if (consumerRecords.count()==0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }

            consumerRecords.forEach(record -> {
                System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
                        record.key(), record.value(),
                        record.partition(), record.offset());
            });

            consumer.commitAsync();
        }
        consumer.close();
        System.out.println("DONE");
    }


}
