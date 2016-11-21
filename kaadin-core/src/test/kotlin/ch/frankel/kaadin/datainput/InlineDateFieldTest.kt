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
package ch.frankel.kaadin.datainput

import ch.frankel.kaadin.*
import com.vaadin.data.util.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import java.util.*

class InlineDateFieldTest {

    @Test
    fun `inline date field should be added to layout`() {
        val layout = horizontalLayout {
            inlineDateField()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(InlineDateField::class.java)
    }

    @Test(dependsOnMethods = arrayOf("inline date field should be added to layout"))
    fun `inline date field value can be initialized`() {
        val date = Date()
        val layout = horizontalLayout {
            inlineDateField(value = date)
        }
        val component = layout.getComponent(0) as InlineDateField
        assertThat(component).hasFieldOrPropertyWithValue("value", date)
    }

    @Test(dependsOnMethods = arrayOf("inline date field should be added to layout"))
    fun `inline date field value can be initialized via property`() {
        val date = Date()
        val property = ObjectProperty(date)
        val layout = horizontalLayout {
            inlineDateField(dataSource = property)
        }
        val component = layout.getComponent(0) as InlineDateField
        assertThat(component).hasFieldOrPropertyWithValue("value", date)
    }

    @Test(dependsOnMethods = arrayOf("inline date field should be added to layout"))
    fun `inline date field should be configurable`() {
        val date = Date()
        val layout = horizontalLayout {
            inlineDateField {
                value = date
            }
        }
        val component = layout.getComponent(0) as InlineDateField
        assertThat(component).hasFieldOrPropertyWithValue("value", date)
    }
}