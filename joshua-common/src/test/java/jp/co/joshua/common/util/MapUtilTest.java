package jp.co.joshua.common.util;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * {@linkplain MapUtil}のjUnit
 *
 * @version 1.0.0
 */
public class MapUtilTest {

    @Test
    public void testIsEmpty() {
        {
            Map<String, String> map = new HashMap<>();
            assertTrue(MapUtil.isEmpty(map));
        }
        {
            Map<String, String> map = null;
            assertTrue(MapUtil.isEmpty(map));
        }
        {
            Map<String, String> map = new HashMap<>();
            map.put("", "");
            assertFalse(MapUtil.isEmpty(map));
        }
    }

    @Test
    public void testAddOrReplace() {
        {
            Map<String, BigDecimal> map = new HashMap<>();
            MapUtil.addOrReplace(map, "1", BigDecimal.valueOf(10));
            assertEquals(BigDecimal.valueOf(10), map.get("1"));
        }
        {
            Map<String, BigDecimal> map = new HashMap<>();
            MapUtil.addOrReplace(map, "1", BigDecimal.valueOf(10));
            MapUtil.addOrReplace(map, "1", BigDecimal.valueOf(11));
            assertEquals(BigDecimal.valueOf(21), map.get("1"));
        }
        {
            Map<String, BigDecimal> map = new HashMap<>();
            MapUtil.addOrReplace(map, "1", BigDecimal.valueOf(10));
            MapUtil.addOrReplace(map, "2", BigDecimal.valueOf(11));
            assertEquals(BigDecimal.valueOf(10), map.get("1"));
        }
    }

}
