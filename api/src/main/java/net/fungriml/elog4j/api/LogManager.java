package net.fungriml.elog4j.api;

import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.fungriml.elog4j.common.Arguments;
import net.fungriml.elog4j.common.ConsoleSink;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class LogManager {

    private static LogManager GLOBAL_INSTANCE;

    public static synchronized LogManager getGlobalManager() {
        if (GLOBAL_INSTANCE == null) {
            System.out.println("Global log manager requested without configuration, will create with a single catch-all category with a DEBUG filter and a system UTC clock");
            // create the global instance
            GLOBAL_INSTANCE = LogManager.builder(ClockService.systemUtc()).build();
            GLOBAL_INSTANCE.routeUnmatched().through(Filter.level(LogLevel.DEBUG)).to(ConsoleSink.getDefaultInstance());
        }
        return GLOBAL_INSTANCE;
    }

    public static synchronized void setGlobalManager(LogManager man) {
        if (GLOBAL_INSTANCE != null) {
            System.out.println("Global log manager replaced");
        }
        GLOBAL_INSTANCE = man;
    }

    public static class Builder {

        private ClockService clock;
        private boolean isGlobal = false;

        Builder(ClockService clock) {
            this.clock = clock;
        }

        public LogManager build() {
            LogManager man = new LogManager(clock);
            if (isGlobal) {
                setGlobalManager(man);
            }
            return man;
        }

        public Builder global() {
            isGlobal = true;
            return this;
        }
    }

    public static Builder builder(ClockService clock) {
        Arguments.notNull(clock);
        return new Builder(clock);
    }

    public static Builder builder() {
        return builder(ClockService.systemUtc());
    }

    @NonNull
    private ClockService clockService;

    public Category.Builder route(Package site) {
        Arguments.notNull(site);
        return route(RegexpUtil.packageToRegexp(site));
    }

    public Category.Builder route(Pattern siteRegexp) {
        Arguments.notNull(siteRegexp);
        return Category.builder(this, siteRegexp);
    }

    public Category.Builder route(String siteRegexp) {
        Arguments.notNull(siteRegexp);
        return route(Pattern.compile(siteRegexp));
    }

    public Category.Builder routeUnmatched() {
        return route(Pattern.compile(".*"));
    }

    public Logger getLogger(Class<?> site) {

        return null;
    }

    public Logger getLogger(String site) {

        return null;
    }
}
