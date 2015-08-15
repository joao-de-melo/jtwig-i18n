package org.jtwig.i18n.locale;

import java.util.Locale;

public class JavaLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolve(String localeIdentifier) {
        return Locale.forLanguageTag(localeIdentifier);
    }
}
