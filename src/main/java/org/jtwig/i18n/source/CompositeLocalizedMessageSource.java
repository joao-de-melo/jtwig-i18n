package org.jtwig.i18n.source;

import com.google.common.base.Optional;

import java.util.Collection;
import java.util.Locale;

public class CompositeLocalizedMessageSource implements LocalizedMessageSource {
    private final Collection<LocalizedMessageSource> messageSource;

    public CompositeLocalizedMessageSource(Collection<LocalizedMessageSource> messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public Optional<String> message(Locale locale, String original) {
        for (LocalizedMessageSource localizedMessageSource : messageSource) {
            Optional<String> message = localizedMessageSource.message(locale, original);
            if (message.isPresent()) {
                return message;
            }
        }
        return Optional.absent();
    }
}
