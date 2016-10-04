package ch.frankel.kaadin.doc

// tag::simpleusageui[]
import ch.frankel.kaadin.*
import com.vaadin.server.*
import com.vaadin.ui.*

class SimpleUsageUI(): UI() {
    override fun init(request: VaadinRequest) {
        verticalLayout(margin = true, spacing = true) {
            label("Welcome to Vaadin")
        }
    }
}
// end::simpleusageui[]