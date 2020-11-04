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
import com.vaadin.data.util.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.*

@Test(groups = ["viewer"])
class ViewerTest {

    private lateinit var comboBox: ComboBox

    @BeforeMethod
    private fun setUp() {
        comboBox = ComboBox()
    }

    @Test
    fun `bean item container can be added to viewer`() {
        comboBox.beanItemContainer(String::class.java)
        assertThat(comboBox.containerDataSource).isNotNull.isInstanceOf(BeanItemContainer::class.java)
    }

    @Test(dependsOnMethods = ["bean item container can be added to viewer"])
    fun `bean item container can be initialized with collection`() {
        val items = arrayListOf("One", "Two", "Three")
        comboBox.beanItemContainer(String::class.java, items)
        assertThat(comboBox.containerDataSource).isNotNull.isInstanceOf(BeanItemContainer::class.java)
        assertThat(comboBox.size()).isEqualTo(items.size)
    }

    @Test(dependsOnMethods = ["bean item container can be added to viewer"])
    fun `bean item container can be initialized with varargs`() {
        val items = arrayOf("One", "Two", "Three")
        comboBox.beanItemContainer(String::class.java, * items)
        assertThat(comboBox.containerDataSource).isNotNull.isInstanceOf(BeanItemContainer::class.java)
        assertThat(comboBox.size()).isEqualTo(items.size)
    }
}