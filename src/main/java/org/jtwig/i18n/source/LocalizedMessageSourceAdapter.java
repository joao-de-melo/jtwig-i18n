package org.jtwig.i18n.source;

import com.google.common.base.Optional;
import org.jtwig.i18n.source.message.MessageSource;

import java.util.Locale;
import java.util.Map;

public class LocalizedMessageSourceAdapter implements LocalizedMessageSource {
    private final Map<Locale, MessageSource> messageSourceMap;

    public LocalizedMessageSourceAdapter(Map<Locale, MessageSource> messageSourceMap) {
        this.messageSourceMap = messageSourceMap;
    }

    @Override
    public Optional<String> message(Locale locale, String original) {
        MessageSource messageSource = messageSourceMap.get(locale);
        if (messageSource != null) {
            return messageSource.message(original);
        } else {
            return Optional.absent();
        }
    }
}
