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
package ch.frankel.kaadin.sampler

import ch.frankel.kaadin.*
import com.vaadin.data.util.*
import com.vaadin.server.*
import com.vaadin.server.FontAwesome.*
import com.vaadin.server.Sizeable.Unit.*
import com.vaadin.shared.ui.colorpicker.Color.*
import com.vaadin.shared.ui.label.ContentMode.*
import com.vaadin.ui.*
import com.vaadin.ui.Grid.SelectionMode.*
import com.vaadin.ui.components.calendar.event.*
import java.io.ByteArrayOutputStream
import java.util.*

class KaadinSampler() : UI() {

    override fun init(request: VaadinRequest) {
        theme = "valo"
        verticalLayout(margin = true, spacing = true) {
            tabSheet {
                tab("Interactions") {
                    accordion {
                        tab("Button", HAND_O_RIGHT) {
                            horizontalLayout(true, true) {
                                // tag::button[]
                                button()
                                button("Label")
                                button("Label", HAND_O_RIGHT)
                                button("Click me", onClick = { show("Clicked") })
                                button("Click me", HAND_O_RIGHT, { show("Clicked") })
                                // end::button[]
                            }
                        }
                        tab("Native button", HAND_O_RIGHT) {
                            horizontalLayout(true, true) {
                                // tag::nativebutton[]
                                nativeButton()
                                nativeButton("Label")
                                nativeButton("Click me", { show("Clicked") })
                                // end::nativebutton[]
                            }
                        }
                        tab("Link", LINK) {
                            horizontalLayout(true, true) {
                                // tag::link[]
                                link("Label")
                                link("Click me", ExternalResource("http://vaadin.com"))
                                link("Click me - blank", ExternalResource("http://vaadin.com"), "_blank", 640, 480)
                                // end::link[]
                            }
                        }
                        tab("Progress Bar", ARROWS_H) {
                            horizontalLayout(true, true) {
                                // tag::progressbar[]
                                progressBar(0.5F)
                                progressBar(ObjectProperty<Float>(0.5F))
                                // end::progressbar[]
                            }
                        }
                        tab("Menu bar", BARS) {
                            horizontalLayout(true, true) {
                                // tag::menubar[]
                                menuBar {
                                    isAutoOpen = true
                                    menuItem("Icon and click listener", FOLDER, onClick = { show("Clicked $it") }) {
                                        menuItem("Disabled", enabled = false)
                                        menuItem("Checkable", checkable = true, checked = true)
                                        separator()
                                        menuItem("Simple")
                                        menuItem("Icon", FOLDER)
                                        separator()
                                        menuItem("Simple")
                                    }
                                    menuItem("Checkable", checkable = true)
                                    menuItem("Disabled", enabled = false)
                                }
                                // end::menubar[]
                            }
                        }
                        tab("Notification", EXCLAMATION_TRIANGLE) {
                            horizontalLayout(true, true) {
                                // tag::notification[]
                                button("Humanized", onClick = { show("Humanized message") })
                                button("Warn", onClick = { warn("Warning message") })
                                button("Error", onClick = { error("Error message") })
                                button("Tray", onClick = { tray("Tray message at ${System.currentTimeMillis()}") })
                                // end::notification[]
                            }
                        }
                    }
                }
                tab("Data Input - Text", FONT) {
                    accordion {
                        // tag::stringprop[]
                                val stringProp = ObjectProperty<String>("From property")
                        // end::stringprop[]
                        tab("Text Field", FONT) {
                            formLayout {
                                // tag::textfield[]
                                textField()
                                textField(dataSource = stringProp)
                                textField("No value")
                                textField("From property", stringProp)
                                textField("From value", "From value")
                                // end::textfield[]
                            }
                        }
                        tab("Text Area", FILE_TEXT_O) {
                            horizontalLayout(true, true) {
                                // tag::textarea[]
                                textArea()
                                textArea(dataSource = stringProp)
                                textArea("No value")
                                textArea("From property", stringProp)
                                textArea("From value", "From value")
                                // end::textarea[]
                            }
                        }
                        tab("Rich Text Area", INDENT) {
                            verticalLayout(true, true) {
                                // tag::richtextarea[]
                                richTextArea()
                                richTextArea(dataSource = stringProp)
                                richTextArea("No value")
                                richTextArea("From property", stringProp)
                                richTextArea("From value", "From value")
                                // end::richtextarea[]
                            }
                        }
                        tab("Password Field", USER_SECRET) {
                            horizontalLayout(true, true) {
                                // tag::passwordfield[]
                                passwordField()
                                passwordField(dataSource = stringProp)
                                passwordField("No value")
                                passwordField("From property", stringProp)
                                passwordField("From value", "From value")
                                // end::passwordfield[]
                            }
                        }
                    }
                }
                tab("Data Input - Date", CALENDAR) {
                    accordion {
                        // tag::dateprop[]
                                val dateProp = ObjectProperty<Date>(Date())
                        // end::dateprop[]
                        tab("Date Field", CALENDAR) {
                            formLayout {
                                // tag::datefield[]
                                dateField()
                                dateField(dataSource = dateProp)
                                dateField("No value")
                                dateField("From property", dateProp)
                                dateField("From value", Date())
                                // end::datefield[]
                            }
                        }
                        tab("Inline Date Field", CALENDAR) {
                            gridLayout(3, 2, true, true) {
                                // tag::inlinedatefield[]
                                inlineDateField()
                                inlineDateField(dataSource = dateProp)
                                inlineDateField("No value")
                                inlineDateField("From property", dateProp)
                                inlineDateField("From value", Date())
                                // end::inlinedatefield[]
                            }
                        }
                        tab("Calendar", CALENDAR) {
                            verticalLayout(true, true) {
                                // tag::calendar[]
                                calendar()
                                calendar(eventProvider = BasicEventProvider())
                                calendar("No event provider")
                                calendar("With provider provided", BasicEventProvider().apply {
                                    addEvent(BasicEvent("Event name", "Event description", Date()))
                                })
                                // end::calendar[]
                            }
                        }
                    }
                }
                tab("Data Input - Multiple", LIST) {
                    accordion {
                                // tag::indexedcontainer[]
                                val list = listOf("First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh")
                                val dataSource = IndexedContainer(list)
                                // end::indexedcontainer[]
                        tab("Combo box", LIST) {
                            formLayout {
                                // tag::combo[]
                                comboBox()
                                comboBox(dataSource = dataSource)
                                comboBox("No value")
                                comboBox("From collection", list)
                                comboBox("From property", dataSource)
                                // end::combo[]
                            }
                        }
                        tab("Native select", LIST) {
                            horizontalLayout(true, true) {
                                // tag::nativeselect[]
                                nativeSelect()
                                nativeSelect(dataSource = dataSource)
                                nativeSelect("No value")
                                nativeSelect("From collection", list)
                                nativeSelect("From property", dataSource)
                                // end::nativeselect[]
                            }
                        }
                        tab("List select", LIST) {
                            horizontalLayout(true, true) {
                                // tag::select[]
                                listSelect()
                                listSelect(dataSource = dataSource)
                                listSelect("No value")
                                listSelect("From collection", list)
                                listSelect("From property", dataSource)
                                // end::select[]
                            }
                        }
                        tab("Twin columns select", TH_LIST) {
                            gridLayout(3, 2, true, true) {
                                // tag::twinselect[]
                                twinColSelect()
                                twinColSelect(dataSource = dataSource)
                                twinColSelect("No value")
                                twinColSelect("From collection", list)
                                twinColSelect("From property", dataSource)
                                // end::twinselect[]
                            }
                        }
                        tab("Options Group", CHECK_CIRCLE_O) {
                            horizontalLayout(true, true) {
                                // tag::options[]
                                optionGroup()
                                optionGroup(dataSource = dataSource)
                                optionGroup("No value")
                                optionGroup("From collection", list)
                                optionGroup("From property", dataSource)
                                // end::options[]
                            }
                        }
                    }
                }
                tab("Data Input - Other") {
                    accordion {
                        tab("Check box", CHECK_SQUARE_O) {
                            horizontalLayout(true, true) {
                                // tag::checkbox[]
                                checkBox()
                                checkBox("No value")
                                checkBox("From value", true)
                                checkBox("From property", ObjectProperty<Boolean>(true))
                                // end::checkbox[]
                            }
                        }
                        tab("Slider", BATTERY_THREE_QUARTERS) {
                            horizontalLayout(true, true) {
                                // tag::slider[]
                                slider(min = 1, max = 10, resolution = 2)
                                slider("From integers", 1, 10, 2)
                                slider(min = 1.00, max = 10.00)
                                slider("From doubles", 1.00, 10.00)
                                // end::slider[]
                            }
                        }
                        tab("Upload", UPLOAD) {
                            horizontalLayout(true, true) {
                                // tag::upload[]
                                val receiver = Upload.Receiver { name, type ->
                                    ByteArrayOutputStream().apply {
                                        show("File $name received", "MIME type is $type")
                                    }
                                }
                                upload(receiver)
                                upload("Caption", receiver)
                                // end::upload[]
                            }
                        }
                        tab("Color Picker") {
                            horizontalLayout(true, true) {
                                // tag::colorpicker[]
                                colorPicker()
                                colorPicker("Default initial color")
                                colorPicker(initialColor = RED)
                                colorPicker("Initial color set", RED)
                                // end::colorpicker[]
                            }
                        }
                        tab("Color Picker Area") {
                            horizontalLayout(true, true) {
                                // tag::colorpickerarea[]
                                colorPickerArea()
                                colorPickerArea("Default initial color")
                                colorPickerArea(initialColor = RED)
                                colorPickerArea("Initial color set", RED)
                                // end::colorpickerarea[]
                            }
                        }
                    }
                }
                tab("Data Presentation") {
                    accordion {
                        tab("Label", BOLD) {
                            verticalLayout(true, true) {
                                // tag::label[]
                                val stringProp = ObjectProperty<String>("<strong>From</strong> <em>property")
                                label("<strong>From</strong> <em>value")
                                label("<strong>From</strong> <em>value", HTML)
                                label(stringProp)
                                label(stringProp, HTML)
                                html("<strong>From</strong> <em>value")
                                html(stringProp)
                                // end::label[]
                            }
                        }
                        tab("Image", PHOTO) {
                            horizontalLayout(true, true) {
                                // tag::image[]
                                image()
                                image("No image")
                                classpathImage(source = "/vaadin-logo.png")
                                classpathImage("Vaadin logo", "/vaadin-logo.png")
                                // end::image[]
                            }
                        }
                        tab("Browser Frame") {
                            horizontalLayout(true, true) {
                                // tag::frame[]
                                frame()
                                frame("No resource")
                                classpathFrame(source = "/vaadin-logo.png")
                                classpathFrame("Vaadin logo", "/vaadin-logo.png")
                                // end::frame[]
                            }
                        }
                    }
                }
                tab("Grid and Tree") {
                    accordion {
                        tab("Grid") {
                            val flatDataSource = beanItemContainer(Line::class.java) {
                                beans(Line.data)
                            }
                            grid(dataSource = flatDataSource) {
                                width(100f, PERCENTAGE)
                                selectionMode(SINGLE) {
                                    reset()
                                }
                                frozenColumnCount = 1
                                cellStyleGenerator({ it.propertyId == "company" }, "align-right")
                                headerRowAt(1) {
                                    cell("company") {
                                        component = TextField()
                                        styleName = "filter-header"
                                    }
                                    /*
                                    Target:
                                    cell("company") {
                                        textField()
                                    }
                                     */
                                }
                                footerRowAtEnd()
                                val propertyIds = IntRange(0, 9)
                                        .map { "year$it" }
                                        .flatMap { arrayListOf("${it}q1", "${it}q2") }
                                columns(propertyIds) {
                                    converter = ch.frankel.kaadin.sampler.converter
                                }
                            }
                        }
                        tab("Table") {
                            table(dataSource = beanItemContainer(Person::class.java) {
                                beans(Person.devs)
                                bean(Person.lead)
                            })
                        }
                    }
                }
                tab("Structure & Hierarchy") {
                    accordion {
                        tab("Panel") {
                            // tag::panel[]
                            panel {
                                horizontalLayout(margin = true) { label("Inside a panel") }
                            }
                            // end::panel[]
                        }
                        tab("Popup View") {
                            // tag::popup[]
                            horizontalLayout(true, true) {
                                popupView("Component defined inside the body") {
                                    verticalLayout {
                                        textField("Component defined inside the body")
                                    }
                                }
                                val content = object: PopupView.Content {
                                    override fun getPopupComponent() = VerticalLayout(
                                            TextField("Component defined outside the body")
                                    )
                                    override fun getMinimizedValueAsHTML() = "Component defined outside the body"
                                }
                                popupView(content)
                            }
                            // end::popup[]
                        }
                        tab("Split panel", COLUMNS) {
                            // tag::splitpanel[]
                            horizontalSplitPanel {
                                height(200F, PIXELS)
                                verticalSplitPanel {
                                    label("1")
                                    label("2")
                                }
                                verticalSplitPanel {
                                    label("3")
                                    label("4")
                                }
                            }
                            // end::splitpanel[]
                        }
                    }
                }
            }
        }
    }
}
