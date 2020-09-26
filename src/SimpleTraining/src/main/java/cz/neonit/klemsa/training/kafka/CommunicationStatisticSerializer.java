package cz.neonit.klemsa.training.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.neonit.klemsa.training.domain.communication.CommunicationCountryDirection;
import cz.neonit.klemsa.training.domain.communication.CommunicationStatistic;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

/**
 * @author tomasklemsa
 */
@Component
public class CommunicationStatisticSerializer implements Serializer<CommunicationStatistic> {

    /**
     *
     * @param s
     * @param communicationStatistic
     * @return
     */
    @Override
    public byte[] serialize(String s, CommunicationStatistic communicationStatistic) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(communicationStatistic).getBytes();
            objectMapper.writerFor(CommunicationCountryDirection.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retVal;
    }
}
