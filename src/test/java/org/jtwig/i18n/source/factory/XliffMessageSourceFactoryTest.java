package org.jtwig.i18n.source.factory;

import com.google.common.base.Optional;
import org.jtwig.i18n.source.message.MessageSource;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class XliffMessageSourceFactoryTest {
    @Test
    public void create() throws Exception {
        String xml = "<?xml version=\"1.0\"?>\n" +
                "<xliff version=\"1.2\" xmlns=\"urn:oasis:names:tc:xliff:document:1.2\">\n" +
                "    <file source-language=\"en\" datatype=\"plaintext\" original=\"file.ext\">\n" +
                "        <body>\n" +
                "            <trans-unit id=\"1\">\n" +
                "                <source>hello</source>\n" +
                "                <target>world</target>\n" +
                "            </trans-unit>\n" +
                "        </body>\n" +
                "    </file>\n" +
                "</xliff>";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        MessageSource messageSource = XliffMessageSourceFactory.create(inputStream);

        Optional<String> result = messageSource.message("hello");

        assertThat(result.isPresent(), is(true));
        assertThat(result.get(), is("world"));
    }
}