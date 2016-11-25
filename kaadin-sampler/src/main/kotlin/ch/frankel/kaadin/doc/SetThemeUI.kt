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
package ch.frankel.kaadin.doc

// tag::setthemeui[]
import ch.frankel.kaadin.horizontalLayout
import ch.frankel.kaadin.optionGroup
import com.vaadin.data.*
import com.vaadin.server.*
import com.vaadin.ui.*

val changeTheme: (Property.ValueChangeEvent) -> Unit = { //<1>
    ch.frankel.kaadin.theme(it.property.value.toString()) //<2>
}

class SetThemeUI() : UI() {
    override fun init(request: VaadinRequest) {
        val themes = arrayListOf("valo", "reindeer", "runo", "chameleon", "liferay")
        horizontalLayout(spacing = true, margin = true) {
            optionGroup("Select theme", themes, changeTheme) { //<3>
                select("valo")
            }
        }
    }
}
// end::setthemeui[]