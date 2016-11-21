/*
 * Copyright 2016 Nicolas Fränkel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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