package org.jtwig.i18n.source.message;

import com.google.common.base.Optional;
import com.google.common.cache.Cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class CachedMessageSource implements MessageSource {
    private final Cache<String, Optional<String>> cache;
    private final MessageSource messageSource;

    public CachedMessageSource(Cache<String, Optional<String>> cache, MessageSource messageSource) {
        this.cache = cache;
        this.messageSource = messageSource;
    }

    @Override
    public Optional<String> message(final String message) {
        try {
            return cache.get(message, new Callable<Optional<String>>() {
                @Override
                public Optional<String> call() throws Exception {
                    return messageSource.message(message);
                }
            });
        } catch (ExecutionException e) {
            return Optional.absent();
        }
    }
}
