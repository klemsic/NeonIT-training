package cz.neonit.klemsa.training.service;

import cz.neonit.klemsa.training.domain.kpi.Kpi;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author tomasklemsa
 */
@Service
public final class KpiCounterService {
    private final AtomicInteger files;
    private final AtomicInteger rows;
    private final AtomicInteger calls;
    private final AtomicInteger messages;
    private final Set<Integer> originCountryCodes;
    private final Set<Integer> destinationCountryCodes;
    private final AtomicLong duration;

    /**
     * Creates new instance of KPI counter with zero initialize.
     */
    public KpiCounterService() {
        this.files = new AtomicInteger();
        this.rows = new AtomicInteger();
        this.calls = new AtomicInteger();
        this.messages = new AtomicInteger();
        this.originCountryCodes = Collections.synchronizedSet(new HashSet<>());
        this.destinationCountryCodes = Collections.synchronizedSet(new HashSet<>());
        this.duration = new AtomicLong();
    }

    /**
     *
     * @return KPI statistic of service.
     */
    public synchronized Kpi getKpi() {
        return new Kpi(files.intValue(),
                rows.intValue(),
                calls.intValue(),
                messages.intValue(),
                originCountryCodes.size(),
                destinationCountryCodes.size(),
                duration.longValue());
    }

    /**
     *
     * @return
     */
    public Integer incrementFiles() {
        return files.incrementAndGet();
    }

    /**
     *
     * @return
     */
    public Integer incrementRows() {
        return rows.incrementAndGet();
    }

    /**
     *
     * @return
     */
    public Integer incrementCalls() {
        return calls.incrementAndGet();
    }

    /**
     *
     * @return
     */
    public Integer incrementMessages() {
        return messages.incrementAndGet();
    }

    /**
     *
     * @param cc country code
     */
    public void addOriginCountryCode(Integer cc) {
        originCountryCodes.add(cc);
    }

    /**
     *
     * @param cc country code
     */
    public void addDestinationCountryCode(Integer cc) {
        destinationCountryCodes.add(cc);
    }

    /**
     *
     * @param duration
     * @return
     */
    public Long addDuration(Long duration) {
        return this.duration.addAndGet(duration);
    }
}
