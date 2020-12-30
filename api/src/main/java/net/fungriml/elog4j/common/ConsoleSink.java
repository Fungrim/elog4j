package net.fungriml.elog4j.common;

import java.util.List;

import net.fungriml.elog4j.api.LogEvent;
import net.fungriml.elog4j.api.Sink;

public class ConsoleSink implements Sink {

    private static final ConsoleSink DEFAULT = new ConsoleSink();

    public static ConsoleSink getDefaultInstance() {
        return DEFAULT;
    }

    @Override
    public void sink(List<LogEvent> e) {
        // TODO Auto-generated method stub

    }
}