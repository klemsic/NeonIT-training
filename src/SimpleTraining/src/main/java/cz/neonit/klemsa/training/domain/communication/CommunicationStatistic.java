package cz.neonit.klemsa.training.domain.communication;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * @author tomasklemsa
 */
public final class CommunicationStatistic {
    private Integer incompleteRows;
    private Integer emptyMessages;
    private Integer errors;
    private Map<CommunicationCountryDirection,Integer> calls;
    private Map<CommunicationCountryDirection,Double> averageCallDurations;
    private Double failtureCallsRatio;
    private Map<String, Integer> wordOccurrence;

    /**
     *
     * @param incompleteRows
     * @param emptyMessages
     * @param errors
     * @param calls
     * @param averageCallDurations
     * @param failtureCallsRatio
     * @param wordOccurrence
     */
    @JsonCreator
    public CommunicationStatistic(@JsonProperty("incompleteRows") Integer incompleteRows,
                                  @JsonProperty("emptyMessages") Integer emptyMessages,
                                  @JsonProperty("errors") Integer errors,
                                  @JsonProperty("calls") Map<CommunicationCountryDirection,Integer> calls,
                                  @JsonProperty("averageCallDurations") Map<CommunicationCountryDirection, Double> averageCallDurations,
                                  @JsonProperty("failtureCallsRatio") Double failtureCallsRatio,
                                  @JsonProperty("wordOccurrence") Map<String, Integer> wordOccurrence) {
        this.incompleteRows = incompleteRows;
        this.emptyMessages = emptyMessages;
        this.errors = errors;
        this.calls = calls;
        this.averageCallDurations = averageCallDurations;
        this.failtureCallsRatio = failtureCallsRatio;
        this.wordOccurrence = wordOccurrence;
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
    public Map<CommunicationCountryDirection, Double> getAverageCallDurations() {
        return averageCallDurations;
    }

    /**
     *
     * @return
     */
    @JsonGetter
    public Double getFailtureCallsRatio() {
        return failtureCallsRatio;
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
