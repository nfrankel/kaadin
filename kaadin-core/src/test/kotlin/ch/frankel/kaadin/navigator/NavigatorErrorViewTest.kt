package ch.frankel.kaadin.navigator

import ch.frankel.kaadin.*
import com.vaadin.navigator.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@Test(dependsOnGroups = arrayOf(NAVIGATOR_GROUP))
class NavigatorErrorViewTest {

    @BeforeMethod
    fun setUp() {
        initializeAndSetCurrentUI()
    }

    @Test
    fun `navigator can be set an error view`() {
        ui {
            navigator({ }) {
                errorView(HorizontalLayoutView())
            }
        }
        val navigator = ui().navigator
        navigator.navigateTo("does not exist")
        assertThat(navigator.currentView).isNotNull().isInstanceOf(HorizontalLayoutView::class.java)
    }

    @Test
    fun `navigator can be set an error view with class`() {
        val errorViewClass = AbsoluteLayoutView::class.java
        ui {
            navigator({ }) {
                errorView(errorViewClass)
            }
        }
        val navigator = ui().navigator
        navigator.navigateTo("does not exist")
        assertThat(navigator.currentView).isNotNull().isInstanceOf(errorViewClass)
    }

    @Test
    fun `navigator can be set an error view provider`() {
        val provider = object : ViewProvider {
            override fun getViewName(viewAndParameters: String) = "dummy"
            override fun getView(viewName: String) = CssLayoutView()
        }
        ui {
            navigator({ }) {
                errorProvider(provider)
            }
        }
        val navigator = ui().navigator
        navigator.navigateTo("does not exist")
        assertThat(navigator.currentView).isNotNull().isInstanceOf(CssLayoutView::class.java)
    }
}