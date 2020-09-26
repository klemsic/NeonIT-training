package cz.neonit.klemsa.training.service;

import cz.neonit.klemsa.training.dao.MessageInfoLoader;
import cz.neonit.klemsa.training.domain.communication.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service that's provides calculation of communication statistics.
 * @author tomasklemsa
 */
@Service
public final class CommunicationStatisticService {

    /**
     * Calculates simple communication statistic.
     * @param date in format yyyyMMdd, in witch we want calculate statistic
     * @param infoLoader for read log messages
     * @return new CommunicationStatistic for required date
     */
    public CommunicationStatistic getCommunicationStatistic(String date, MessageInfoLoader infoLoader) {
        List<CommunicationInfo> communicationInfo = infoLoader.getMessagesInfo(date);
        AtomicInteger rows = new AtomicInteger();
        AtomicInteger incompleteRows = new AtomicInteger();
        AtomicInteger emptyMessages = new AtomicInteger();
        AtomicInteger errors = new AtomicInteger();
        AtomicInteger okCalls = new AtomicInteger();
        AtomicInteger koCalls = new AtomicInteger();
        Map<CommunicationCountryDirection, SmallCallStat> callsStat = new HashMap<>();
        Map<CommunicationCountryDirection, AtomicInteger> messagesStat = new HashMap<>();
        Map<String, AtomicInteger> wordsCounter = new HashMap<>();

        for (CommunicationInfo c: communicationInfo) {
            rows.incrementAndGet();

            if (c == null) {
                // Number of rows with fields errors.
                errors.incrementAndGet();

            } else if (c instanceof MessageInfo) {
                MessageInfo messageInfo = (MessageInfo) c;

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

                // Message count statistic.
                Integer originCc = null;
                Integer destinationCc = null;

                if (messageInfo.getOrigin() != null) {
                    originCc = messageInfo.getOrigin().getCc();
                }

                if (messageInfo.getDestination() != null) {
                    destinationCc = messageInfo.getDestination().getCc();
                }

                CommunicationCountryDirection ccd = new CommunicationCountryDirection(originCc, destinationCc);

                if(!messagesStat.containsKey(ccd)) {
                    AtomicInteger messagesCount = new AtomicInteger(1);
                    messagesStat.put(ccd, messagesCount);
                } else {
                    AtomicInteger messagesCount = messagesStat.get(ccd);
                    messagesCount.incrementAndGet();
                }

            } else if (c instanceof CallInfo) {
                CallInfo callInfo = (CallInfo) c;

                // Number of rows with missing fields.
                if (!callInfo.isRecordComplete())
                    incompleteRows.incrementAndGet();

                // Relationship between OK/KO calls.
                if (callInfo.getStatusCode() == StatusCode.OK)
                    okCalls.incrementAndGet();
                else if (callInfo.getStatusCode() == StatusCode.KO)
                    koCalls.incrementAndGet();

                // Call info statistic.
                Integer originCc = null;
                Integer destinationCc = null;

                if (callInfo.getOrigin() != null) {
                    originCc = callInfo.getOrigin().getCc();
                }

                if (callInfo.getDestination() != null) {
                    destinationCc = callInfo.getDestination().getCc();
                }

                CommunicationCountryDirection ccd = new CommunicationCountryDirection(originCc, destinationCc);

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

            } else {
                throw new IllegalStateException("Unexpected class: " + c.getClass());
            }
        }

        // Calculate calls & average durations.
        Map<CommunicationCountryDirection, Integer> calls = new HashMap<>(callsStat.size());
        Map<CommunicationCountryDirection, Double> averageDurations = new HashMap<>(callsStat.size());
        callsStat.forEach((k, v) -> {
            calls.put(k, v.calls.intValue());
            averageDurations.put(k, v.duration.doubleValue() / v.calls.doubleValue());
        });

        // Calculate word occurrence.
        Map<String,Integer> wordOccurrence = new HashMap<>(wordsCounter.size());
        wordsCounter.forEach((k, v) -> {
            wordOccurrence.put(k,v.intValue());
        });

        // Calculate messages.
        Map<CommunicationCountryDirection, Integer> messages = new HashMap<>(messagesStat.size());
        messagesStat.forEach((k, v) -> {
            messages.put(k,v.intValue());
        });

        return new CommunicationStatistic(rows.intValue(),
                incompleteRows.intValue(),
                emptyMessages.intValue(),
                errors.intValue(),
                calls,
                messages,
                averageDurations,
                koCalls.doubleValue() / koCalls.doubleValue() + okCalls.doubleValue(),
                wordOccurrence);
    }

    /**
     * Split text to list of words.
     * @param s sentence to be splitted
     * @return List of words
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
     * @param wordsCounter into which words are to be counted
     * @param words to be counted into words counter
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
    private static final class SmallCallStat {
        private final AtomicInteger calls = new AtomicInteger();
        private final AtomicInteger duration = new AtomicInteger();
    }
}
