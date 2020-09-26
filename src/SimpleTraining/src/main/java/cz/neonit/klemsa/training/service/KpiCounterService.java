package cz.neonit.klemsa.training.service;

import cz.neonit.klemsa.training.domain.communication.*;
import cz.neonit.klemsa.training.domain.kpi.Kpi;
import org.springframework.stereotype.Service;

import java.util.*;
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
     * @param communicationStatistic
     */
    public void addCommunicationStatistic(CommunicationStatistic communicationStatistic) {
        this.files.incrementAndGet();
        this.rows.addAndGet(communicationStatistic.getRows());

        // Total calls & country codes.
        communicationStatistic.getCalls().forEach((k, v) -> {
            this.calls.addAndGet(v);
            this.originCountryCodes.add(k.originCc);
            this.destinationCountryCodes.add(k.destinationCc);
        });

        // Total messages & country codes.
        communicationStatistic.getMessages().forEach((k, v) -> {
            this.messages.addAndGet(v);
            this.originCountryCodes.add(k.originCc);
            this.destinationCountryCodes.add(k.destinationCc);
        });
    }
}
