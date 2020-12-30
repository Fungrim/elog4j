package net.fungriml.elog4j.api;

import java.util.List;
import java.util.regex.Pattern;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Category implements Sink {

    public static class Builder {

        private boolean isRoot;
        private Pattern sitePattern;

        Builder(LogManager parent, Pattern sitePattern) {
            this.sitePattern = sitePattern;
            this.isRoot = false;
        }

        Builder(LogManager parent) {
            this.isRoot = true;
        }

        public Builder at(LogLevel level) {
            return this;
        }

        public Builder with(Decorator decorator) {
            return this;
        }

        public Builder through(Filter filter) {
            return this;
        }

        public Builder extractSource() {
            return this;
        }

        public void to(Sink sink) {

        }
    }

    static Builder builder(LogManager parent, Pattern sitePattern) {
        return new Builder(parent, sitePattern);
    }

    static Builder builder(LogManager parent) {
        return new Builder(parent);
    }

    @NonNull
    private Pattern sitePattern;

    @NonNull
    private LogLevel threshold;

    private boolean captureSource = false;

    public boolean matches(String siteName) {
        return siteName.matches(siteName);
    }

    public boolean matches(Class<?> site) {
        return site == null ? false : matches(site.getName());
    }

    public boolean implies(LogLevel level) {
        return threshold.implies(level);
    }

    @Override
    public void sink(List<LogEvent> e) {
        // TODO Auto-generated method stub

    }
}
