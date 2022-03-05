package mathtools.numbers.primes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static mathtools.numbers.primes.PrimeFactoring.firstPrimeAbove;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

/** Testing [PrimeFactoring] functions
 * @author DK96-OS : 2022 */
public final class PrimeFactoringTest {

    @ParameterizedTest
    @ArgumentsSource(PrimeCacheArgumentProvider.class)
    void testFirstPrimeAbove(
            final PrimeCacheBase cache
    ) {
        assertEquals(
                7, firstPrimeAbove(49, 6, cache));
        assertNull(
                firstPrimeAbove(49, 7, cache));
        //
        assertEquals(
                5, firstPrimeAbove(5000, 4, cache));
        assertEquals(
                5, firstPrimeAbove(5000, 4, cache));
        //
        assertNull(
                firstPrimeAbove(5000, 71, cache));
        assertNull(
                firstPrimeAbove(5000, 5, cache));
    }

    @ParameterizedTest
    @ArgumentsSource(PrimeCacheArgumentProvider.class)
    void testFirstPrimeAboveNegativeArgs(
            final PrimeCacheBase cache
    ) {
        // Check negative inputs
        assertEquals(
                5, firstPrimeAbove(-5000, 4, cache));
        assertNull(
                firstPrimeAbove(-5000, 71, cache));
        // Null when limit is negative
        assertNull(
                firstPrimeAbove(5000, -4, cache));
        assertNull(
                firstPrimeAbove(-49, -6, cache));
    }

}
