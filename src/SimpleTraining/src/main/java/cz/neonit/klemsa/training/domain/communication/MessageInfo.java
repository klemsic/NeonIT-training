package cz.neonit.klemsa.training.domain.message;

import cz.neonit.klemsa.training.domain.msisdn.MSISDN;

/**
 * @author tomasklemsa
 */
public final class MessageInfo extends CommunicationInfo {
    private static final MessageType MESSAGE_TYPE = MessageType.MSG;
    private final String messageContent;
    private final MessageStatus messageStatus;

    /**
     *
     * @param timestamp
     * @param origin
     * @param destination
     * @param messageContent
     * @param messageStatus
     */
    public MessageInfo(Long timestamp, MSISDN origin, MSISDN destination, String messageContent,
                       MessageStatus messageStatus) {
        super(MESSAGE_TYPE,timestamp,origin,destination);
        this.messageContent = messageContent;
        this.messageStatus = messageStatus;
    }

    /**
     *
     * @return
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     *
     * @return
     */
    public MessageStatus getMessageStatus() {
        return messageStatus;
    }
}
