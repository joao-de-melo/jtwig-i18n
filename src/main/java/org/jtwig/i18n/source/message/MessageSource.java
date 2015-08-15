package org.jtwig.i18n.source.message;

import com.google.common.base.Optional;

public interface MessageSource {
    Optional<String> message(String message);
}
