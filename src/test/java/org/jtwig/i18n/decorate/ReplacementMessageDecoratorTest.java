package org.jtwig.i18n.decorate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReplacementMessageDecoratorTest {
    private final ReplacementMessageDecorator.ReplacementProvider replacementProvider = mock(ReplacementMessageDecorator.ReplacementProvider.class);
    private ReplacementMessageDecorator underTest = new ReplacementMessageDecorator(replacementProvider);

    @Test
    public void decoratorWithoutArguments() throws Exception {
        String result = underTest.decorate("Hello");

        assertThat(result, is("Hello"));
    }

    @Test
    public void decoratorWithOneArgument() throws Exception {
        when(replacementProvider.replacement("name")).thenReturn("World");

        String result = underTest.decorate("Hello %name%");

        assertThat(result, is("Hello World"));
    }

    @Test
    public void decoratorWithDuplicateArgument() throws Exception {
        when(replacementProvider.replacement("name")).thenReturn("World");

        String result = underTest.decorate("Hello %name% and %name%");

        assertThat(result, is("Hello World and World"));
    }

    @Test
    public void decoratorWithTwoArguments() throws Exception {
        when(replacementProvider.replacement("name")).thenReturn("World");
        when(replacementProvider.replacement("value")).thenReturn("2");

        String result = underTest.decorate("Hello %name% and %value%");

        assertThat(result, is("Hello World and 2"));
    }
}