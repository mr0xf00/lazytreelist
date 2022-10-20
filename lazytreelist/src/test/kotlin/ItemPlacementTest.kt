import com.mr0xf00.lazytreelist.ItemPlacement
import org.junit.Assert
import org.junit.Test

internal class ItemPlacementTest {
    private val cases = arrayOf(
        booleanArrayOf(false, false, false), booleanArrayOf(true, false, false), booleanArrayOf(false, true, false), booleanArrayOf(true, true, false),
        booleanArrayOf(false, false, true), booleanArrayOf(true, false, true), booleanArrayOf(false, true, true), booleanArrayOf(true, true, true)
    )
    @Test
    fun `isFirst-Middle-Last are correct`() {
        for((first, middle, last) in cases) {
            val placement = ItemPlacement(first,middle,last)
            Assert.assertEquals(placement.isFirst, first)
            Assert.assertEquals(placement.isMiddle, middle)
            Assert.assertEquals(placement.isLast, last)
        }
    }
}