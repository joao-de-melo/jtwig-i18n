package org.jtwig.i18n.source.message;

import com.google.common.base.Optional;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class PersistentCachedMessageSourceTest {
    private final MessageSource messageSource = mock(MessageSource.class);
    private PersistentCachedMessageSource underTest = new PersistentCachedMessageSource(messageSource);

    @Test
    public void messageNotCached() throws Exception {
        String message = "test";
        when(messageSource.message(message)).thenReturn(Optional.<String>absent());

        underTest.message(message);
        Optional<String> result = underTest.message(message);

        assertThat(result.isPresent(), is(false));
        verify(messageSource).message(message);
    }

    @Test
    public void messageCached() throws Exception {
        String message = "test";
        Optional<String> expected = Optional.of("expected");
        when(messageSource.message(message)).thenReturn(expected);

        underTest.message(message);
        Optional<String> result = underTest.message(message);

        assertThat(result, is(expected));
        verify(messageSource).message(message);
    }
}