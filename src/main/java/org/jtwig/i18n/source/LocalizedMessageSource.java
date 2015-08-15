package org.jtwig.i18n.source;

import com.google.common.base.Optional;

import java.util.Locale;

public interface LocalizedMessageSource {
    Optional<String> message (Locale locale, String original);
}
