package cz.neonit.klemsa.training.domain.communication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * @author tomasklemsa
 */
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
