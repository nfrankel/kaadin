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

// tag::navigatorui[]
import ch.frankel.kaadin.*
import com.vaadin.server.*
import com.vaadin.ui.*

class NavigatorUI() : UI() {
    private val firstView = firstView()
    private val secondView = secondView()
    override fun init(request: VaadinRequest) {
        navigator(verticalLayout(margin = true)) { //<1>
            view("", firstView) //<2>
            view("second", secondView) //<3>
        }
    }
}

private fun firstView() = verticalLayout {
    html("<h1>Kaadin</h1>")
    label("Kaadin is a library to create Vaadin Graphical User Interface using a Kotlin DSL.")
    button("Navigate to second view", onClick = { ui().navigator.navigateTo("second") }) //<4>
}

private fun secondView() = verticalLayout {
    label("This is the second view, as an example.")
    button("Navigate back to first view", onClick = { ui().navigator.navigateTo("") }) //<5>
}
// end::navigatorui[]