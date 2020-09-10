package cz.neonit.klemsa.training.domain.message;

import cz.neonit.klemsa.training.domain.msisdn.MSISDN;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CommunicationInfoBuilder {
    /**
     *
     * @param json
     * @return
     */
    public CommunicationInfo buildFromJson(String json) {
        JSONParser parser = new JSONParser();
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

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(json);

            messageType = (MessageType) jsonObject.get("message_type");
            timestamp = (Long) jsonObject.get("timestamp");
            origin = MSISDN.valueOf((String) jsonObject.get("origin"));
            destination = MSISDN.valueOf((String) jsonObject.get("destination"));

            switch (messageType) {
                case MSG:
                    messageContent = (String) jsonObject.get("message_content");
                    messageStatus = (MessageStatus) jsonObject.get("message_status");
                    communicationInfo = new MessageInfo(timestamp, origin, destination, messageContent, messageStatus);
                    break;
                case CALL:
                    duration = (Integer) jsonObject.get("duration");
                    statusCode = (StatusCode) jsonObject.get("status_code");
                    statusDescription = (String) jsonObject.get("status_description");
                    communicationInfo = new CallInfo(timestamp, origin, destination, duration, statusCode, statusDescription);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + messageType);
            }
        } catch (ParseException e) {
            return null;
        }
        return communicationInfo;
    }

}
