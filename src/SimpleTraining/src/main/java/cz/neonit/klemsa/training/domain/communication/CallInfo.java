package cz.neonit.klemsa.training.domain.message;

import cz.neonit.klemsa.training.domain.msisdn.MSISDN;

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
}
