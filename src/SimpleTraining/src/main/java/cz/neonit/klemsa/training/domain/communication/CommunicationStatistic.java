package cz.neonit.klemsa.training.domain.communication;

import java.util.Map;
import java.util.Objects;

/**
 * @author tomasklemsa
 */
public final class CommunicationStatistic {
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

    /**
     *
     * @return
     */
    public Integer getIncompleteRows() {
        return incompleteRows;
    }

    /**
     *
     * @return
     */
    public Integer getEmptyMessages() {
        return emptyMessages;
    }

    /**
     *
     * @return
     */
    public Map<Integer, Integer> getOutgoingCallsByCountry() {
        return outgoingCallsByCountry;
    }

    /**
     *
     * @return
     */
    public Map<Integer, Integer> getIncomingCallsByCountry() {
        return incomingCallsByCountry;
    }

    /**
     *
     * @return
     */
    public Map<Integer, Double> getAvarageCallsDurationByCountry() {
        return avarageCallsDurationByCountry;
    }

    /**
     *
     * @return
     */
    public Double getFailtureCallsRatio() {
        return failtureCallsRatio;
    }

    /**
     *
     * @return
     */
    public Integer getGivenWordCount() {
        return givenWordCount;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommunicationStatistic that = (CommunicationStatistic) o;
        return Objects.equals(incompleteRows, that.incompleteRows) &&
                Objects.equals(emptyMessages, that.emptyMessages) &&
                Objects.equals(outgoingCallsByCountry, that.outgoingCallsByCountry) &&
                Objects.equals(incomingCallsByCountry, that.incomingCallsByCountry) &&
                Objects.equals(avarageCallsDurationByCountry, that.avarageCallsDurationByCountry) &&
                Objects.equals(failtureCallsRatio, that.failtureCallsRatio) &&
                Objects.equals(givenWordCount, that.givenWordCount);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(incompleteRows, emptyMessages, outgoingCallsByCountry, incomingCallsByCountry, avarageCallsDurationByCountry, failtureCallsRatio, givenWordCount);
    }
}
