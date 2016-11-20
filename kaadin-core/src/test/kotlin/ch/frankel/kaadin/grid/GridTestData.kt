package ch.frankel.kaadin.grid

import com.vaadin.data.util.*
import com.vaadin.ui.*
import java.util.*


internal fun HorizontalLayout.getGrid() = getComponent(0) as Grid
internal fun Grid.getStringColumn() = getColumn("string")
internal fun Grid.getDateColumn() = getColumn("date")
internal fun Grid.getIntColumn() = getColumn("int")
internal fun Grid.getNestedColumn() = getColumn("nested")

internal class DataRow(val string: String, val date: Date, val int: Int, val nested: NestedData? = null)
internal class NestedData(val string: String, val date: Date, val int: Int)

class GridTestData {
    companion object {
        internal val data = arrayListOf(
                DataRow("AAA", Date(), 1),
                DataRow("BBB", Date(), 2),
                DataRow("CCC", Date(), 3),
                DataRow("DDD", Date(), 4),
                DataRow("EEE", Date(), 5))
        internal val container = BeanItemContainer(DataRow::class.java, data)
    }
}