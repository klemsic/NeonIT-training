package cz.neonit.klemsa.training.domain.communication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @author tomasklemsa
 */
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
