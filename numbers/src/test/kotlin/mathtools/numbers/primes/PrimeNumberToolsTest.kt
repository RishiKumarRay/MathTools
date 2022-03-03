package mathtools.numbers.primes

import mathtools.numbers.primes.PrimeNumberTools.checkForPrimeFactorAboveLimit
import mathtools.numbers.primes.PrimeNumberTools.divideOutSmallPrimes
import mathtools.numbers.primes.PrimeNumberTools.getFirstPrimeAboveLimit
import mathtools.numbers.primes.PrimeNumberTools.reduceByPrimeRange
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/** Testing the Prime Number tools object */
class PrimeNumberToolsTest {

	/** A cache is required by all function calls */
	private lateinit var cache: ShortPrimeCache

	@BeforeEach
	fun testSetup() {
		cache = ShortPrimeCache()
	}

    @Test
    fun testPrimeFactorLimitCheck() {
		assertFalse(
			checkForPrimeFactorAboveLimit(40, 11, cache))
    	assertFalse(
			checkForPrimeFactorAboveLimit(39, 13, cache))
		//
    	assertTrue(
			checkForPrimeFactorAboveLimit(34, 11, cache))
    	assertTrue(
			checkForPrimeFactorAboveLimit(39, 11, cache))
    }

	@Test
	fun testFirstPrimeAboveLimit() {
		assertNull(
			getFirstPrimeAboveLimit(5000, 71, cache))
		assertNull(
			getFirstPrimeAboveLimit(5000, 5, cache))
		assertEquals(
			5,
			getFirstPrimeAboveLimit(5000, 4, cache))
		assertEquals(
			5,
			getFirstPrimeAboveLimit(5000, 4, cache))
		assertEquals(
			7,
			getFirstPrimeAboveLimit(49, 6, cache))
		assertNull(
			getFirstPrimeAboveLimit(49, 7, cache))
	}

	@Test
	fun testFirstPrimeAboveLimitNegative() {
		// Ignore negative sign on product
		assertNull(
			getFirstPrimeAboveLimit(-5000, 71, cache))
		assertEquals(
			5,
			getFirstPrimeAboveLimit(-5000, 4, cache))
		assertEquals(
			7,
			getFirstPrimeAboveLimit(-49, 6, cache))
		// Null when limit is negative
		assertNull(
			getFirstPrimeAboveLimit(-5000, -4, cache))
		assertNull(
			getFirstPrimeAboveLimit(-49, -6, cache))
	}

	@Test
	fun testFirstPrimeAboveLimitInvalid() {
		// Null when product is 1 or zero
		assertNull(
			getFirstPrimeAboveLimit(-1, 5, cache))
		assertNull(
			getFirstPrimeAboveLimit(0, 5, cache))
		assertNull(
			getFirstPrimeAboveLimit(1, 5, cache))
	}

	@Test
	fun testReduceByPrimes() {
		assertEquals(
			2, reduceByPrimeRange(1..2, 30, cache))
		assertEquals(
			4, reduceByPrimeRange(1..5, 60, cache))
		assertEquals(
			12, reduceByPrimeRange(2..2, 60, cache))
	}

	@Test
	fun testReduceByPrimesNegative() {
		assertEquals(
			-2, reduceByPrimeRange(1..2, -30, cache))
		assertEquals(
			-4, reduceByPrimeRange(1..2, -60, cache))
		assertEquals(
			-12, reduceByPrimeRange(2..3, -60, cache))
	}

	@Test
	fun testReduceByPrimesIndivisibleProduct() {
		assertNull(
			reduceByPrimeRange(1..3, -1, cache))
		assertNull(
			reduceByPrimeRange(1..3, 0, cache))
		assertNull(
			reduceByPrimeRange(1..3, 1, cache))
	}

	@Test
	fun testReduceByPrimesInvalidRange() {
		// Negative valued ranges are ignored
		assertNull(
			reduceByPrimeRange(-3..-1, -30, cache))
		// The invalid region of the range is ignored
		assertNull(
			reduceByPrimeRange(-3..3, -60, cache))
		// Invalid region is ignored
		assertEquals(
			-15, reduceByPrimeRange(-1..0, -60, cache))
	}

	@Test
	fun testReduceByPrimesReversedRange() {
		assertEquals(
			4, reduceByPrimeRange(2..1, 60, cache))
		assertEquals(
			-4, reduceByPrimeRange(2..1, -60, cache))
	}

	@Test
	fun testDivideOutSmallPrimes() {
		assertEquals(
			25, divideOutSmallPrimes(100, 2, cache))
		assertEquals(
			25, divideOutSmallPrimes(100, 3, cache))
		assertEquals(
			25, divideOutSmallPrimes(100, 4, cache))
		assertNull(
			divideOutSmallPrimes(100, 5, cache))
		//
		assertEquals(
			11, divideOutSmallPrimes(77, 7, cache))
		assertEquals(
			11, divideOutSmallPrimes(77, 9, cache))
		assertEquals(
			17, divideOutSmallPrimes(68, 13, cache))
		//
		assertNull(
			divideOutSmallPrimes(50, 43, cache))
	}

	@Test
	fun testDivideOutSmallPrimesNegative() {
		assertNull(divideOutSmallPrimes(-50, 43, cache))
		assertNull(divideOutSmallPrimes(-100, 5, cache))
	}

}