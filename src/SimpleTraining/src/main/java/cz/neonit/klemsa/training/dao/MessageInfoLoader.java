package cz.neonit.klemsa.training.dao;

import cz.neonit.klemsa.training.domain.communication.CommunicationInfo;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

/**
 * @author tomasklemsa
 */
public interface MessageInfoLoader {

    /**
     * Gets all message info on the specified date.
     * @param date in witch you search for message info.
     * @return List of message info witch available in current date.
     */
    List<CommunicationInfo> getMessagesInfo(String date);
}
