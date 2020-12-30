package net.fungriml.elog4j.api;

import org.junit.Assert;
import org.junit.Test;

public class LogLevelTest {

    @Test
    public void impliesHigherSeverity() {
        Assert.assertTrue(LogLevel.ERROR.implies(LogLevel.FATAL));
    }

    @Test
    public void impliesSameSeverity() {
        Assert.assertTrue(LogLevel.ERROR.implies(LogLevel.ERROR));
    }

    @Test
    public void doesNotImplyLowerSeverity() {
        Assert.assertFalse(LogLevel.ERROR.implies(LogLevel.DEBUG));
    }
}
