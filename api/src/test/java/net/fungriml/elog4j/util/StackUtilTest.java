package net.fungriml.elog4j.util;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import test.TestClass;

public class StackUtilTest {

    @Test
    public void shouldFindSelf() {
        Optional<StackTraceElement> opt = new TestClass().findSelf();
        Assert.assertTrue(opt.isPresent());
        StackTraceElement el = opt.get();
        Assert.assertEquals(TestClass.class.getName(), el.getClassName());
        Assert.assertEquals("findSelf", el.getMethodName());
    }
}
