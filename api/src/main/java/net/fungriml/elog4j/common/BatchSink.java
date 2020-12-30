package net.fungriml.elog4j.common;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import net.fungriml.elog4j.api.LogEvent;
import net.fungriml.elog4j.api.Sink;

@RequiredArgsConstructor
public class BatchSink implements Sink {

    public static BatchSink wrap(Sink target, int size) {
        Arguments.test(b -> b > 0, size, "Batch size must be a positive number");
        Arguments.notNull(target);
        return new BatchSink(size, target);
    }

    public static BatchSink wrap(Sink target) {
        return wrap(target, 64);
    }

    private Integer size;
    private Sink target;

    private List<LogEvent> batch;

    BatchSink(int size, Sink target) {
        batch = new ArrayList<LogEvent>(size);
        this.size = size;
        this.target = target;
    }

    // visible for testing
    int size() {
        return batch.size();
    }

    @Override
    public void sink(List<LogEvent> e) {
        int len = e.size();
        int cap = size - batch.size();
        if (cap >= len) {
            batch.addAll(e);
            checkFlush();
        } else {
            List<LogEvent> sub = e.subList(0, cap);
            batch.addAll(sub);
            checkFlush();
            sink(e.subList(cap, e.size()));
        }
    }

    private void checkFlush() {
        if (batch.size() >= size) {
            flush();
        }
    }

    public void flush() {
        target.sink(batch);
        batch.clear();
    }
}
