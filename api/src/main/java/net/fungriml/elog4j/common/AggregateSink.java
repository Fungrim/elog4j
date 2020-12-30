package net.fungriml.elog4j.common;

import java.util.Arrays;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import net.fungriml.elog4j.api.LogEvent;
import net.fungriml.elog4j.api.Sink;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class AggregateSink implements Sink {

    public static AggregateSink of(Sink... sinks) {
        return new AggregateSink(sinks == null ? new Sink[0] : sinks);
    }

    @NonNull
    private Sink[] sinks;

    @Override
    public void sink(List<LogEvent> e) {
        Arrays.stream(sinks).forEach(s -> s.sink(e));
    }
}
