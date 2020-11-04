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

import ch.frankel.kaadin.item
import ch.frankel.kaadin.itemAfter
import ch.frankel.kaadin.itemAt
import ch.frankel.kaadin.property
import com.vaadin.data.util.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.*

class ItemTest {

    private lateinit var container: IndexedContainer

    @BeforeMethod
    private fun setUp() {
        container = IndexedContainer()
    }

    @Test
    fun `container can add item`() {
        val id = container.item()
        assertThat(container.size()).isEqualTo(1)
        assertThat(id).isNotNull
    }

    @Test
    fun `container can add multiple items`() {
        with(container) {
            repeat(10) {
                item()
            }
        }
        assertThat(container.size()).isEqualTo(10)
    }

    @Test
    fun `container can add multiple items with id via varargs`() {
        val ids = IntRange(0, 9).map { it }.toTypedArray()
        container.item(*ids)
        assertThat(container.size()).isEqualTo(10)
        assertThat(container.getIdByIndex(4)).isEqualTo(4)
    }

    @Test
    fun `container can add multiple items with id via collection`() {
        val ids = IntRange(1, 5).map { it }
        container.item(ids)
        assertThat(container.size()).isEqualTo(5)
        assertThat(container.getIdByIndex(3)).isEqualTo(4)
    }

    @Test
    fun `item can be added after another item`() {
        container.item("One")
        container.itemAfter("One", "Two")
        assertThat(container.size()).isEqualTo(2)
        assertThat(container.getIdByIndex(0)).isEqualTo("One")
        assertThat(container.getIdByIndex(1)).isEqualTo("Two")
    }

    @Test
    fun `item can be added at specific index`() {
        container.item("One")
        container.item("Three")
        container.itemAt(1, "Two")
        assertThat(container.size()).isEqualTo(3)
        assertThat(container.getIdByIndex(0)).isEqualTo("One")
        assertThat(container.getIdByIndex(1)).isEqualTo("Two")
        assertThat(container.getIdByIndex(2)).isEqualTo("Three")
    }

    @Test
    fun `property can be added to the container`() {
        val propertyId = "dummy"
        val defaultValue = "foo"
        container.property(propertyId, String::class.java, defaultValue)
        val properties = container.containerPropertyIds
        assertThat(properties).hasSize(1).element(0).isEqualTo(propertyId)
        container.item()
        val idByIndex = container.getIdByIndex(0)
        val property = container.getContainerProperty(idByIndex, propertyId)
        assertThat(property).isNotNull
        assertThat(property.type).isEqualTo(String::class.java)
        assertThat(property.value).isEqualTo(defaultValue)
    }
}