package cz.neonit.klemsa.training.service.communication;

import cz.neonit.klemsa.training.dao.communication.LogFileMessageInfoLoader;
import cz.neonit.klemsa.training.dao.communication.MessageInfoLoader;
import cz.neonit.klemsa.training.domain.communication.CallInfo;
import cz.neonit.klemsa.training.domain.communication.CommunicationInfo;
import cz.neonit.klemsa.training.domain.communication.CommunicationStatistic;
import cz.neonit.klemsa.training.domain.communication.MessageInfo;

import java.rmi.UnexpectedException;
import java.util.Date;
import java.util.List;

public final class CommunicationStatisticService {


    public CommunicationStatistic getCommunicationStatistic(Date date) throws UnexpectedException {


        MessageInfoLoader messageInfoLoader = new LogFileMessageInfoLoader();
        List<CommunicationInfo> communicationInfos = messageInfoLoader.getMessagesInfo(date);


        for (CommunicationInfo c: communicationInfos) {
            if (c instanceof MessageInfo) {

            } else if (c instanceof CallInfo) {

            } else {
                // TODO: Check what type of exception is recomanded.
                throw new UnexpectedException("");
            }
        }



        return null;
    }
}
