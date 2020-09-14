package cz.neonit.klemsa.training.domain.communication;

import cz.neonit.klemsa.training.domain.msisdn.MSISDN;

import java.util.Objects;

/**
 * @author tomasklemsa
 */
public final class CallInfo extends CommunicationInfo {
    private static final MessageType MESSAGE_TYPE = MessageType.CALL;
    private final Integer duration;
    private final StatusCode statusCode;
    private final String statusDescription;

    /**
     *
     * @param timestamp timestamp
     * @param origin origin
     * @param destination destination
     * @param duration duration
     * @param statusCode status code
     * @param statusDescription status description
     */
    public CallInfo(Long timestamp, MSISDN origin, MSISDN destination, Integer duration, StatusCode statusCode,
                    String statusDescription) {
        super(MESSAGE_TYPE, timestamp, origin, destination);
        this.duration = duration;
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
    }

    /**
     * Return true if call info is complete. That means no fields is  null.
     * @return true if call info is complete, else false.
     */
    @Override
    public boolean isRecordComplete() {
        return super.isRecordComplete()
                && duration == null
                && statusCode == null
                && statusDescription == null;
    }

    /**
     *
     * @return call duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     *
     * @return status code
     */
    public StatusCode getStatusCode() {
        return statusCode;
    }

    /**
     *
     * @return status description
     */
    public String getStatusDescription() {
        return statusDescription;
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
        if (!super.equals(o)) return false;
        CallInfo callInfo = (CallInfo) o;
        return Objects.equals(duration, callInfo.duration) &&
                statusCode == callInfo.statusCode &&
                Objects.equals(statusDescription, callInfo.statusDescription) &&
                super.equals((CommunicationInfo) callInfo);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), duration, statusCode, statusDescription);
    }
}
