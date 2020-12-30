package net.fungriml.elog4j.api;

import java.util.Collections;

import lombok.NonNull;

public class Logger {

    @NonNull
    private Category category;

    @NonNull
    private ClockService clock;

    public boolean isDebugEnabled() {
        return isEnabled(LogLevel.DEBUG);
    }

    public boolean isEnabled(LogLevel level) {
        return category.implies(level);
    }

    public void debug(String msg, Decorator d) {
        log(LogLevel.DEBUG, msg, d, null);
    }

    private void log(LogLevel level, String msg, Decorator d, Throwable e) {
        if (category.implies(level)) {
            LogEvent.Builder b = LogEvent.builder(clock.now(), level).summary(msg).cause(e);

            if (category.isCaptureSource()) {
                // TODO
            }

            if (d != null) {
                d.decorate(b);
            }

            category.sink(Collections.singletonList(b.build()));
        }
    }
}
