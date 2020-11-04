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

class TwinColSelectTest {

    @Test
    fun `twin col select should be added to layout`() {
        val layout = horizontalLayout {
            twinColSelect()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull.isInstanceOf(TwinColSelect::class.java)
    }

    @Test(dependsOnMethods = ["twin col select should be added to layout"])
    fun `twin col select elements can be initialized via varargs`() {
        val layout = horizontalLayout {
            twinColSelect("caption", "One", "Two", "Three")
        }
        val component = layout.getComponent(0) as TwinColSelect
        assertThat(component.size()).isEqualTo(3)
    }

    @Test(dependsOnMethods = ["twin col select should be added to layout"])
    fun `twin col select elements can be initialized via collection`() {
        val layout = horizontalLayout {
            twinColSelect(options = arrayListOf("One", "Two", "Three"))
        }
        val component = layout.getComponent(0) as TwinColSelect
        assertThat(component.size()).isEqualTo(3)
    }

    @Test(dependsOnMethods = ["twin col select should be added to layout"])
    fun `twin col select elements can be initialized via property`() {
        val container = BeanItemContainer(String::class.java).apply {
            addAll(arrayListOf("One", "Two", "Three"))
        }
        val layout = horizontalLayout {
            twinColSelect(dataSource = container)
        }
        val component = layout.getComponent(0) as TwinColSelect
        assertThat(component.size()).isEqualTo(3)
    }

    @Test(dependsOnMethods = ["twin col select should be added to layout"])
    fun `twin col select caption can be initialized`() {
        val caption = "caption"
        val layout = horizontalLayout {
            twinColSelect(caption)
        }
        val component = layout.getComponent(0) as TwinColSelect
        assertThat(component.caption).isEqualTo(caption)
    }

    @Test(dependsOnMethods = ["twin col select should be added to layout"])
    fun `twin col select should be configurable`() {
        val id = "dummy"
        val layout = horizontalLayout {
            twinColSelect {
                this.id = id
            }
        }
        val component = layout.getComponent(0) as TwinColSelect
        assertThat(component.id).isEqualTo(id)
    }
}