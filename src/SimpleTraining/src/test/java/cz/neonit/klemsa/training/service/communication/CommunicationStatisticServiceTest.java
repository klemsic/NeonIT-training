package cz.neonit.klemsa.training.service.communication;

import cz.neonit.klemsa.training.dao.communication.MessageInfoLoader;
import cz.neonit.klemsa.training.domain.communication.*;
import cz.neonit.klemsa.training.domain.kpi.KpiCounter;
import cz.neonit.klemsa.training.domain.msisdn.MSISDN;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class CommunicationStatisticServiceTest {
    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Mock
    MessageInfoLoader messageInfoLoader;

    @BeforeEach
    public void init() throws ParseException {
        Date date = new SimpleDateFormat("yyyyMMdd").parse("20180131");
        List<CommunicationInfo>  communicationInfoList = new ArrayList<>();
        communicationInfoList.add(new MessageInfo(1517645700L,
                MSISDN.valueOf("34969000001"),
                MSISDN.valueOf("49151123789"),
                "Hi, I'm Tomas.",
                MessageStatus.DELIVERED));
        Mockito.lenient().when(messageInfoLoader.getMessagesInfo(date)).thenReturn(communicationInfoList);
    }



    @Test
    public void noDataToDate(@Autowired KpiCounter kpiCounter) throws ParseException {
        Date date = new SimpleDateFormat("yyyyMMdd").parse("20180131");
        List<CommunicationInfo>  communicationInfoList = new ArrayList<>();
        communicationInfoList.add(new MessageInfo(1517645700L,
                MSISDN.valueOf("34969000001"),
                MSISDN.valueOf("49151123789"),
                "Hi, I'm Tomas.",
                MessageStatus.DELIVERED));
        Mockito.when(messageInfoLoader.getMessagesInfo(date)).thenReturn(communicationInfoList);

        CommunicationStatisticService css = new CommunicationStatisticService();
        CommunicationStatistic cs1 = css.getCommunicationStatistic(date, messageInfoLoader, kpiCounter);


        Map<CommunicationCountryDirection,Integer> calls = new HashMap<>();

        Map<CommunicationCountryDirection, Double> averageCallDurations = new HashMap<>();

        Map<String, Integer> wordOccurrence = new HashMap<>();
        wordOccurrence.put("Hi", 1);
        wordOccurrence.put("I", 1);
        wordOccurrence.put("m", 1);
        wordOccurrence.put("Tomas", 1);

        CommunicationStatistic cs2 = new CommunicationStatistic(1,
                0,
                0,
                calls,
                averageCallDurations,
                0.0,
                wordOccurrence);

        // assertEquals(cs1, cs2);
    }






}
