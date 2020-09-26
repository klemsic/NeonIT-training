package cz.neonit.klemsa.training.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.neonit.klemsa.training.domain.communication.CommunicationStatistic;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Component;

/**
 * @author tomasklemsa
 */
@Component
public class CommunicationStatisticDeserializer implements Deserializer<CommunicationStatistic> {

    /**
     *
     * @param s
     * @param bytes
     * @return
     */
    @Override
    public CommunicationStatistic deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        CommunicationStatistic communicationStatistic = null;
        try {
            communicationStatistic = mapper.readValue(bytes, CommunicationStatistic.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return communicationStatistic;
    }
}
