package net.fungriml.elog4j.api;

import java.util.function.Predicate;

@FunctionalInterface
public interface Filter {

    public static Filter level(LogLevel threshold) {
        return conditional((e) -> threshold.implies(e.getLevel()));
    }

    public static Filter conditional(Predicate<LogEvent> p) {
        return (e, c) -> {
            if (p.test(e)) {
                c.next();
            }
        };
    }

    public void filter(LogEvent event, FilterChain chain);

}
