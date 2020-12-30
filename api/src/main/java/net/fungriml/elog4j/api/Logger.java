package net.fungriml.elog4j.api;

import java.util.Collections;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.fungriml.elog4j.util.StackUtil;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
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

    public void debug(String msg) {
        log(LogLevel.DEBUG, msg, null, null);
    }

    public void debug(String msg, Decorator d) {
        log(LogLevel.DEBUG, msg, d, null);
    }

    public void debug(String msg, Throwable cause) {
        log(LogLevel.DEBUG, msg, null, cause);
    }

    public void debug(String msg, Throwable cause, Decorator d) {
        log(LogLevel.DEBUG, msg, d, cause);
    }

    public boolean isTraceEnabled() {
        return isEnabled(LogLevel.TRACE);
    }

    public void trace(String msg) {
        log(LogLevel.TRACE, msg, null, null);
    }

    public void trace(String msg, Decorator d) {
        log(LogLevel.TRACE, msg, d, null);
    }

    public void trace(String msg, Throwable cause) {
        log(LogLevel.TRACE, msg, null, cause);
    }

    public void trace(String msg, Throwable cause, Decorator d) {
        log(LogLevel.TRACE, msg, d, cause);
    }

    public boolean isWarnEnabled() {
        return isEnabled(LogLevel.WARN);
    }

    public void warn(String msg) {
        log(LogLevel.WARN, msg, null, null);
    }

    public void warn(String msg, Decorator d) {
        log(LogLevel.WARN, msg, d, null);
    }

    public void warn(String msg, Throwable cause) {
        log(LogLevel.WARN, msg, null, cause);
    }

    public void warn(String msg, Throwable cause, Decorator d) {
        log(LogLevel.WARN, msg, d, cause);
    }

    public boolean isInfoEnabled() {
        return isEnabled(LogLevel.INFO);
    }

    public void info(String msg, Decorator d) {
        log(LogLevel.INFO, msg, d, null);
    }

    public void info(String msg, Throwable cause) {
        log(LogLevel.INFO, msg, null, cause);
    }

    public void info(String msg, Throwable cause, Decorator d) {
        log(LogLevel.INFO, msg, d, cause);
    }

    public void info(String msg) {
        log(LogLevel.INFO, msg, null, null);
    }

    public boolean isErrorEnabled() {
        return isEnabled(LogLevel.ERROR);
    }

    public void error(String msg) {
        log(LogLevel.ERROR, msg, null, null);
    }

    public void error(String msg, Decorator d) {
        log(LogLevel.ERROR, msg, d, null);
    }

    public void error(String msg, Throwable cause, Decorator d) {
        log(LogLevel.ERROR, msg, d, cause);
    }

    public void error(String msg, Throwable cause) {
        log(LogLevel.ERROR, msg, null, cause);
    }

    public void fatal(String msg, Throwable cause, Decorator d) {
        log(LogLevel.FATAL, msg, d, cause);
    }

    public void fatal(String msg, Throwable cause) {
        log(LogLevel.FATAL, msg, null, cause);
    }

    public void fatal(String msg, Decorator d) {
        log(LogLevel.FATAL, msg, d, null);
    }

    public void fatal(String msg) {
        log(LogLevel.FATAL, msg, null, null);
    }

    private void log(LogLevel level, String msg, Decorator d, Throwable e) {
        if (category.implies(level)) {
            Thread th = Thread.currentThread();
            LogEvent.Builder b = LogEvent.builder(clock.now(), level).summary(msg).cause(e).thread(th);

            if (category.isCaptureSource()) {
                Optional<StackTraceElement> opt = StackUtil.findFirstForeignClass(th);
                if (opt.isPresent()) {
                    b = b.source(opt.get());
                }
            }

            if (d != null) {
                d.decorate(b);
            }

            category.sink(Collections.singletonList(b.build()));
        }
    }
}
