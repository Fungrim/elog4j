package test;

import java.util.Optional;

import net.fungriml.elog4j.util.StackUtil;

public class TestClass {

    public Optional<StackTraceElement> findSelf() {
        return StackUtil.findFirstForeignClass(Thread.currentThread());
    }
}
