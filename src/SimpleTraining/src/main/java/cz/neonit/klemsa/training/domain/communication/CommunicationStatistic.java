package cz.neonit.klemsa.training.domain.communication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * @author tomasklemsa
 */
public final class CommunicationStatistic {
    private final Integer rows;
    private final Integer incompleteRows;
    private final Integer emptyMessages;
    private final Integer errors;
    private final Map<CommunicationCountryDirection,Integer> calls;
    private final Map<CommunicationCountryDirection,Integer> messages;
    private final Map<CommunicationCountryDirection,Double> averageCallDurations;
    private final Double failureCallsRatio;
    private final Map<String, Integer> wordOccurrence;

    /**
     *
     * @param incompleteRows
     * @param emptyMessages
     * @param errors
     * @param calls
     * @param averageCallDurations
     * @param failureCallsRatio
     * @param wordOccurrence
     */
    @JsonCreator
    public CommunicationStatistic(@JsonProperty("rows") Integer rows,
                                  @JsonProperty("incompleteRows") Integer incompleteRows,
                                  @JsonProperty("emptyMessages") Integer emptyMessages,
                                  @JsonProperty("errors") Integer errors,
                                  @JsonProperty("calls") Map<CommunicationCountryDirection,Integer> calls,
                                  @JsonProperty("messages") Map<CommunicationCountryDirection,Integer> messages,
                                  @JsonProperty("averageCallDurations") Map<CommunicationCountryDirection, Double> averageCallDurations,
                                  @JsonProperty("failureCallsRatio") Double failureCallsRatio,
                                  @JsonProperty("wordOccurrence") Map<String, Integer> wordOccurrence) {
        this.rows = rows;
        this.incompleteRows = incompleteRows;
        this.emptyMessages = emptyMessages;
        this.errors = errors;
        this.calls = calls;
        this.messages = messages;
        this.averageCallDurations = averageCallDurations;
        this.failureCallsRatio = failureCallsRatio;
        this.wordOccurrence = wordOccurrence;
    }

    /**
     *
     * @return
     */
    @JsonGetter
    public Integer getRows() {
        return rows;
    }

    /**
     *
     * @return
     */
    @JsonGetter
    public Integer getIncompleteRows() {
        return incompleteRows;
    }

    /**
     *
     * @return
     */
    @JsonGetter
    public Integer getEmptyMessages() {
        return emptyMessages;
    }

    /**
     *
     * @return
     */
    @JsonGetter
    public Integer getErrors() {
        return errors;
    }

    /**
     *
     * @return
     */
    @JsonGetter
    public Map<CommunicationCountryDirection, Integer> getCalls() {
        return calls;
    }

    /**
     *
     * @return
     */
    @JsonGetter
    public Map<CommunicationCountryDirection, Integer> getMessages() {
        return messages;
    }

    /**
     *
     * @return
     */
    @JsonGetter
    public Map<CommunicationCountryDirection, Double> getAverageCallDurations() {
        return averageCallDurations;
    }

    /**
     *
     * @return
     */
    @JsonGetter
    public Double getFailureCallsRatio() {
        return failureCallsRatio;
    }

    /**
     *
     * @return
     */
    @JsonGetter
    public Map<String, Integer> getWordOccurrence() {
        return wordOccurrence;
    }


}
