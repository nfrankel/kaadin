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
import com.vaadin.shared.ui.colorpicker.*
import com.vaadin.shared.ui.colorpicker.Color.*
import com.vaadin.ui.*
import com.vaadin.ui.Upload.*

/**
 * see http://demo.vaadin.com/sampler/#ui/data-input/other
 */
fun HasComponents.checkBox(caption: String? = null,
                           value: Boolean = false,
                           init: CheckBox.() -> Unit = {}) = CheckBox()
        .apply {
            caption?.let { this.caption = caption }
            this.value = value
        }
        .apply(init)
        .addTo(this)

fun HasComponents.checkBox(caption: String? = null,
                           dataSource: Property<out Any>,
                           init: CheckBox.() -> Unit = {}) = CheckBox()
        .apply {
            caption?.let { this.caption = caption }
            this.propertyDataSource = dataSource
        }
        .apply(init)
        .addTo(this)

fun HasComponents.slider(caption: String? = null, min: Int, max: Int, resolution: Int,
                         init: Slider.() -> Unit = {}) = Slider()
        .apply {
            caption?.let { this.caption = caption }
            this.resolution = resolution
            this.min = min.toDouble()
            this.max = max.toDouble()
        }
        .apply(init)
        .addTo(this)

fun HasComponents.slider(caption: String? = null, min: Double, max: Double,
                         init: Slider.() -> Unit = {}) = Slider()
        .apply {
            caption?.let { this.caption = caption }
            this.min = min
            this.max = max
        }
        .apply(init)
        .addTo(this)

fun HasComponents.upload(caption: String? = null, uploadReceiver: Receiver, init: Upload.() -> Unit = {}) = Upload()
        .apply {
            caption?.let { this.caption = caption }
            this.receiver = uploadReceiver
        }
        .apply(init)
        .addTo(this)

fun HasComponents.upload(uploadReceiver: Receiver, init: Upload.() -> Unit = {}) = upload(null, uploadReceiver, init)

fun HasComponents.colorPicker(popupCaption: String = "Colors",
                              initialColor: Color = WHITE,
                              init: ColorPicker.() -> Unit = {}) = ColorPicker()
        .apply {
            this.caption = popupCaption
            this.color = initialColor
        }
        .apply(init)
        .addTo(this)

fun HasComponents.colorPickerArea(popupCaption: String = "Colors",
                                  initialColor: Color = WHITE,
                                  init: ColorPickerArea.() -> Unit = {}) = ColorPickerArea()
        .apply {
            this.caption = popupCaption
            this.color = initialColor
        }
        .apply(init)
        .addTo(this)

// TODO DragAndDropWrapper
// TODO CustomComponent