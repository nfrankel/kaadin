package ch.frankel.kaadin.structure

import ch.frankel.kaadin.*
import com.vaadin.ui.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class PopupViewTest {

    @Test
    fun `popup should be added to layout via nested API`() {
        val layout = horizontalLayout {
            popupView("dummy") {
                label()
            }
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(PopupView::class.java)
    }

    @Test(dependsOnMethods = arrayOf("popup should be added to layout via nested API"))
    fun `popup should display component when clicked via nested API`() {
        val layout = horizontalLayout {
            popupView("dummy") {
                label()
            }
        }
        val component = layout.getComponent(0) as PopupView
        val popupComponent = component.content?.popupComponent
        assertThat(popupComponent).isNotNull().isInstanceOf(Label::class.java)
    }

    @Test
    fun `popup should be added to layout via parameter`() {
        val popup = object : PopupView.Content {
            override fun getPopupComponent() = Label()
            override fun getMinimizedValueAsHTML() = "dummy"
        }
        val layout = horizontalLayout {
            popupView(popup)
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(PopupView::class.java)
    }

    @Test(dependsOnMethods = arrayOf("popup should be added to layout via nested API"))
    fun `popup should be configurable`() {
        val layout = horizontalLayout {
            popupView("dummy") {
                label()
                isPopupVisible = true
            }
        }
        val component = layout.getComponent(0) as PopupView
        assertThat(component.isPopupVisible).isTrue()
    }
}