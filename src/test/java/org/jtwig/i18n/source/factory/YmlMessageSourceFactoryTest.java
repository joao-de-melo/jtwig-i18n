package org.jtwig.i18n.source.factory;

import com.google.common.base.Optional;
import org.jtwig.i18n.source.message.MessageSource;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class YmlMessageSourceFactoryTest {

    @Test
    public void create() throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("hello: \"world\"".getBytes());

        MessageSource messageSource = YmlMessageSourceFactory.create(inputStream);

        Optional<String> result = messageSource.message("hello");

        assertThat(result.isPresent(), is(true));
        assertThat(result.get(), is("world"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNonMap() throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("hello: \n  world: \"test\"".getBytes());
        YmlMessageSourceFactory.create(inputStream);
    }
}