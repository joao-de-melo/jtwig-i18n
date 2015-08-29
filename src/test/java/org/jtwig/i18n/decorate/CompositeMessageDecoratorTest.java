package org.jtwig.i18n.decorate;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CompositeMessageDecoratorTest {
    private final Collection<MessageDecorator> messageDecorators = new ArrayList<MessageDecorator>();
    private CompositeMessageDecorator underTest = new CompositeMessageDecorator(messageDecorators);

    @Before
    public void setUp() throws Exception {
        messageDecorators.clear();
    }

    @Test
    public void decorate() throws Exception {
        String message = "message";

        MessageDecorator first = mock(MessageDecorator.class);
        MessageDecorator second = mock(MessageDecorator.class);

        messageDecorators.add(first);
        messageDecorators.add(second);

        when(first.decorate(message)).thenReturn("first");
        when(second.decorate("first")).thenReturn("second");

        String result = underTest.decorate(message);

        assertThat(result, is("second"));
    }
}