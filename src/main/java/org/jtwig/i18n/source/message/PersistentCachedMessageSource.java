package org.jtwig.i18n.source.message;

import com.google.common.base.Optional;

import java.util.HashMap;
import java.util.Map;

public class PersistentCachedMessageSource implements MessageSource {
    private final Map<String, String> map = new HashMap<String, String>();
    private final MessageSource messageSource;

    public PersistentCachedMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public Optional<String> message(String message) {
        if (!map.containsKey(message)) {
            map.put(message, messageSource.message(message).orNull());
        }
        return Optional.fromNullable(map.get(message));
    }
}
