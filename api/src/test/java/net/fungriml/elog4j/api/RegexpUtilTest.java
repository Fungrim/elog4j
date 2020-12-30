package net.fungriml.elog4j.api;

import java.util.regex.Pattern;

import net.fungriml.elog4j.api.test.TestClass;
import net.fungriml.elog4j.util.RegexpUtil;

import org.junit.Assert;
import org.junit.Test;

public class RegexpUtilTest {

    @Test
    public void shouldMatchClassInPackage() {
        Pattern p = RegexpUtil.packageToRegexp(getClass().getPackage());
        Assert.assertTrue(p.matcher(getClass().getName()).matches());
    }

    @Test
    public void shouldMatchClassInSubPackagePackage() {
        Pattern p = RegexpUtil.packageToRegexp(getClass().getPackage());
        Assert.assertTrue(p.matcher(TestClass.class.getName()).matches());
    }
}
