package net.fungriml.elog4j.api;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.fungriml.elog4j.api.Payload.Node;
import net.fungriml.elog4j.common.Arguments;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class LogEvent {

    public static interface Access {

        public Access put(String name, String value);

        public Access put(String name, Boolean value);

        public Access put(String name, Number value);

        public Access putAll(Map<String, ?> payload);

        public Access set(Payload p);

        public Access summary(String msg);

        public Access label(String name, String value);

        public Access label(Label l);

    }

    public static class Builder implements Access {

        private Instant time;
        private LogLevel level;
        private String summary;

        private Payload payload = new Payload();
        private Map<String, String> labels = new HashMap<String, String>();
        private Throwable cause;

        Builder(Instant time, LogLevel level) {
            this.time = time;
            this.level = level;
        }

        public LogEvent build() {
            LogEvent e = new LogEvent(time, level);
            e.summary = summary;
            e.cause = cause;
            e.payload = payload;
            if (labels.size() > 0) {
                e.labels = labels;
            }
            return e;
        }

        public Builder put(String name, String value) {
            verifyPayloadExists();
            payload.set(name, Node.of(value));
            return this;
        }

        public Builder put(String name, Boolean value) {
            verifyPayloadExists();
            payload.set(name, Node.of(value));
            return this;
        }

        public Builder put(String name, Number value) {
            verifyPayloadExists();
            payload.set(name, Node.of(value));
            return this;
        }

        public Builder putAll(Map<String, ?> payload) {
            Payload p = Payload.of(payload);
            this.payload.integrate(p);
            return this;
        }

        public Builder set(Payload p) {
            if (p == null) {
                payload.clear();
            } else {
                payload = new Payload(p);
            }
            return this;
        }

        public Builder summary(String msg) {
            this.summary = msg;
            return this;
        }

        public Builder label(String name, String value) {
            return label(Label.of(name, value));
        }

        public Builder label(Label l) {
            Arguments.notNull(l);
            this.labels.put(l.getName(), l.getValue());
            return this;
        }

        private void verifyPayloadExists() {
            if (payload == null) {
                this.payload = Payload.empty();
            }
        }

        public Builder cause(Throwable e) {
            this.cause = e;
            return this;
        }
    }

    static Builder builder(Instant time, LogLevel level) {
        return new Builder(time, level);
    }

    @NonNull
    private Instant time;

    @NonNull
    private LogLevel level;

    private String summary;
    private Map<String, String> labels;
    private Payload payload;
    private Throwable cause;

    public Map<String, String> getLabels() {
        return labels == null ? Collections.emptyMap() : new HashMap<>(labels);
    }
}
