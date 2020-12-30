package net.fungriml.elog4j.common;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import net.fungriml.elog4j.api.LogEvent;
import net.fungriml.elog4j.api.Sink;
import net.fungriml.elog4j.util.Arguments;

@AllArgsConstructor
public class AsyncSink implements Sink {

    public static AsyncSink wrap(Sink target, ExecutorService exec) {
        Arguments.notNull(target);
        Arguments.notNull(exec);
        return new AsyncSink(exec, target);
    }

    public static AsyncSink wrap(Sink target) {
        return wrap(target, Executors.newSingleThreadExecutor());
    }

    @NonNull
    private ExecutorService exec;

    @NonNull
    private Sink target;

    @Override
    public void sink(List<LogEvent> e) {
        exec.execute(() -> target.sink(e));
    }
}
