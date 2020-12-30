package net.fungriml.elog4j.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public class StackUtil {

    private StackUtil() {
    }

    public static Optional<StackTraceElement> findFirst(Thread th, Predicate<StackTraceElement> p) {
        return Arrays.stream(th.getStackTrace()).filter(p).findFirst();
    }

    public static Optional<StackTraceElement> findFirstForeignClass(Thread th) {
        return findFirst(th, f -> {
            return !f.getClassName().startsWith("java.lang.") && !f.getClassName().startsWith("net.fungriml.elog4j");
        });
    }
}
