package cz.neonit.klemsa.training.service;

import cz.neonit.klemsa.training.domain.communication.CommunicationCountryDirection;
import cz.neonit.klemsa.training.domain.communication.CommunicationStatistic;
import cz.neonit.klemsa.training.kafka.CommunicationStatisticDeserializer;
import cz.neonit.klemsa.training.kafka.MetricsCallKafkaProducer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MetricsCallKafkaProducerTest {

    /*@Test
    public void producerCall() throws IOException, ClassNotFoundException {
        MetricsCallKafkaProducer producer = new MetricsCallKafkaProducer();

        Map<CommunicationCountryDirection, Integer> calls = new HashMap<>();
        Map<CommunicationCountryDirection, Double> duration = new HashMap<>();
        Map<String, Integer> words = new HashMap<>();

        calls.put(new CommunicationCountryDirection(420,420), 628);
        calls.put(new CommunicationCountryDirection(420,421), 129);
        calls.put(new CommunicationCountryDirection(421,421), 427);
        calls.put(new CommunicationCountryDirection(421,420), 86);

        CommunicationStatistic cs = new CommunicationStatistic(10,
                2,
                1,
                calls,
                duration,
                0.9853,
                words);

        producer.send(cs);

        CommunicationCountryDirection direction = new CommunicationCountryDirection(420,421);
        Map<CommunicationCountryDirection, Integer> map = new HashMap<>();
        map.put(direction, 125);
    }
     */

    @Test
    public void deserializertest() {
        CommunicationStatisticDeserializer deserializer = new CommunicationStatisticDeserializer();
        CommunicationStatistic cs = deserializer.deserialize("",  new String("{\"incompleteRows\":10,\"emptyMessages\":1,\"errors\":4,\"calls\":{\"421-420\":63,\"421-421\":259,\"420-420\":328,\"420-421\":15},\"averageCallDurations\":{\"421-420\":6.28,\"421-421\":2.51,\"420-420\":2.86,\"420-421\":5.86},\"failtureCallsRatio\":0.1,\"wordOccurrence\":{}}\n").getBytes());
        System.out.println(cs);

    }
}
