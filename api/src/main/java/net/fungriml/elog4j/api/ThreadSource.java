package net.fungriml.elog4j.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class ThreadSource {

    private String threadName;
    private long threadId;
    private int threadPriority;

    ThreadSource(Thread th) {
        this.threadName = th.getName();
        this.threadId = th.getId();
        this.threadPriority = th.getPriority();
    }
}
