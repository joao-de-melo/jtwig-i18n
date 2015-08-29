package org.jtwig.i18n.source.message;

import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CompositeMessageSourceTest {
    private final ArrayList<MessageSource> messageSources = new ArrayList<MessageSource>();
    private CompositeMessageSource underTest = new CompositeMessageSource(messageSources);

    @Before
    public void setUp() throws Exception {
        messageSources.clear();
    }

    @Test
    public void messageWhenNotFound() throws Exception {
        String message = "test";

        Optional<String> result = underTest.message(message);

        assertThat(result.isPresent(), is(false));
    }

    @Test
    public void messageWhenFound() throws Exception {
        String message = "test";
        MessageSource first = mock(MessageSource.class);
        MessageSource second = mock(MessageSource.class);
        messageSources.add(first);
        messageSources.add(second);

        Optional<String> expected = Optional.of("expected");
        when(first.message(message)).thenReturn(expected);

        Optional<String> result = underTest.message(message);

        assertThat(result, is(expected));
        verify(second, never()).message(message);
    }
}