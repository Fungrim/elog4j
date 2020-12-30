package net.fungriml.elog4j.api;

import org.junit.Test;
import org.mockito.Mockito;

public class LogEventFilterTest {

    @Test
    public void shouldNotCallNextIfConditionalFails() {
        FilterChain chain = Mockito.mock(FilterChain.class);
        Filter f = Filter.conditional(e -> false);
        f.filter(null, chain);
        Mockito.verifyZeroInteractions(chain);
    }

    @Test
    public void shouldNotCallNextIfConditionalSucceeds() {
        FilterChain chain = Mockito.mock(FilterChain.class);
        Filter f = Filter.conditional(e -> true);
        f.filter(null, chain);
        Mockito.verify(chain).next();
    }
}