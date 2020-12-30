package net.fungriml.elog4j.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import net.fungriml.elog4j.util.Arguments;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Label {

    public static Label of(String name, String value) {
        Arguments.notBlank(name);
        Arguments.notBlank(value);
        return new Label(name, value);
    }

    @NonNull
    private String name;

    @NonNull
    private String value;

}
