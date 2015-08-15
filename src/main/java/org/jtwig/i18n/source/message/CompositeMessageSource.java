package org.jtwig.i18n.source.message;

import com.google.common.base.Optional;

import java.util.Collection;

public class CompositeMessageSource implements MessageSource {
    private final Collection<MessageSource> messageSourceCollection;

    public CompositeMessageSource(Collection<MessageSource> messageSourceCollection) {
        this.messageSourceCollection = messageSourceCollection;
    }

    @Override
    public Optional<String> message(String message) {
        for (MessageSource messageSource : messageSourceCollection) {
            Optional<String> translate = messageSource.message(message);
            if (translate.isPresent()) {
                return translate;
            }
        }
        return Optional.absent();
    }
}
