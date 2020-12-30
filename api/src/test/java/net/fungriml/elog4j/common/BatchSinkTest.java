package net.fungriml.elog4j.common;

import java.util.ArrayList;
import java.util.List;

import net.fungriml.elog4j.api.LogEvent;
import net.fungriml.elog4j.api.Sink;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class BatchSinkTest {

    private Sink mockTarget;
    private BatchSink sink;

    @Before
    public void init() {
        mockTarget = Mockito.mock(Sink.class);
        sink = BatchSink.wrap(mockTarget, 5);
    }

    @Test
    public void shouldBatchSingleEvent() {
        sink(1);
        Mockito.verifyZeroInteractions(mockTarget);
        Assert.assertEquals(1, sink.size());
    }

    @Test
    public void shouldSplitSingleBatches() {
        sink(6);
        Mockito.verify(mockTarget, Mockito.times(1)).sink(Mockito.anyList());
        Assert.assertEquals(1, sink.size());
    }

    @Test
    public void shouldSplitMultipleBatches() {
        sink(17);
        Mockito.verify(mockTarget, Mockito.times(3)).sink(Mockito.anyList());
        Assert.assertEquals(2, sink.size());
    }

    private void sink(int size) {
        List<LogEvent> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            LogEvent e = Mockito.mock(LogEvent.class);
            Mockito.when(e.getSummary()).thenReturn("Event " + (i + 1));
            list.add(e);
        }
        sink.sink(list);
    }
}
