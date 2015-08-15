package org.jtwig.i18n.source.message;

import com.google.common.base.Optional;

import java.util.Map;

public class MapMessageSource implements MessageSource {
    private final Map<String, String> map;

    public MapMessageSource(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public Optional<String> message(String message) {
        return Optional.fromNullable(map.get(message));
    }
}
