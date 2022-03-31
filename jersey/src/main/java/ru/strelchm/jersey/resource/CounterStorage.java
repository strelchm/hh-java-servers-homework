package ru.strelchm.jersey.resource;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterStorage {
    private static final AtomicInteger VISIT_TOTAL_COUNTER = new AtomicInteger(0);

    public GetCounterResponse getCounter() {
        return new GetCounterResponse(LocalDateTime.now(), VISIT_TOTAL_COUNTER.get());
    }

    public int incrementAndGet() {
        return VISIT_TOTAL_COUNTER.incrementAndGet();
    }

    public int decrementAndGet() {
        return VISIT_TOTAL_COUNTER.decrementAndGet();
    }

    public void clear() {
        VISIT_TOTAL_COUNTER.set(0);
    }
}
