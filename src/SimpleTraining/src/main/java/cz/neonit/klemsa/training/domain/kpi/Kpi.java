package cz.neonit.klemsa.training.domain.kpi;

/**
 * @author tomasklemsa
 */
public class Kpi {
    private final Integer files;
    private final Integer rows;
    private final Integer calls;
    private final Integer messages;
    private final Integer origins;
    private final Integer destinations;
    private final Long duration;

    /**
     *
     * @param files
     * @param rows
     * @param calls
     * @param messages
     * @param origins
     * @param destinations
     * @param duration
     */
    public Kpi(Integer files,
               Integer rows,
               Integer calls,
               Integer messages,
               Integer origins,
               Integer destinations,
               Long duration) {
        this.files = files;
        this.rows = rows;
        this.calls = calls;
        this.messages = messages;
        this.origins = origins;
        this.destinations = destinations;
        this.duration = duration;
    }

    /**
     *
     * @return number of processed files.
     */
    public Integer getFiles() {
        return files;
    }

    /**
     *
     * @return number of processed rows.
     */
    public Integer getRows() {
        return rows;
    }

    /**
     *
     * @return number of processed calls.
     */
    public Integer getCalls() {
        return calls;
    }

    /**
     *
     * @return number of processed messages
     */
    public Integer getMessages() {
        return messages;
    }

    /**
     *
     * @return number of different origin country codes.
     */
    public Integer getOrigins() {
        return origins;
    }

    /**
     *
     * @return number of different destination country codes.
     */
    public Integer getDestinations() {
        return destinations;
    }

    /**
     *
     * @return duration of each file process.
     */
    public Long getDuration() {
        return duration;
    }
}
