package org.jtwig.i18n.integration;

import com.google.common.base.Optional;
import org.jtwig.i18n.MessageResolver;
import org.jtwig.i18n.MessageResolverConfiguration;
import org.jtwig.i18n.MessageResolverConfigurationBuilder;
import org.jtwig.i18n.MessageResolverFactory;
import org.jtwig.i18n.decorate.CompositeMessageDecorator;
import org.jtwig.i18n.decorate.MessageDecorator;
import org.jtwig.i18n.decorate.ReplacementMessageDecorator;
import org.jtwig.i18n.source.LocalizedMessageSource;
import org.jtwig.i18n.source.LocalizedMessageSourceAdapter;
import org.jtwig.i18n.source.message.MapMessageSource;
import org.jtwig.i18n.source.message.MessageSource;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MessageResolverIntegrationTest {

    @Test
    public void test() throws Exception {
        MessageResolverFactory messageResolverFactory = new MessageResolverFactory();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("test", "one %blah%");
        MessageResolverConfigurationBuilder messageResolverConfigurationBuilder = new MessageResolverConfigurationBuilder(new MessageResolverConfiguration(Collections.<LocalizedMessageSource>emptyList()))
                .withMessageSource(Locale.CANADA, new MapMessageSource(map))
                .withMessageSources(Collections.<LocalizedMessageSource>emptyList())
                .withMessageSource(new LocalizedMessageSourceAdapter(new HashMap<Locale, MessageSource>()))
                .withMessageSources(Locale.CANADA, Collections.<MessageSource>emptyList())
                ;

        MessageResolver messageResolver = messageResolverFactory.create(messageResolverConfigurationBuilder.build());

        Optional<String> result = messageResolver.resolve(Locale.CANADA, "test", new CompositeMessageDecorator(Arrays.<MessageDecorator>asList(new ReplacementMessageDecorator(asList(new ReplacementMessageDecorator.Replacement("%blah%", "two"))))));

        assertThat(result.get(), is("one two"));
    }
}
