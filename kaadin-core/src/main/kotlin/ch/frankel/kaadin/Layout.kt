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

import com.vaadin.shared.ui.*
import com.vaadin.ui.*
import java.io.InputStream

/**
 * see http://demo.vaadin.com/sampler/#ui/layout
 */
class HorizontalLayoutView() : HorizontalLayout(), NoopView
class VerticalLayoutView() : VerticalLayout(), NoopView
class FormLayoutView() : FormLayout(), NoopView
class GridLayoutView(columns: Int, rows: Int) : GridLayout(columns, rows), NoopView
class AbsoluteLayoutView() : AbsoluteLayout(), NoopView
class CssLayoutView() : CssLayout(), NoopView

private fun <L : AbstractOrderedLayout> L.process(spacing: Boolean, margin: Boolean, init: L.() -> Unit) =
        apply {
            this.isSpacing = spacing
            this.margin = MarginInfo(margin)
        }.apply(init)

fun horizontalLayout(spacing: Boolean = false, margin: Boolean = false, init: HorizontalLayout.() -> Unit = {}) =
        HorizontalLayoutView().process(spacing, margin, init)

fun HasComponents.horizontalLayout(spacing: Boolean = false, margin: Boolean = false, init: HorizontalLayout.() -> Unit = {}) =
        ch.frankel.kaadin.horizontalLayout(spacing, margin, init).addTo(this)

fun verticalLayout(spacing: Boolean = false, margin: Boolean = false, init: VerticalLayout.() -> Unit = {}) =
        VerticalLayoutView().process(spacing, margin, init)

fun HasComponents.verticalLayout(spacing: Boolean = false, margin: Boolean = false, init: VerticalLayout.() -> Unit = {}) =
        ch.frankel.kaadin.verticalLayout(spacing, margin, init).addTo(this)

fun formLayout(spacing: Boolean = true, margin: Boolean = true, init: FormLayout.() -> Unit = {}) =
        FormLayoutView().process(spacing, margin, init)

fun HasComponents.formLayout(spacing: Boolean = true, margin: Boolean = true, init: FormLayout.() -> Unit = {}) =
        ch.frankel.kaadin.formLayout(spacing, margin, init).addTo(this)

fun gridLayout(columns: Int = 1, rows: Int = 1, spacing: Boolean = false, margin: Boolean = false, init: GridLayout.() -> Unit = {}) =
        GridLayoutView(columns, rows)
                .apply {
                    this.isSpacing = spacing
                    this.margin = MarginInfo(margin)
                }.apply(init)

fun HasComponents.gridLayout(columns: Int = 1, rows: Int = 1, spacing: Boolean = false, margin: Boolean = false, init: GridLayout.() -> Unit = {}) =
        ch.frankel.kaadin.gridLayout(columns, rows, spacing, margin, init).addTo(this)

fun customLayout(init: CustomLayout.() -> Unit = {}) = CustomLayout().apply(init)
fun customLayout(template: String, init: CustomLayout.() -> Unit = {}) = CustomLayout(template).apply(init)
fun customLayout(templateStream: InputStream, init: CustomLayout.() -> Unit = {}) = CustomLayout(templateStream).apply(init)

fun absoluteLayout(init: AbsoluteLayout.() -> Unit = {}) = AbsoluteLayoutView().apply(init)

fun cssLayout(vararg components: Component, init: CssLayout.() -> Unit = {}) = CssLayoutView()
        .apply {
            components.forEach { it.addTo(this) }
        }
        .apply(init)

// TODO LayoutClickNotifier.onClick(onClick: (LayoutClickEvent) -> Unit) = onClick