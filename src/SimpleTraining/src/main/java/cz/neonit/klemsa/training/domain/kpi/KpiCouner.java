package cz.neonit.klemsa.training.domain.kpi;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author tomasklemsa
 */
public class KpiCouner {
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
    private KpiCouner() {
        this.files = new AtomicInteger();
        this.rows = new AtomicInteger();
        this.calls = new AtomicInteger();
        this.messages = new AtomicInteger();
        this.originCountryCodes = Collections.synchronizedSet(new HashSet<>());
        this.destinationCountryCodes = Collections.synchronizedSet(new HashSet<>());
        this.duration = new AtomicLong();
    }

    /**
     * Gets thread safe KPI counter.
     * @return KpiCounter
     */
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public KpiCouner kpiCounter() {
        return new KpiCouner();
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
}
