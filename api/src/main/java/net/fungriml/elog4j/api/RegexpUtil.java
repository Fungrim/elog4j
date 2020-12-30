package net.fungriml.elog4j.api;

import java.util.regex.Pattern;

public class RegexpUtil {

    public static Pattern packageToRegexp(Package p) {
        return Pattern.compile("^" + p.getName() + "\\..*");
    }
}
