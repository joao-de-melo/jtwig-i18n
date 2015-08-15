package org.jtwig.i18n;

import com.google.common.base.Optional;
import org.jtwig.i18n.decorate.MessageDecorator;

import java.util.Locale;

public interface MessageResolver {
    public Optional<String> resolve(Locale locale, String message, MessageDecorator decorator);
}
