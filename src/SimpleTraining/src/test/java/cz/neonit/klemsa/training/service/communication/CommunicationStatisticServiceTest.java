package cz.neonit.klemsa.training.service.communication;

import cz.neonit.klemsa.training.dao.communication.LogFileMessageInfoLoader;
import cz.neonit.klemsa.training.dao.communication.MessageInfoLoader;
import cz.neonit.klemsa.training.domain.communication.CommunicationInfo;
import cz.neonit.klemsa.training.domain.communication.CommunicationStatistic;
import cz.neonit.klemsa.training.domain.communication.MessageInfo;
import cz.neonit.klemsa.training.domain.communication.MessageStatus;
import cz.neonit.klemsa.training.domain.msisdn.MSISDN;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
public class CommunicationStatisticServiceTest {
/*
    @Mock
    MessageInfoLoader messageInfoLoader;

    //@BeforeEach
    public void init() throws ParseException {
        Date date = new SimpleDateFormat("yyyyMMdd").parse("20180131");
        List<CommunicationInfo>  communicationInfoList = new ArrayList<>();
        communicationInfoList.add(new MessageInfo(1517645700L,
                MSISDN.valueOf("34969000001"),
                MSISDN.valueOf("49151123789"),
                "Hi, I'm Tomas.",
                MessageStatus.DELIVERED));
        // Mockito.lenient().when(messageInfoLoader.getMessagesInfo(date)).thenReturn(communicationInfoList);
        // Mockito.when(messageInfoLoader.getMessagesInfo(date)).thenReturn(communicationInfoList);
    }



    @Test
    public void noDataToDate() throws ParseException {
        Date date = new SimpleDateFormat("yyyyMMdd").parse("20180131");
        List<CommunicationInfo>  communicationInfoList = new ArrayList<>();
        communicationInfoList.add(new MessageInfo(1517645700L,
                MSISDN.valueOf("34969000001"),
                MSISDN.valueOf("49151123789"),
                "Hi, I'm Tomas.",
                MessageStatus.DELIVERED));
        Mockito.when(messageInfoLoader.getMessagesInfo(date)).thenReturn(communicationInfoList);

        CommunicationStatisticService css = new CommunicationStatisticService();
        CommunicationStatistic cs = null;

        cs = css.getCommunicationStatistic(date, messageInfoLoader);

        System.out.println(cs);
    }
*/
}
