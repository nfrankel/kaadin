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

import ch.frankel.kaadin.*
import com.vaadin.data.util.converter.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import java.util.*

@Test(dependsOnGroups = arrayOf("baseGrid"))
class GridConverterTest {

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun `column should throw exception if converter type mismatch`() {
        horizontalLayout {
            grid(dataSource = GridTestData.container) {
                column("string").intConverter()
            }
        }
    }

    @Test
    fun `column should add integer converter`() {
        val layout = horizontalLayout {
            grid(dataSource = GridTestData.container) {
                column("int").intConverter()
            }
        }
        val grid = layout.getGrid()
        assertThat(grid.getIntColumn().converter)
                .isNotNull()
                .isInstanceOf(StringToIntegerConverter::class.java)
    }

    @Test
    fun `column should add date converter`() {
        val layout = horizontalLayout {
            grid(dataSource = GridTestData.container) {
                column("date").dateConverter()
            }
        }
        val grid = layout.getGrid()
        assertThat(grid.getDateColumn().converter)
                .isNotNull()
                .isInstanceOf(StringToDateConverter::class.java)
    }

    @Test
    fun `column should add custom converter`() {
        val layout = horizontalLayout {
            grid(dataSource = GridTestData.container) {
                column("date").converter(Date::class.java, String::class.java) { date -> "foo" }
            }
        }
        val grid = layout.getGrid()
        assertThat(grid.getDateColumn().converter).isNotNull()
    }

    @Test
    fun `column should remove converter`() {
        val layout = horizontalLayout {
            grid(dataSource = GridTestData.container) {
                column("string") {
                    converter(String::class.java, String::class.java) { string -> string }
                    unsetConverter()
                }
            }
        }
        val grid = layout.getGrid()
        assertThat(grid.getStringColumn().converter).isNull()
    }
}