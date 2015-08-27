package org.jtwig.i18n.decorate;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReplacementMessageDecoratorTest {
    private final ArrayList<ReplacementMessageDecorator.Replacement> replacements = new ArrayList<ReplacementMessageDecorator.Replacement>();
    private ReplacementMessageDecorator underTest = new ReplacementMessageDecorator(replacements);

    @Before
    public void setUp() throws Exception {
        replacements.clear();
    }

    @Test
    public void decoratorWithoutArguments() throws Exception {
        String result = underTest.decorate("Hello");

        assertThat(result, is("Hello"));
    }

    @Test
    public void decoratorWithOneArgument() throws Exception {
        replacements.add(new ReplacementMessageDecorator.Replacement("%name%", "World"));

        String result = underTest.decorate("Hello %name%");

        assertThat(result, is("Hello World"));
    }

    @Test
    public void decoratorWithDuplicateArgument() throws Exception {
        replacements.add(new ReplacementMessageDecorator.Replacement("%name%", "World"));

        String result = underTest.decorate("Hello %name% and %name%");

        assertThat(result, is("Hello World and World"));
    }

    @Test
    public void decoratorWithTwoArguments() throws Exception {
        replacements.add(new ReplacementMessageDecorator.Replacement("%name%", "World"));
        replacements.add(new ReplacementMessageDecorator.Replacement("%value%", "2"));

        String result = underTest.decorate("Hello %name% and %value%");

        assertThat(result, is("Hello World and 2"));
    }
}