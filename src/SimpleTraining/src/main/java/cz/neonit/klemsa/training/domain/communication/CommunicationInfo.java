package cz.neonit.klemsa.training.domain.communication;

import cz.neonit.klemsa.training.domain.msisdn.MSISDN;

import java.util.Objects;

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
     * Return true if communication info is complete. That means no fields is  null.
     * @return true if communication info is complete, else false.
     */
    public boolean isRecordComplete() {
        return messageType == null
                && timestamp == null
                && origin == null
                && destination == null;
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

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommunicationInfo that = (CommunicationInfo) o;
        return messageType == that.messageType &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(origin, that.origin) &&
                Objects.equals(destination, that.destination);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(messageType, timestamp, origin, destination);
    }
}
