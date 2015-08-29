package org.jtwig.i18n.source.factory;

import org.jtwig.i18n.source.message.MapMessageSource;
import org.jtwig.i18n.source.message.MessageSource;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class YmlMessageSourceFactory {
    private static final Yaml yaml = new Yaml();

    public static MessageSource create (InputStream inputStream) {
        Object load = yaml.load(inputStream);
        if (load instanceof Map) {
            Map loaded = (Map) load;
            for (Object value : loaded.values()) {
                if (!(value instanceof String)) {
                    throw new IllegalArgumentException("Yaml file provided is not a list of key pair values");
                }
            }
            return new MapMessageSource(loaded);
        }
        throw new IllegalArgumentException("Yaml file provided is not a list of key pair values");
    }
}
