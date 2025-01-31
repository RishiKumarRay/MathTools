package mathtools.numbers.format

import mathtools.numbers.format.StringPackager.packBytes
import mathtools.numbers.format.StringPackager.packShort
import mathtools.numbers.format.StringPackager.packUShort
import mathtools.numbers.format.StringPackager.unpackByte0
import mathtools.numbers.format.StringPackager.unpackByte1
import mathtools.numbers.format.StringPackager.unpackBytes
import mathtools.numbers.format.StringPackager.unpackShort
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing StringPackager functions of Integers
 * @author DK96-OS : 2022 */
class StringPackagerIntTest {

	@Test
	fun testBytes() {
		for (b0Int in 0 until 255) {
			val b0 = b0Int.toByte()
			for (b1Int in 0 until 255) {
				val b1 = b1Int.toByte()
				val packed = packBytes(b0, b1)
				//
				val unpackedB0 = unpackByte0(packed)
				val unpackedB1 = unpackByte1(packed)
				val unpackedPair = unpackBytes(packed)
				//
				assertEquals(b0, unpackedB0)
				assertEquals(b1, unpackedB1)
				// Check pair function
				assertEquals(b0, unpackedPair.first)
				assertEquals(b1, unpackedPair.second)
			}
		}
	}

	@Test
	fun testShorts() {
		for (sInt in 0 until 2 * Short.MAX_VALUE + 1) {
			val s = sInt.toShort()
			val uShort = sInt.toUShort()
			val packed = packShort(s)
			val uPacked = packUShort(uShort)
			// Both versions should be the same
			assertEquals(packed.code, uPacked.code)
			//
			assertEquals(s, unpackShort(packed))
			assertEquals(uShort, unpackShort(uPacked).toUShort())
		}
	}

}