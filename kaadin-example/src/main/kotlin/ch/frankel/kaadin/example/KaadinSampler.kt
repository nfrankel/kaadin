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
package ch.frankel.kaadin.example

import ch.frankel.kaadin.*
import com.vaadin.annotations.*
import com.vaadin.data.util.*
import com.vaadin.server.*
import com.vaadin.server.FontAwesome.*
import com.vaadin.server.Sizeable.Unit.*
import com.vaadin.shared.ui.colorpicker.Color.*
import com.vaadin.shared.ui.label.ContentMode.*
import com.vaadin.ui.*
import com.vaadin.ui.Grid.SelectionMode.*
import com.vaadin.ui.MenuBar.*
import com.vaadin.ui.components.calendar.event.*
import java.io.ByteArrayOutputStream
import java.util.*

@javax.servlet.annotation.WebServlet(value = "/*", asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = KaadinSampler::class)
class TestServlet() : VaadinServlet() {}

@Theme("valo")
class KaadinSampler() : UI() {

    override fun init(request: VaadinRequest?) {
        content = verticalLayout(margin = true, spacing = true) {
            tabSheet {
                tab("Interactions") {
                    accordion {
                        tab("Button", HAND_O_RIGHT) {
                            horizontalLayout(true, true) {
                                button()
                                button("Label")
                                button("Label", HAND_O_RIGHT)
                                button("Click me", onClick = { show("Clicked") })
                                button("Click me", HAND_O_RIGHT, { show("Clicked") })
                            }
                        }
                        tab("Native button", HAND_O_RIGHT) {
                            horizontalLayout(true, true) {
                                nativeButton()
                                nativeButton("Label")
                                nativeButton("Click me", { show("Clicked") })
                            }
                        }
                        tab("Link", LINK) {
                            horizontalLayout(true, true) {
                                link("Label")
                                link("Click me", ExternalResource("http://vaadin.com"))
                                link("Click me - blank", ExternalResource("http://vaadin.com"), "_blank", 640, 480)
                            }
                        }
                        tab("Progress Bar", ARROWS_H) {
                            horizontalLayout(true, true) {
                                progressBar(0.5F)
                                progressBar(ObjectProperty<Float>(0.5F))
                            }
                        }
                        tab("Menu bar", BARS) {
                            horizontalLayout(true, true) {
                                menuBar {
                                    isAutoOpen = true
                                    menuItem("Icon and click listener", FOLDER, command = Command { Notification.show("Clicked $it") }) {
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
                            }
                        }
                        tab("Notification", EXCLAMATION_TRIANGLE) {
                            horizontalLayout(true, true) {
                                button("Humanized", onClick = { show("Humanized message") })
                                button("Warn", onClick = { warn("Warning message") })
                                button("Error", onClick = { error("Error message") })
                                button("Tray", onClick = { tray("Tray message at ${System.currentTimeMillis()}") })
                            }
                        }
                    }
                }
                tab("Data Input - Text", FONT) {
                    accordion {
                        val stringProp = ObjectProperty<String>("From property")
                        tab("Text Field", FONT) {
                            formLayout {
                                textField()
                                textField(dataSource = stringProp)
                                textField("No value")
                                textField("From property", stringProp)
                                textField("From value", "From value")
                            }
                        }
                        tab("Text Area", FILE_TEXT_O) {
                            horizontalLayout(true, true) {
                                textArea()
                                textArea(dataSource = stringProp)
                                textArea("No value")
                                textArea("From property", stringProp)
                                textArea("From value", "From value")
                            }
                        }
                        tab("Rich Text Area", INDENT) {
                            verticalLayout(true, true) {
                                richTextArea()
                                richTextArea(dataSource = stringProp)
                                richTextArea("No value")
                                richTextArea("From property", stringProp)
                                richTextArea("From value", "From value")
                            }
                        }
                        tab("Password Field", USER_SECRET) {
                            horizontalLayout(true, true) {
                                passwordField()
                                passwordField(dataSource = stringProp)
                                passwordField("No value")
                                passwordField("From property", stringProp)
                                passwordField("From value", "From value")
                            }
                        }
                    }
                }
                tab("Data Input - Date", CALENDAR) {
                    accordion {
                        val dateProp = ObjectProperty<Date>(Date())
                        tab("Date Field", CALENDAR) {
                            formLayout {
                                dateField()
                                dateField(dataSource = dateProp)
                                dateField("No value")
                                dateField("From property", dateProp)
                                dateField("From value", Date())
                            }
                        }
                        tab("Inline Date Field", CALENDAR) {
                            gridLayout(3, 2, true, true) {
                                inlineDateField()
                                inlineDateField(dataSource = dateProp)
                                inlineDateField("No value")
                                inlineDateField("From property", dateProp)
                                inlineDateField("From value", Date())
                            }
                        }
                        tab("Calendar", CALENDAR) {
                            verticalLayout(true, true) {
                                calendar()
                                calendar(eventProvider = BasicEventProvider())
                                calendar("No event provider")
                                calendar("With provider provided", BasicEventProvider().apply {
                                    addEvent(BasicEvent("Event name", "Event description", Date()))
                                })
                            }
                        }
                    }
                }
                tab("Data Input - Multiple", LIST) {
                    accordion {
                        val list = listOf("First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth", "Tenth")
                        val dataSource = IndexedContainer().apply { list.forEach { addItem(it) } }
                        tab("Combo box", LIST) {
                            formLayout {
                                comboBox()
                                comboBox(dataSource = dataSource)
                                comboBox("No value")
                                comboBox("From collection", list)
                                comboBox("From property", dataSource)
                            }
                        }
                        tab("Native select", LIST) {
                            horizontalLayout(true, true) {
                                nativeSelect()
                                nativeSelect(dataSource = dataSource)
                                nativeSelect("No value")
                                nativeSelect("From collection", list)
                                nativeSelect("From property", dataSource)
                            }
                        }
                        tab("List select", LIST) {
                            horizontalLayout(true, true) {
                                listSelect()
                                listSelect(dataSource = dataSource)
                                listSelect("No value")
                                listSelect("From collection", list)
                                listSelect("From property", dataSource)
                            }
                        }
                        tab("Twin columns select", TH_LIST) {
                            gridLayout(3, 2, true, true) {
                                twinColSelect()
                                twinColSelect(dataSource = dataSource)
                                twinColSelect("No value")
                                twinColSelect("From collection", list)
                                twinColSelect("From property", dataSource)
                            }
                        }
                        tab("Options Group", CHECK_CIRCLE_O) {
                            horizontalLayout(true, true) {
                                optionGroup()
                                optionGroup(dataSource = dataSource)
                                optionGroup("No value")
                                optionGroup("From collection", list)
                                optionGroup("From property", dataSource)
                            }
                        }
                    }
                }
                tab("Data Input - Other") {
                    accordion {
                        tab("Check box", CHECK_SQUARE_O) {
                            horizontalLayout(true, true) {
                                checkBox()
                                checkBox("No value")
                                checkBox("From value", true)
                                checkBox("From property", ObjectProperty<Boolean>(true))
                            }
                        }
                        tab("Slider", BATTERY_THREE_QUARTERS) {
                            horizontalLayout(true, true) {
                                slider(min = 1, max = 10, resolution = 2)
                                slider("From integers", 1, 10, 2)
                                slider(min = 1.00, max = 10.00)
                                slider("From doubles", 1.00, 10.00)
                            }
                        }
                        tab("Upload", UPLOAD) {
                            horizontalLayout(true, true) {
                                val receiver = Upload.Receiver { name, type ->
                                    ByteArrayOutputStream().apply {
                                        show("File $name received", "MIME type is $type")
                                    }
                                }
                                upload(receiver)
                                upload("Caption", receiver)
                            }
                        }
                        tab("Color Picker") {
                            horizontalLayout(true, true) {
                                colorPicker()
                                colorPicker("Default initial color")
                                colorPicker(initialColor = RED)
                                colorPicker("Initial color set", RED)
                            }
                        }
                        tab("Color Picker Area") {
                            horizontalLayout(true, true) {
                                colorPickerArea()
                                colorPickerArea("Default initial color")
                                colorPickerArea(initialColor = RED)
                                colorPickerArea("Initial color set", RED)
                            }
                        }
                    }
                }
                tab("Data Presentation") {
                    accordion {
                        tab("Label", BOLD) {
                            verticalLayout(true, true) {
                                val stringProp = ObjectProperty<String>("<strong>From</strong> <em>property")
                                label("<strong>From</strong> <em>value")
                                label("<strong>From</strong> <em>value", HTML)
                                label(stringProp)
                                label(stringProp, HTML)
                            }
                        }
                        tab("Image", PHOTO) {
                            horizontalLayout(true, true) {
                                image()
                                image("No image")
                                image(source = ClassResource("/vaadin-logo.png"))
                                image("Vaadin logo", ClassResource("/vaadin-logo.png"))
                            }
                        }
                        tab("Browser Frame") {
                            horizontalLayout(true, true) {
                                browserFrame()
                                browserFrame("No resource")
                                browserFrame(source = ClassResource("/vaadin-logo.png"))
                                browserFrame("Vaadin logo", ClassResource("/vaadin-logo.png"))
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
                                    this.converter = ch.frankel.kaadin.example.converter
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
                        tab("Split panel", COLUMNS) {
                            horizontalSplitPanel {
                                setHeight(200F, PIXELS)
                                verticalSplitPanel {
                                    label("1")
                                    label("2")
                                }
                                verticalSplitPanel {
                                    label("3")
                                    label("4")
                                }
                            }
                        }
                        tab("Panel") {
                            panel {
                                horizontalLayout(margin = true) { label("Inside a panel") }
                            }
                        }
                    }
                }
            }
        }
    }
}
