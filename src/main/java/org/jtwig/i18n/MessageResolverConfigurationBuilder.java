package org.jtwig.i18n;

import org.apache.commons.lang3.builder.Builder;
import org.jtwig.i18n.source.LocalizedMessageSource;
import org.jtwig.i18n.source.LocalizedMessageSourceAdapter;
import org.jtwig.i18n.source.message.CompositeMessageSource;
import org.jtwig.i18n.source.message.MessageSource;

import java.util.*;

public class MessageResolverConfigurationBuilder<B extends MessageResolverConfigurationBuilder> implements Builder<MessageResolverConfiguration> {
    private final Collection<LocalizedMessageSource> localizedMessageSources = new ArrayList<LocalizedMessageSource>();
    private final Map<Locale, Collection<MessageSource>> messageSources = new HashMap<Locale, Collection<MessageSource>>();

    public MessageResolverConfigurationBuilder () {}
    public MessageResolverConfigurationBuilder (MessageResolverConfiguration prototype) {
        localizedMessageSources.addAll(prototype.getLocalizedMessageSources());
    }

    public B withMessageSources (Collection<LocalizedMessageSource> messageSource) {
        localizedMessageSources.addAll(messageSource);
        return self();
    }

    public B withMessageSource (LocalizedMessageSource messageSource) {
        localizedMessageSources.add(messageSource);
        return self();
    }

    public B withMessageSource (Locale locale, MessageSource messageSource) {
        if (!messageSources.containsKey(locale)) {
            messageSources.put(locale, new ArrayList<MessageSource>());
        }
        messageSources.get(locale).add(messageSource);
        return self();
    }

    public B withMessageSources (Locale locale, Collection<MessageSource> messageSource) {
        if (!messageSources.containsKey(locale)) {
            messageSources.put(locale, new ArrayList<MessageSource>());
        }
        messageSources.get(locale).addAll(messageSource);
        return self();
    }

    private B self() {
        return (B) this;
    }

    @Override
    public MessageResolverConfiguration build() {
        ArrayList<LocalizedMessageSource> sources = new ArrayList<LocalizedMessageSource>(localizedMessageSources);
        if (!messageSources.isEmpty()) {
            Map<Locale, MessageSource> messageSourceMap = new HashMap<Locale, MessageSource>();
            for (Map.Entry<Locale, Collection<MessageSource>> entry : messageSources.entrySet()) {
                messageSourceMap.put(entry.getKey(), new CompositeMessageSource(entry.getValue()));
            }
            sources.add(new LocalizedMessageSourceAdapter(messageSourceMap));
        }
        return new MessageResolverConfiguration(sources);
    }
}
