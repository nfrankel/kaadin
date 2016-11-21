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

class PasswordFieldTest {

    @Test
    fun `password should be added to layout`() {
        val layout = horizontalLayout {
            passwordField()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(PasswordField::class.java)
    }

    @Test(dependsOnMethods = arrayOf("password should be added to layout"))
    fun `password value can be initialized`() {
        val password = "Hello world"
        val layout = horizontalLayout {
            passwordField(value = password)
        }
        val component = layout.getComponent(0) as PasswordField
        assertThat(component).hasFieldOrPropertyWithValue("value", password)
    }

    @Test(dependsOnMethods = arrayOf("password should be added to layout"))
    fun `password value can be initialized via property`() {
        val password = "Hello world"
        val property = ObjectProperty(password)
        val layout = horizontalLayout {
            passwordField(dataSource = property)
        }
        val component = layout.getComponent(0) as PasswordField
        assertThat(component).hasFieldOrPropertyWithValue("value", password)
    }

    @Test(dependsOnMethods = arrayOf("password should be added to layout"))
    fun `password should be configurable`() {
        val password = "Hello world"
        val layout = horizontalLayout {
            passwordField {
                value = password
            }
        }
        val component = layout.getComponent(0) as PasswordField
        assertThat(component).hasFieldOrPropertyWithValue("value", password)
    }
}