package ch.frankel.kaadin.navigator

import com.vaadin.server.*
import com.vaadin.ui.*
import org.mockito.Mockito.*

internal const val NAVIGATOR_GROUP = "navigator"

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