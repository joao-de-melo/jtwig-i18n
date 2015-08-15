package org.jtwig.i18n;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import org.jtwig.i18n.decorate.MessageDecorator;
import org.jtwig.i18n.source.CompositeLocalizedMessageSource;
import org.jtwig.i18n.source.LocalizedMessageSource;

import java.util.Locale;

public class MessageResolverFactory {
    public MessageResolver create (MessageResolverConfiguration configuration) {
        return new MessageResolverImpl(new CompositeLocalizedMessageSource(configuration.getLocalizedMessageSources()));
    }

    private class MessageResolverImpl implements MessageResolver {
        private final LocalizedMessageSource messageSource;

        public MessageResolverImpl(LocalizedMessageSource messageSource) {
            this.messageSource = messageSource;
        }

        @Override
        public Optional<String> resolve(Locale locale, String message, final MessageDecorator decorator) {
            return messageSource.message(locale, message).transform(new Function<String, String>() {
                @Override
                public String apply(String input) {
                    return decorator.decorate(input);
                }
            });
        }
    }
}
