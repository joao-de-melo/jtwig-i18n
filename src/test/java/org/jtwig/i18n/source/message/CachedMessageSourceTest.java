package org.jtwig.i18n.source.message;

import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CachedMessageSourceTest {
    private final Cache<String, Optional<String>> cache = CacheBuilder.<String, Optional<String>>newBuilder().build();
    private final MessageSource messageSource = mock(MessageSource.class);
    private CachedMessageSource underTest = new CachedMessageSource(cache, messageSource);

    @Before
    public void setUp() throws Exception {
        cache.cleanUp();
    }

    @Test
    public void messageWhenNotFound() throws Exception {
        String message = "test";
        when(messageSource.message(message)).thenReturn(Optional.<String>absent());

        Optional<String> result = underTest.message(message);

        assertThat(result.isPresent(), is(false));
    }

    @Test
    public void messageWhenFound() throws Exception {
        String message = "test";
        String expected = "expected";
        when(messageSource.message(message)).thenReturn(Optional.of(expected));

        underTest.message(message);
        Optional<String> result = underTest.message(message);

        assertThat(result.isPresent(), is(true));
        assertThat(result.get(), is(expected));
        verify(messageSource).message(message);
    }
}