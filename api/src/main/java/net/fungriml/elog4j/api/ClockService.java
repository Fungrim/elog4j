package net.fungriml.elog4j.api;

import java.time.Clock;
import java.time.Instant;

@FunctionalInterface
public interface ClockService {

    public static ClockService systemUtc() {
        return () -> Clock.systemUTC().instant();
    }

    public Instant now();

}
