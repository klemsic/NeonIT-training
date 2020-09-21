package cz.neonit.klemsa.training.dao;

import cz.neonit.klemsa.training.domain.communication.*;
import cz.neonit.klemsa.training.domain.msisdn.MSISDN;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

/**
 * @author tomasklemsa
 */
public final class CommunicationInfoParser {

    /**
     * Creates new instance from json.
     * @param json witch represents CommunicationInfo object.
     * @return new instance of CommunicationInfo or null if json is invalid or null.
     */
    public static CommunicationInfo parse(String json) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        CommunicationInfo communicationInfo = null;
        JSONObjectWrapper jsonObjectWrapper;

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

        jsonObjectWrapper = new JSONObjectWrapper(jsonObject);

        messageType = jsonObjectWrapper.getMessageType("message_type");

        if (messageType != null) {
            timestamp = jsonObjectWrapper.getLong("timestamp");
            origin = jsonObjectWrapper.getMSISDN("origin");
            destination = jsonObjectWrapper.getMSISDN("destination");

            // Construct return object dependent on communication info type.
            switch (messageType) {
                case MSG:
                    messageContent = jsonObjectWrapper.getString("message_content");
                    messageStatus = jsonObjectWrapper.getMessageStatus("message_status");
                    return new MessageInfo(timestamp, origin, destination, messageContent, messageStatus);

                case CALL:
                    duration = jsonObjectWrapper.getInteger("duration");
                    statusCode = jsonObjectWrapper.getStatusCode("status_code");
                    statusDescription = jsonObjectWrapper.getString("status_description");
                    return new CallInfo(timestamp, origin, destination, duration, statusCode, statusDescription);

                default:
                    throw new IllegalStateException("Unexpected value: " + messageType);
            }
        } else {
            return null;
        }

    }

    /**
     * Wrapper class for JSONObject, witch provides get methods with return types.
     */
    static class JSONObjectWrapper {
        private final Map jsonObject;

        /**
         *
         * @param jsonObject
         */
        JSONObjectWrapper(JSONObject jsonObject) {
            this.jsonObject = jsonObject;
        }

        String getString(String key) {
            try {
                return jsonObject.get(key).toString();
            } catch (Exception e) {
                return null;
            }
        }

        Integer getInteger(String key) {
            try {
                return Integer.valueOf(getString(key));
            } catch (Exception e) {
                return null;
            }
        }

        Long getLong(String key) {
            try {
                return Long.valueOf(getString(key));
            } catch (Exception e) {
                return null;
            }
        }

        MSISDN getMSISDN(String key) {
            try {
                return MSISDN.valueOf(getString(key));
            } catch (Exception e) {
                return null;
            }
        }

        MessageType getMessageType(String key) {
            try {
                return MessageType.valueOf(getString(key));
            } catch (Exception e) {
                return null;
            }
        }

        MessageStatus getMessageStatus(String key) {
            try {
                return MessageStatus.valueOf(getString(key));
            } catch (Exception e) {
                return null;
            }
        }

        StatusCode getStatusCode(String key) {
            try {
                return StatusCode.valueOf(getString(key));
            } catch (Exception e) {
                return null;
            }
        }
    }
}
