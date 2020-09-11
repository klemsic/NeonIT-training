package cz.neonit.klemsa.training.domain.message;

import java.util.Map;

/**
 * @author tomasklemsa
 */
public class CommunicationStatistic {
    private final Integer incompleteRows;
    private final Integer emptyMessages;
    private final Map<Integer,Integer> outgoingCallsByCountry;
    private final Map<Integer,Integer> incomingCallsByCountry;
    private final Map<Integer,Double> avarageCallsDurationByCountry;
    private final Double failtureCallsRatio;
    private final Integer givenWordCount;

    /**
     *
     * @param incompleteRows
     * @param emptyMessages
     * @param outgoingCallsByCountry
     * @param incomingCallsByCountry
     * @param avarageCallsDurationByCountry
     * @param failtureCallsRatio
     * @param givenWordCount
     */
    public CommunicationStatistic(Integer incompleteRows,
                                  Integer emptyMessages,
                                  Map<Integer, Integer> outgoingCallsByCountry,
                                  Map<Integer, Integer> incomingCallsByCountry,
                                  Map<Integer, Double> avarageCallsDurationByCountry,
                                  Double failtureCallsRatio,
                                  Integer givenWordCount) {
        this.incompleteRows = incompleteRows;
        this.emptyMessages = emptyMessages;
        this.outgoingCallsByCountry = outgoingCallsByCountry;
        this.incomingCallsByCountry = incomingCallsByCountry;
        this.avarageCallsDurationByCountry = avarageCallsDurationByCountry;
        this.failtureCallsRatio = failtureCallsRatio;
        this.givenWordCount = givenWordCount;
    }

    public Integer getIncompleteRows() {
        return incompleteRows;
    }

    public Integer getEmptyMessages() {
        return emptyMessages;
    }

    public Map<Integer, Integer> getOutgoingCallsByCountry() {
        return outgoingCallsByCountry;
    }

    public Map<Integer, Integer> getIncomingCallsByCountry() {
        return incomingCallsByCountry;
    }

    public Map<Integer, Double> getAvarageCallsDurationByCountry() {
        return avarageCallsDurationByCountry;
    }

    public Double getFailtureCallsRatio() {
        return failtureCallsRatio;
    }

    public Integer getGivenWordCount() {
        return givenWordCount;
    }
}
