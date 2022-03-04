package mathtools.generators.counters.ints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/** Testing the [IntCounter32000]
 * @author DK96-OS : 2022 */
public class IntCounter32000Test {

    private IntCounter32000 mCounter;

    @AfterEach
    void testCleanup() { mCounter = null; }

    @Test
    void test0To10() {
        mCounter = new IntCounter32000(0, 10);
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 10; j++)
                assertTrue(mCounter.count(i));
        }
        short[] result = mCounter.getValueArray();
        for (int i = 0; i < 11; i++) {
            assertEquals(10, result[i]);
        }
    }

    @Test
    void testConstructorInvalidArgs() {
        assertThrows(IllegalArgumentException.class, () -> {
            mCounter = new IntCounter32000(20, 19);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            mCounter = new IntCounter32000(20, 20);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            mCounter = new IntCounter32000(Integer.MIN_VALUE, 1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            mCounter = new IntCounter32000(Integer.MIN_VALUE, Integer.MAX_VALUE);
        });
    }

    @Test
    void testCountByThousands() {
        mCounter = new IntCounter32000(1, 10);
        final short thousands = 2000;
        // 32000 / 2000 = 16
        for (int i = 0; i < 16; i++) assertTrue(
                mCounter.countBy(1, thousands)
        );
        assertEquals(
                (short) 32000, mCounter.getCountOf(1));
        assertFalse(
                mCounter.countBy(1, thousands));
    }

    @Test
    void testCountByInvalidArgs() {
        mCounter = new IntCounter32000(1, 10);
        assertFalse(
                mCounter.countBy(0, (short) 1));
        assertFalse(
                mCounter.countBy(1, (short) 0));
        assertFalse(
                mCounter.countBy(1, (short) -2));
    }

    @Test
    void testNegativeRangeCount() {
        mCounter = new IntCounter32000(-10, -1);
        assertTrue(
                mCounter.count(-10));
        assertTrue(
                mCounter.count(-1));
        assertFalse(
                mCounter.count(-11));
        assertFalse(
                mCounter.count(0));
        // Check Counts
        assertEquals(
                (short) 1, mCounter.getCountOf(-10));
        assertEquals(
                (short) 1, mCounter.getCountOf(-1));
        assertNull(
                mCounter.getCountOf(-11));
        assertNull(
                mCounter.getCountOf(0));
    }

    @Test
    void testNegativeRangeCountBy() {
        mCounter = new IntCounter32000(-10, -1);
        assertTrue(
                mCounter.countBy(-10, (short) 200));
        assertTrue(
                mCounter.countBy(-1, (short) 200));
        assertFalse(
                mCounter.countBy(-11, (short) 200));
        assertFalse(
                mCounter.countBy(0, (short) 200));

    }

    @Test
    void testGetCountOfInvalidArgs() {
        mCounter = new IntCounter32000(1, 10);
        assertNull(
                mCounter.getCountOf(0));
        assertNull(
                mCounter.getCountOf(20));
    }

}
