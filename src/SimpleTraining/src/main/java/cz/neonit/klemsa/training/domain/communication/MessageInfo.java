package cz.neonit.klemsa.training.domain.communication;

import cz.neonit.klemsa.training.domain.msisdn.MSISDN;

import java.util.Objects;

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
        MessageInfo messageInfo = (MessageInfo) o;
        return Objects.equals(messageContent, messageInfo.messageContent) &&
                messageStatus == messageInfo.messageStatus &&
                super.equals((CommunicationInfo) messageInfo);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), messageContent, messageStatus);
    }
}
