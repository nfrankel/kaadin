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
package ch.frankel.kaadin.container

import ch.frankel.kaadin.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.*

@Test(groups = arrayOf("bean"), dependsOnGroups = arrayOf("viewer"))
class BeanTest {

    private lateinit var comboBox: ComboBox

    @BeforeMethod
    private fun setUp() {
        comboBox = ComboBox()
    }

    @Test
    fun `one initialized bean can be added to a container`() {
        val value = "One"
        comboBox.beanItemContainer(String::class.java) {
            bean(value)
        }
        val itemIds = comboBox.containerDataSource.itemIds
        assertThat(itemIds.size).isEqualTo(1)
        assertThat(itemIds.first()).isEqualTo(value)
    }

    @Test
    fun `many initialized bean can individually be added to a container`() {
        comboBox.beanItemContainer(String::class.java) {
            IntRange(0,9).map {
                bean(it.toString())
            }
        }
        val itemIds = comboBox.containerDataSource.itemIds
        assertThat(itemIds.size).isEqualTo(10)
        assertThat(itemIds.first()).isEqualTo("0")
    }

    @Test
    fun `many initialized bean can be added to a container as a collection`() {
        val values = IntRange(0,9).map { it.toString() }
        comboBox.beanItemContainer(String::class.java) {
            beans(values)
        }
        val itemIds = comboBox.containerDataSource.itemIds
        assertThat(itemIds.size).isEqualTo(10)
        assertThat(itemIds.first()).isEqualTo("0")
    }

    @Test
    fun `many initialized bean can be added to a container as varargs`() {
        val values = IntRange(0,9).map { it.toString() }.toTypedArray()
        comboBox.beanItemContainer(String::class.java) {
            bean(*values)
        }
        val itemIds = comboBox.containerDataSource.itemIds
        assertThat(itemIds.size).isEqualTo(10)
        assertThat(itemIds.first()).isEqualTo("0")
    }
}