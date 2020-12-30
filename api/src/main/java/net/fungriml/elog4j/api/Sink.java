package net.fungriml.elog4j.api;

import java.util.List;

public interface Sink {

    public void sink(List<LogEvent> e);

}
