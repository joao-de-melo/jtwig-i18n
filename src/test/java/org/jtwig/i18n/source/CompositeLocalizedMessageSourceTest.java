package org.jtwig.i18n.source;

import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Locale;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CompositeLocalizedMessageSourceTest {
    private final ArrayList<LocalizedMessageSource> messageSource = new ArrayList<LocalizedMessageSource>();
    private CompositeLocalizedMessageSource underTest = new CompositeLocalizedMessageSource(messageSource);

    @Before
    public void setUp() throws Exception {
        messageSource.clear();
    }

    @Test
    public void messageWhenExists() throws Exception {
        String message = "message";
        Optional<String> expected = Optional.of("replacement");

        LocalizedMessageSource localizedMessageSource = mock(LocalizedMessageSource.class);
        messageSource.add(localizedMessageSource);
        when(localizedMessageSource.message(Locale.CANADA, message)).thenReturn(expected);

        Optional<String> result = underTest.message(Locale.CANADA, message);

        assertEquals(expected, result);
    }

    @Test
    public void messageWhenNotExists() throws Exception {
        String message = "message";

        LocalizedMessageSource localizedMessageSource = mock(LocalizedMessageSource.class);

        Optional<String> result = underTest.message(Locale.CANADA, message);

        assertThat(result.isPresent(), is(false));
    }
}