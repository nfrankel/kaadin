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

import com.vaadin.data.*
import com.vaadin.data.util.*
import com.vaadin.data.util.converter.*
import com.vaadin.ui.*
import com.vaadin.ui.Grid.*
import com.vaadin.ui.renderers.*
import com.vaadin.ui.renderers.ClickableRenderer.*
import java.text.DateFormat
import java.text.NumberFormat
import java.util.*

/**
 * see http://demo.vaadin.com/sampler/#ui/grids-and-trees/grid
 */
fun HasComponents.grid(caption: String? = null,
                       dataSource: Container.Indexed = IndexedContainer(),
                       init: Grid.() -> Unit = {}): Grid {
    return Grid()
            .apply {
                caption?.let { this.caption = caption }
                this.containerDataSource = dataSource
            }
            .apply(init)
            .addTo(this)
}

fun Grid.column(propertyId: Any, init: Column.() -> Unit = {}): Column = getColumn(propertyId).apply(init)
fun Grid.columns(propertyIds: List<Any>, init: Column.() -> Unit = {}): Unit =
        propertyIds.forEach { column(it).apply(init) }

fun Grid.footerRowAtStart(init: Grid.FooterRow.() -> Unit = {}): FooterRow = prependFooterRow().apply(init)
fun Grid.footerRowAtEnd(init: Grid.FooterRow.() -> Unit = {}): FooterRow = appendFooterRow().apply(init)
fun Grid.footerRowAt(index: Int, init: Grid.FooterRow.() -> Unit = {}): FooterRow = addFooterRowAt(index).apply(init)

fun Grid.headerRowAtStart(init: Grid.HeaderRow.() -> Unit = {}): HeaderRow = prependHeaderRow().apply(init)
fun Grid.headerRowAtEnd(init: Grid.HeaderRow.() -> Unit = {}): HeaderRow = appendHeaderRow().apply(init)
fun Grid.headerRowAt(index: Int, init: Grid.HeaderRow.() -> Unit = {}): HeaderRow = addHeaderRowAt(index).apply(init)

fun Grid.cellStyleGenerator(generator: (Grid.CellReference) -> String?) = setCellStyleGenerator(generator)
fun Grid.cellStyleGenerator(predicate: (Grid.CellReference) -> Boolean, className: String) {
    val computeClassName: (Boolean) -> String? = { if (it) className else null }
    setCellStyleGenerator { computeClassName(predicate(it)) }
}
fun Grid.selectionMode(selectionMode: Grid.SelectionMode, init: Grid.SelectionModel.() -> Unit = {}): Unit {
    setSelectionMode(selectionMode).apply(init)
}

// Because StaticRow is not public
fun Grid.HeaderRow.cell(propertyId: Any, init: Grid.HeaderCell.() -> Unit = {}): HeaderCell = getCell(propertyId).apply(init)
fun Grid.FooterRow.cell(propertyId: Any, init: Grid.FooterCell.() -> Unit = {}): FooterCell = getCell(propertyId).apply(init)

private abstract class KaadinAbstractConverter<P, M>(private val modelClass: Class<M>,
                                                     private val presentationClass: Class<P>) : Converter<P, M> {
    override fun convertToModel(value: P, targetType: Class<out M>, locale: Locale) = throw UnsupportedOperationException("not implemented")
    override fun getPresentationType() = presentationClass
    override fun getModelType() = modelClass
}
private class KaadinLocaleConverter<P, M>(modelClass: Class<M>,
                                          presentationClass: Class<P>,
                                          private val convert: (value: M, locale: Locale) -> P) : KaadinAbstractConverter<P, M>(modelClass, presentationClass) {
    override fun convertToPresentation(value: M, targetType: Class<out P>, locale: Locale) = convert(value, locale)
}
private class KaadinConverter<P, M>(modelClass: Class<M>,
                                    presentationClass: Class<P>,
                                    private val convert: (value: M) -> P) :  KaadinAbstractConverter<P, M>(modelClass, presentationClass)  {
    override fun convertToPresentation(value: M, targetType: Class<out P>, locale: Locale) = convert(value)
}

fun <P, M> Column.converter(modelClass: Class<M>, presentationClass: Class<P>, convert: (value: M, locale: Locale) -> P): Converter<P, M> =
        KaadinLocaleConverter(modelClass, presentationClass, convert)
fun <P, M> Column.converter(modelClass: Class<M>, presentationClass: Class<P>, convert: (value: M) -> P): Converter<P, M> =
        KaadinConverter(modelClass, presentationClass, convert)

fun Column.bigDecimalConverter(): Unit {
    converter = StringToBigDecimalConverter()
}
fun Column.bigIntegerConverter(): Unit {
    converter = StringToBigIntegerConverter()
}
fun Column.booleanConverter(): Unit {
    converter = StringToBooleanConverter()
}
fun Column.byteConverter(): Unit {
    converter = StringToByteConverter()
}
fun Column.collectionConverter(): Unit {
    converter = StringToCollectionConverter()
}
fun Column.doubleConverter(): Unit {
    converter = StringToDoubleConverter()
}
fun Column.dateConverter(): Unit {
    converter = StringToDateConverter()
}
fun Column.enumConverter(): Unit {
    converter = StringToEnumConverter()
}
fun Column.floatConverter(): Unit {
    converter = StringToFloatConverter()
}
fun Column.intConverter(): Unit {
    converter = StringToIntegerConverter()
}
fun Column.longConverter(): Unit {
    converter = StringToLongConverter()
}
fun Column.shortConverter(): Unit {
    converter = StringToShortConverter()
}

fun <P, M> Column.converter(converter: Converter<P, M>): Unit {
    this.converter = converter
}
fun Column.unsetConverter(): Unit {
    converter = null
}

fun <T> Column.renderer(renderer: Renderer<T>): Unit {
    this.renderer = renderer
}
fun <T> Column.renderer(renderer: Renderer<T>, converter: Converter<out T, Any>): Unit {
    setRenderer(renderer, converter)
}
fun Column.buttonRenderer(nullRepresentation: String = "", onClick: (RendererClickEvent) -> Unit = {}): Unit {
    renderer = ButtonRenderer(onClick, nullRepresentation)
}
fun Column.dateRenderer(formatString: String = "%s", locale: Locale = Locale.getDefault(), nullRepresentation: String = ""): Unit {
    renderer = DateRenderer(formatString, locale, nullRepresentation)
}
fun Column.dateRenderer(format: DateFormat, nullRepresentation: String = ""): Unit {
    renderer = DateRenderer(format, nullRepresentation)
}
fun Column.htmlRenderer(nullRepresentation: String = ""): Unit {
    renderer = HtmlRenderer(nullRepresentation)
}
fun Column.imageRenderer(onClick: (RendererClickEvent) -> Unit = {}): Unit {
    renderer = ImageRenderer(onClick)
}
fun Column.numberRenderer(formatString: String = "%s", locale: Locale = Locale.getDefault(), nullRepresentation: String = ""): Unit {
    renderer = NumberRenderer(formatString, locale, nullRepresentation)
}
fun Column.numberRenderer(format: NumberFormat, nullRepresentation: String = ""): Unit {
    renderer = NumberRenderer(format, nullRepresentation)
}
fun Column.progressBarRenderer(): Unit {
    renderer = ProgressBarRenderer()
}
fun Column.textRenderer(nullRepresentation: String = ""): Unit {
    renderer = TextRenderer(nullRepresentation)
}