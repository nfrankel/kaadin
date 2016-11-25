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
import com.vaadin.data.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.*

@Test(dependsOnGroups = arrayOf("bean"))
class FilterTest {

    private lateinit var comboBox: ComboBox

    @BeforeMethod
    private fun setUp() {
        comboBox = ComboBox()
    }

    @Test
    fun `container can be filtered with simple filter`() {
        val persons = arrayOf("John", "Jack", "William", "Averell").map(::Person)
        comboBox.beanItemContainer(Person::class.java) {
            beans(persons)
            filter("name","J",false, false)
        }
        val itemIds = comboBox.visibleItemIds
        assertThat(itemIds.size).isEqualTo(2)
        assertThat(itemIds.first()).isEqualTo(Person("John"))
    }

    @Test
    fun `container can be filtered with complex filter`() {
        val persons = arrayOf("John", "Jack", "William", "Averell").map(::Person)
        val filter = object : Container.Filter {
            override fun passesFilter(itemId: Any, item: Item): Boolean {
                val person = itemId as Person
                return person.name.contains("a", false)
            }
            override fun appliesToProperty(propertyId: Any) = true
        }
        comboBox.beanItemContainer(Person::class.java) {
            beans(persons)
            filter(filter)
        }
        val itemIds = comboBox.visibleItemIds
        assertThat(itemIds.size).isEqualTo(2)
        assertThat(itemIds.first()).isEqualTo(Person("Jack"))
    }

}

internal data class Person(val name: String)