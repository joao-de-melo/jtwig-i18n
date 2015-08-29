package org.jtwig.i18n.source.message;

import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MapMessageSourceTest {
    private final HashMap<String, String> stringStringHashMap = new HashMap<String, String>();
    private MapMessageSource underTest = new MapMessageSource(stringStringHashMap);

    @Before
    public void setUp() throws Exception {
        stringStringHashMap.clear();
    }

    @Test
    public void messageWhenNotFound() throws Exception {

        Optional<String> result = underTest.message("test");

        assertThat(result.isPresent(), is(false));
    }

    @Test
    public void messageWhenFound() throws Exception {
        String expected = "blah";
        stringStringHashMap.put("test", expected);

        Optional<String> result = underTest.message("test");

        assertThat(result.isPresent(), is(true));
        assertThat(result.get(), is(expected));
    }
}