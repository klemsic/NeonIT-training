package cz.neonit.klemsa.training.domain.communication;

/**
 *
 * @author tomasklemsa
 */
public enum MessageType {
    CALL,
    MSG;

    /**
     *
     * @param s
     * @return
     */
    public static boolean contains(String s) {
        for (MessageType messageType: MessageType.values()) {
            if(messageType.name().equals(s))
                return true;
        }
        return false;
    }
}
