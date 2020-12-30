package net.fungriml.elog4j.common;

import java.util.function.Predicate;

public class Arguments {

    private static final Predicate<Object> NOT_NULL = (o) -> o != null;

    private static final Predicate<String> NOT_BLANK = (o) -> o != null && o.trim().length() > 0;

    private Arguments() {
    }

    public static <S> void test(Predicate<S> p, S arg, String msg) {
        if (!p.test(arg)) {
            throw new IllegalArgumentException(msg);
        }
    }

    public static void notNull(Object o, String msg) {
        test(NOT_NULL, o, msg);
    }

    public static void notNull(Object o) {
        notNull(o, "Argument must not be null");
    }

    public static void notBlank(String s) {
        test(NOT_BLANK, s, "String must not be null or empty");
    }
}
