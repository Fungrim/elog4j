package net.fungriml.elog4j.common;

import net.fungriml.elog4j.util.Arguments;

import org.junit.Test;

public class ArgumentsTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldCatchNullArguments() {
        Arguments.notNull(null);
    }

    @Test
    public void shouldAllowNonNullArguments() {
        Arguments.notNull("");
    }

    @Test
    public void shouldAllowNonBlankStrings() {
        Arguments.notBlank("k");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCatchBlankStrings() {
        Arguments.notBlank(" ");
    }
}
