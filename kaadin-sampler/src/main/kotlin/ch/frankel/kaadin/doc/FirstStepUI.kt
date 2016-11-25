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

// tag::simpleusageui[]
import ch.frankel.kaadin.* //<1>
import com.vaadin.server.*
import com.vaadin.ui.*

class FirstStepUI(): UI() {
    override fun init(request: VaadinRequest) {
        verticalLayout(margin = true, spacing = true) { //<2>
            label("Welcome to Vaadin") //<3>
        }
    }
}
// end::simpleusageui[]