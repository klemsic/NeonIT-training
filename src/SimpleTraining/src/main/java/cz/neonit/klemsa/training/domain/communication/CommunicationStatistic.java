package cz.neonit.klemsa.training.domain.communication;

import java.util.Map;

/**
 * @author tomasklemsa
 */
public final class CommunicationStatistic {
    private final Integer incompleteRows;
    private final Integer emptyMessages;
    private final Integer errors;
    private final Map<CommunicationCountryDirection,Integer> calls;
    private final Map<CommunicationCountryDirection,Double> averageCallDurations;
    private final Double failtureCallsRatio;
    private final Map<String, Integer> wordOccurrence;


    public CommunicationStatistic(Integer incompleteRows,
                                  Integer emptyMessages,
                                  Integer errors,
                                  Map<CommunicationCountryDirection,Integer> calls,
                                  Map<CommunicationCountryDirection, Double> averageCallDurations,
                                  Double failtureCallsRatio,
                                  Map<String, Integer> wordOccurrence) {
        this.incompleteRows = incompleteRows;
        this.emptyMessages = emptyMessages;
        this.errors = errors;
        this.calls = calls;
        this.averageCallDurations = averageCallDurations;
        this.failtureCallsRatio = failtureCallsRatio;
        this.wordOccurrence = wordOccurrence;
    }

}
