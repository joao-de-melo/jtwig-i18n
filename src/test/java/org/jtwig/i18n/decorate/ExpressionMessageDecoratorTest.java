package org.jtwig.i18n.decorate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExpressionMessageDecoratorTest {
    private final ExpressionMessageDecorator.ReplacementFinder replacementFinder = mock(ExpressionMessageDecorator.ReplacementFinder.class);
    private ExpressionMessageDecorator underTest = new ExpressionMessageDecorator(replacementFinder);

    @Test
    public void decorate() throws Exception {
        when(replacementFinder.replacementFor("test")).thenReturn("oi");

        String result = underTest.decorate("hello %test%");

        assertThat(result, is("hello oi"));
    }
}