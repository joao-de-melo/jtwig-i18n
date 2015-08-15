package org.jtwig.i18n.source.factory;

import com.lyncode.xliff.XLIFF;
import com.lyncode.xliff.XLiffUtils;
import com.lyncode.xliff.XliffException;
import org.jtwig.i18n.source.message.MapMessageSource;
import org.jtwig.i18n.source.message.MessageSource;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class XliffMessageSourceFactory {
    public static MessageSource create (InputStream inputStream) throws XliffException {
        XLIFF file = XLiffUtils.read(inputStream);
        Map<String, String> map = new HashMap<String, String>();
        for (String original : file.getSources()) {
            map.put(original, file.getTarget(original));
        }

        return new MapMessageSource(map);
    }
}
