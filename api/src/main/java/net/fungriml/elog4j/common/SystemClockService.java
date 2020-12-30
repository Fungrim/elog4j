package net.fungriml.elog4j.common;

import java.time.Clock;
import java.time.Instant;

import lombok.AllArgsConstructor;
import net.fungriml.elog4j.api.ClockService;

@AllArgsConstructor
public class SystemClockService implements ClockService {

    private Clock clock;

    @Override
    public Instant now() {
        return clock.instant();
    }
}
