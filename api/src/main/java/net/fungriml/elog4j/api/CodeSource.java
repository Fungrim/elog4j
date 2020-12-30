package net.fungriml.elog4j.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class CodeSource {

    private String className;
    private String methodName;
    private int lineNumber;

    CodeSource(StackTraceElement el) {
        this.className = el.getClassName();
        this.lineNumber = el.getLineNumber();
        this.methodName = el.getMethodName();
    }
}
