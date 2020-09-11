package cz.neonit.klemsa.training.dao.communication;

import cz.neonit.klemsa.training.domain.communication.*;
import cz.neonit.klemsa.training.domain.msisdn.MSISDN;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author tomasklemsa
 */
public class CommunicationInfoBuilder {

    /**
     * Creates new instance from json.
     * @param json witch represents CommunicationInfo object.
     * @return new instance of CommunicationInfo or null if json is invalid or null.
     */
    public static CommunicationInfo buildFromJson(String json) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        CommunicationInfo communicationInfo = null;

        MessageType messageType;
        Long timestamp;
        MSISDN origin;
        MSISDN destination;
        String messageContent;
        MessageStatus messageStatus;
        Integer duration;
        StatusCode statusCode;
        String statusDescription;

        // If json is cannot be parsed.
        try {
            jsonObject = (JSONObject) parser.parse(json);
        } catch (ParseException | NullPointerException e) {
            return null;
        }

        // If MessageType cannot be determined, return null.
        try {
            messageType = MessageType.valueOf((String) jsonObject.get("message_type"));
        } catch (ClassCastException | NullPointerException | IllegalArgumentException e) {
            return null;
        }

        // If timestamp cannot be determined, continue.
        try {
            timestamp = Long.valueOf(jsonObject.get("timestamp").toString());
        } catch (ClassCastException | NullPointerException e) {
            timestamp = null;
        }

        // If origin cannot be determined, continue.
        try {
            origin = MSISDN.valueOf(jsonObject.get("origin").toString());
        } catch (ClassCastException | NullPointerException e) {
            origin = null;
        }

        // If destination cannot be determined, continue.
        try {
            destination = MSISDN.valueOf(jsonObject.get("destination").toString());
        } catch (ClassCastException | NullPointerException e) {
            destination = null;
        }

        // Construct return object dependent on communication info type.
        switch (messageType) {
            case MSG:
                // If message content cannot be determined, continue.
                try {
                    messageContent = jsonObject.get("message_content").toString();
                } catch (ClassCastException | NullPointerException e) {
                    messageContent = null;
                }

                // If message status cannot be determined, continue.
                try {
                    messageStatus = MessageStatus.valueOf(jsonObject.get("message_status").toString());
                } catch (ClassCastException | NullPointerException e) {
                    messageStatus = null;
                }

                return new MessageInfo(timestamp, origin, destination, messageContent, messageStatus);

            case CALL:

                // If duration cannot be determined, continue.
                try {
                    duration = Integer.valueOf(jsonObject.get("duration").toString());
                } catch (ClassCastException | NullPointerException e) {
                    duration = null;
                }

                // If status code cannot be determined, continue.
                try {
                    statusCode = StatusCode.valueOf(jsonObject.get("status_code").toString());
                } catch (ClassCastException | NullPointerException e) {
                    statusCode = null;
                }

                // If status description cannot be determined, continue.
                try {
                    statusDescription = (String) jsonObject.get("status_description");
                } catch (ClassCastException | NullPointerException e) {
                    statusDescription = null;
                }

                return new CallInfo(timestamp, origin, destination, duration, statusCode, statusDescription);

            default:
                throw new IllegalStateException("Unexpected value: " + messageType);
        }
    }
}
