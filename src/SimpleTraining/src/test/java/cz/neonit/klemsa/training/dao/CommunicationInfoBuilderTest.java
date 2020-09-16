package cz.neonit.klemsa.training.dao.communication;

import cz.neonit.klemsa.training.domain.communication.*;
import cz.neonit.klemsa.training.domain.msisdn.MSISDN;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommunicationInfoBuilderTest {

    @Test
    public void buildFromJson_Null() {
        CommunicationInfo c = CommunicationInfoBuilder.buildFromJson(null);
        assertNull(c);
    }

    @Test
    public void buildFromJson_Empty() {
        CommunicationInfo c = CommunicationInfoBuilder.buildFromJson("");
        assertNull(c);
    }

    @Test
    public void buildFromJson_CorrectCallString() {
        CommunicationInfo c1 = CommunicationInfoBuilder.buildFromJson("{\"message_type\": \"CALL\",\"timestamp\": 1517732102," +
                "\"origin\": 49151123456,\"destination\": 49151123456,\"duration\": 10,\"status_code\": \"KO\"," +
                "\"status_description\": \"301: Self calling\"}");
        CommunicationInfo c2 = new CallInfo(1517732102L, new MSISDN(49, 151, 123456),
                new MSISDN(49, 151, 123456), 10, StatusCode.KO, "301: Self calling");
        assertEquals(c1,c2);
    }

    @Test
    public void buildFromJson_MissingCallParameter() {
        CommunicationInfo c1 = CommunicationInfoBuilder.buildFromJson("{\"message_type\": \"CALL\",\"timestamp\": 1517732102," +
                "\"origin\": 49151123456,\"destination\": 49151123456,\"duration\": 10,\"status_code\": \"KO\"}");
        CommunicationInfo c2 = new CallInfo(1517732102L, new MSISDN(49, 151, 123456),
                new MSISDN(49, 151, 123456), 10, StatusCode.KO, null);
        assertEquals(c1,c2);
    }

    @Test
    public void buildFromJson_CorrectMessageString() {
        CommunicationInfo c1 = CommunicationInfoBuilder.buildFromJson("{\"message_type\": \"MSG\",\"timestamp\": 1517559360," +
                "\"origin\": 49151123456,\"destination\": 49151123789,\"message_content\": \"1. HELLO\"," +
                "\"message_status\": \"DELIVERED\"}");
        CommunicationInfo c2 = new MessageInfo(1517559360L, new MSISDN(49, 151, 123456),
                new MSISDN(49, 151, 123789), "1. HELLO", MessageStatus.DELIVERED);
        assertEquals(c1,c2);
    }

    @Test
    public void buildFromJson_MissingMessageParameter() {
        CommunicationInfo c1 = CommunicationInfoBuilder.buildFromJson("{\"message_type\": \"MSG\",\"timestamp\": 1517559360," +
                "\"origin\": 49151123456,\"destination\": 49151123789,\"message_content\": \"1. HELLO\"}");
        CommunicationInfo c2 = new MessageInfo(1517559360L, new MSISDN(49, 151, 123456),
                new MSISDN(49, 151, 123789), "1. HELLO", null);
        assertEquals(c1,c2);
    }

    @Test
    public void buildFromJson_InvalidMessageType() {
        CommunicationInfo c = CommunicationInfoBuilder.buildFromJson("{\"message_type\": \"XXX\",\"timestamp\": 1517559360," +
                "\"origin\": 49151123456,\"destination\": 49151123789,\"message_content\": \"1. HELLO\"}");
        assertNull(c);
    }

    @Test
    public void buildFromJson_NoMessageType() {
        CommunicationInfo c = CommunicationInfoBuilder.buildFromJson("{\"timestamp\": 1517559360," +
                "\"origin\": 49151123456,\"destination\": 49151123789,\"message_content\": \"1. HELLO\"}");
        assertNull(c);
    }

    @Test
    public void buildFromJson_NotJson() {
        CommunicationInfo c = CommunicationInfoBuilder.buildFromJson("abcdefgh");
        assertNull(c);
    }
}
