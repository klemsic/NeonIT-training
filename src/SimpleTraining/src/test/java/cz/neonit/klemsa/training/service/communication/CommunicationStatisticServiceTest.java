package cz.neonit.klemsa.training.service.communication;

import cz.neonit.klemsa.training.dao.communication.LogFileMessageInfoLoader;
import cz.neonit.klemsa.training.domain.communication.CommunicationStatistic;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommunicationStatisticServiceTest {

    @Test
    public void noDataToDate() {
        CommunicationStatisticService css = new CommunicationStatisticService();
        CommunicationStatistic cs = null;
        try {
            cs = css.getCommunicationStatistic(new SimpleDateFormat("yyyyMMdd").parse("20180131"), new LogFileMessageInfoLoader());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(cs);
    }

}
