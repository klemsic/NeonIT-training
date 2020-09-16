package cz.neonit.klemsa.training.service;

import cz.neonit.klemsa.training.dao.MessageInfoLoader;
import cz.neonit.klemsa.training.domain.communication.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tomasklemsa
 */
public final class CommunicationStatisticService {

    /**
     * Calculates simple communication statistic.
     * @return
     */
    public CommunicationStatistic getCommunicationStatistic(String date,
                                                            MessageInfoLoader infoLoader,
                                                            KpiCounterService kpiCounterService) {
        List<CommunicationInfo> communicationInfo = infoLoader.getMessagesInfo(date);
        AtomicInteger incompleteRows = new AtomicInteger();
        AtomicInteger emptyMessages = new AtomicInteger();
        AtomicInteger errors = new AtomicInteger();
        AtomicInteger okCalls = new AtomicInteger();
        AtomicInteger koCalls = new AtomicInteger();
        Map<CommunicationCountryDirection, SmallCallStat> callsStat = new HashMap<>();
        Map<String, AtomicInteger> wordsCounter = new HashMap<>();

        kpiCounterService.incrementFiles();;

        for (CommunicationInfo c: communicationInfo) {
            if (c == null) {
                // Number of rows with fields errors.
                errors.incrementAndGet();
                kpiCounterService.incrementRows();

            } else if (c instanceof MessageInfo) {
                MessageInfo messageInfo = (MessageInfo) c;
                kpiCounterService.incrementMessages();

                // Number of rows with missing fields.
                if (!messageInfo.isRecordComplete())
                    incompleteRows.incrementAndGet();

                // Number of messages with blank content.
                if (Objects.equals(messageInfo.getMessageContent(),""))
                    emptyMessages.incrementAndGet();

                // Word occurrence ranking.
                if (messageInfo.getMessageContent() != null) {
                    String message = messageInfo.getMessageContent();
                    List<String> words = splitWords(message);
                    addWordsToCount(wordsCounter, words);
                }

                if (messageInfo.getOrigin() != null)
                    kpiCounterService.addOriginCountryCode(messageInfo.getOrigin().getCc());

                if (messageInfo.getDestination() != null)
                    kpiCounterService.addDestinationCountryCode(messageInfo.getDestination().getCc());

            } else if (c instanceof CallInfo) {
                CallInfo callInfo = (CallInfo) c;
                kpiCounterService.incrementCalls();

                // Number of rows with missing fields.
                if (!callInfo.isRecordComplete())
                    incompleteRows.incrementAndGet();

                // Relationship between OK/KO calls.
                if (callInfo.getStatusCode() == StatusCode.OK)
                    okCalls.incrementAndGet();
                else if (callInfo.getStatusCode() == StatusCode.KO)
                    koCalls.incrementAndGet();

                // Call info statistic.
                CommunicationCountryDirection ccd = new CommunicationCountryDirection(callInfo.getOrigin().getCc(), callInfo.getDestination().getCc());

                if(!callsStat.containsKey(ccd)) {
                    SmallCallStat scs = new SmallCallStat();
                    scs.calls.incrementAndGet();
                    scs.duration.addAndGet(callInfo.getDuration() == null ? 0 : callInfo.getDuration());
                    callsStat.put(ccd, scs);
                } else {
                    SmallCallStat scs = callsStat.get(ccd);
                    scs.calls.incrementAndGet();
                    scs.duration.addAndGet(callInfo.getDuration() == null ? 0 : callInfo.getDuration());
                }

                if (callInfo.getDuration() != null)
                    kpiCounterService.addDuration(callInfo.getDuration().longValue());

                if (callInfo.getOrigin() != null)
                    kpiCounterService.addOriginCountryCode(callInfo.getOrigin().getCc());

               if (callInfo.getDestination() != null)
                   kpiCounterService.addDestinationCountryCode(callInfo.getDestination().getCc());

            } else {
                throw new IllegalStateException("Unexpected class: " + c.getClass());
            }
        }

        //
        Map<CommunicationCountryDirection, Integer> calls = new HashMap<>(callsStat.size());
        Map<CommunicationCountryDirection, Double> averageDurations = new HashMap<>(callsStat.size());
        callsStat.forEach((k, v) -> {
            calls.put(k, v.calls.intValue());
            averageDurations.put(k, v.duration.doubleValue() / v.calls.doubleValue());
        });

        //
        Map<String,Integer> wordOccurrence = new HashMap<>(wordsCounter.size());
        wordsCounter.forEach((k, v) -> {
            wordOccurrence.put(k,v.intValue());
        });

        return new CommunicationStatistic(incompleteRows.intValue(),
                emptyMessages.intValue(),
                errors.intValue(),
                calls,
                averageDurations,
                koCalls.doubleValue() / koCalls.doubleValue() + okCalls.doubleValue(),
                wordOccurrence);
    }

    /**
     * Split text to list of words.
     * @param s
     * @return
     */
    private List<String> splitWords(String s) {
        List<String> result = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    /**
     *
     * @param wordsCounter
     * @param words
     */
    private void addWordsToCount(Map<String, AtomicInteger> wordsCounter, List<String> words) {
        for (String word: words) {
            if (wordsCounter.containsKey(word)) {
                wordsCounter.get(word).incrementAndGet();
            } else {
                wordsCounter.put(word, new AtomicInteger(1));
            }
        }
    }

    /**
     *
     * @author tomasklemsa
     */
    private final class SmallCallStat {
        private AtomicInteger calls = new AtomicInteger();
        private AtomicInteger duration = new AtomicInteger();
    }
}
