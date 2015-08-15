package org.jtwig.i18n;

import org.jtwig.i18n.source.LocalizedMessageSource;

import java.util.Collection;

public class MessageResolverConfiguration {
    private final Collection<LocalizedMessageSource> localizedMessageSources;

    public MessageResolverConfiguration(Collection<LocalizedMessageSource> localizedMessageSources) {
        this.localizedMessageSources = localizedMessageSources;
    }

    public Collection<LocalizedMessageSource> getLocalizedMessageSources() {
        return localizedMessageSources;
    }
}
