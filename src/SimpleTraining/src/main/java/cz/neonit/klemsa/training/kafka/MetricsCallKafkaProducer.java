package cz.neonit.klemsa.training.kafka;

import cz.neonit.klemsa.training.domain.communication.CommunicationStatistic;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public final class MetricsCallKafkaProducer  {
    private final static String TOPIC = "METRICS_CALL";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    /**
     *
     * @return
     */
    private static Producer<Long, CommunicationStatistic> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CommunicationStatisticSerializer.class.getName());
        //props.put(ProducerConfig.CLIENT_ID_CONFIG, "MetricsCallKafkaProducer");
        //props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }

    /**
     *
     */
    public void send(CommunicationStatistic communicationStatistic) {
        final Producer<Long, CommunicationStatistic> producer = createProducer();
        long time = System.currentTimeMillis();
        final ProducerRecord<Long, CommunicationStatistic> record = new ProducerRecord<>(TOPIC, time,communicationStatistic);

        producer.send(record);
        producer.flush();
        producer.close();
    }
}
