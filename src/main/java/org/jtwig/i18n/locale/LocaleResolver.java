package org.jtwig.i18n.locale;

import java.util.Locale;

public interface LocaleResolver {
    Locale resolve (String localeIdentifier);
}
