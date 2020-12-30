package net.fungriml.elog4j.api;

@FunctionalInterface
public interface Decorator {

    public static Decorator label(String name, String value) {
        return label(Label.of(name, value));
    }

    public static Decorator label(Label... labels) {
        return (e) -> {
            if (labels != null) {
                for (Label l : labels) {
                    e.label(l);
                }
            }
        };
    }

    public void decorate(LogEvent.Access e);

}
