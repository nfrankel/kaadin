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

import com.vaadin.navigator.*
import com.vaadin.ui.*

interface NoopView : View {
    override fun enter(event: ViewChangeListener.ViewChangeEvent) {}
}

fun UI.navigator(container: ComponentContainer, init: Navigator.() -> Unit = {}) = Navigator(this, container).apply(init)
fun UI.navigator(container: SingleComponentContainer, init: Navigator.() -> Unit = {}) = Navigator(this, container).apply(init)
fun UI.navigator(display: (view: View) -> Unit, init: Navigator.() -> Unit = {}) = Navigator(this, display).apply(init)

class NamedView(val name: String, val view: View)

fun Navigator.provider(provider: ViewProvider) = addProvider(provider)
fun Navigator.view(viewName: String, view: View) = addView(viewName, view)
fun <V : View> Navigator.view(viewName: String, viewClass: Class<V>) = addView(viewName, viewClass)
fun Navigator.view(namedView: NamedView) = addView(namedView.name, namedView.view)
fun Navigator.views(namedViews: List<NamedView>) = namedViews.forEach { view(it.name, it.view) }
fun Navigator.views(vararg namedViews: NamedView) = views(namedViews.toList())

fun Navigator.errorProvider(provider: ViewProvider) = setErrorProvider(provider)
fun Navigator.errorView(view: View) = setErrorView(view)
fun <V : View> Navigator.errorView(viewClass: Class<V>) = setErrorView(viewClass)
