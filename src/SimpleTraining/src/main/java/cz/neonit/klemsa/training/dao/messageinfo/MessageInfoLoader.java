package cz.neonit.klemsa.training.dao.messageinfo;

import cz.neonit.klemsa.training.domain.message.CommunicationInfo;

import java.time.LocalDate;
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
    List<CommunicationInfo> getMessagesInfo(Date date);

    /**
     * Gets all dates on witch message info are available for current data source.
     * @return dates of available message info
     */
    List<LocalDate> getDates();

}
