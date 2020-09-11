package cz.neonit.klemsa.training.domain.message;

import cz.neonit.klemsa.training.domain.msisdn.MSISDN;

/**
 * @author tomasklemsa
 */
public abstract class CommunicationInfo {
    private final MessageType messageType;
    private final Long timestamp;
    private final MSISDN origin;
    private final MSISDN destination;

    /**
     *
     * @param messageType message type
     * @param timestamp timestamp
     * @param origin origin
     * @param destination destination
     */
    public CommunicationInfo(MessageType messageType, Long timestamp, MSISDN origin, MSISDN destination) {
        this.messageType = messageType;
        this.timestamp = timestamp;
        this.origin = origin;
        this.destination = destination;
    }

    /**
     *
     * @return message type
     */
    public MessageType getMessageType() {
        return messageType;
    }

    /**
     *
     * @return timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @return origin
     */
    public MSISDN getOrigin() {
        return origin;
    }

    /**
     *
     * @return destination
     */
    public MSISDN getDestination() {
        return destination;
    }
}
