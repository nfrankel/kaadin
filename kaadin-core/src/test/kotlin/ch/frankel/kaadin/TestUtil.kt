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
package ch.frankel.kaadin

import com.vaadin.server.*
import com.vaadin.ui.*
import org.mockito.Mockito.*

/**
 * Initialize an empty UI and prevents NPE when switching views with a Navigator regarding the Page's location.
 */
internal fun initializeAndSetCurrentUI(): UI {
    val ui = object : UI() {
        override fun init(request: VaadinRequest) {
        }
    }
    UI.setCurrent(ui)
    val vaadinRequest = mock(VaadinRequest::class.java)
    `when`(vaadinRequest.getParameter("v-loc")).thenReturn("http://localhost:8080")
    ui.page.init(vaadinRequest)
    return ui
}