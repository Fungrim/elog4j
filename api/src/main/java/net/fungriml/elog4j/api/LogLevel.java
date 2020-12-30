package net.fungriml.elog4j.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogLevel {

    TRACE(1), DEBUG(2), INFO(3), WARN(4), ERROR(5), FATAL(6);

    private int level;

    public boolean implies(LogLevel other) {
        return this.level <= other.level;
    }
}
