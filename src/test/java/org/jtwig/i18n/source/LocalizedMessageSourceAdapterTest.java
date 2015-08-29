package org.jtwig.i18n.source;

import com.google.common.base.Optional;
import org.jtwig.i18n.source.message.MessageSource;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Locale;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocalizedMessageSourceAdapterTest {
    private final HashMap<Locale, MessageSource> messageSourceMap = new HashMap<Locale, MessageSource>();
    private LocalizedMessageSourceAdapter underTest = new LocalizedMessageSourceAdapter(messageSourceMap);

    @Before
    public void setUp() throws Exception {
        messageSourceMap.clear();
    }

    @Test
    public void messageWhenNoMessageSource() throws Exception {
        Optional<String> result = underTest.message(Locale.CANADA, "original");

        assertThat(result.isPresent(), is(false));
    }

    @Test
    public void messageWhenMessageSource() throws Exception {
        String message = "original";
        Optional<String> expected = Optional.of("replacement");

        MessageSource messageSource = mock(MessageSource.class);
        messageSourceMap.put(Locale.CANADA, messageSource);
        when(messageSource.message(message)).thenReturn(expected);

        Optional<String> result = underTest.message(Locale.CANADA, message);

        assertEquals(expected, result);
    }
}