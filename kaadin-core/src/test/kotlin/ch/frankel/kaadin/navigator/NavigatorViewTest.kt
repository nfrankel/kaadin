package ch.frankel.kaadin.navigator

import ch.frankel.kaadin.*
import com.vaadin.navigator.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@Test(dependsOnGroups = arrayOf(NAVIGATOR_GROUP))
class NavigatorViewTest {

    @BeforeMethod
    fun setUp() {
        initializeAndSetCurrentUI()
    }

    @Test
    fun `navigator can be set a single view`() {
        val viewName = "dummy"
        ui {
            navigator({ }) {
                view(viewName, HorizontalLayoutView())
            }
        }
        val navigator = ui().navigator
        navigator.navigateTo(viewName)
        assertThat(navigator.currentView).isNotNull().isInstanceOf(HorizontalLayoutView::class.java)
    }

    @Test
    fun `navigator can be set a single view class`() {
        val viewName = "dummy"
        ui {
            navigator({ }) {
                view(viewName, HorizontalLayoutView::class.java)
            }
        }
        val navigator = ui().navigator
        navigator.navigateTo(viewName)
        assertThat(navigator.currentView).isNotNull().isInstanceOf(HorizontalLayoutView::class.java)
    }

    @Test(dependsOnMethods = arrayOf("navigator can be set a single view"))
    fun `navigator can be set multiple views`() {
        val viewName1 = "dummy"
        val viewName2 = "another $viewName1"
        ui {
            navigator({ }) {
                view(viewName1, VerticalLayoutView())
                view(viewName2, VerticalLayoutView())
                view(viewName1, HorizontalLayoutView())
            }
        }
        val navigator = ui().navigator
        navigator.navigateTo(viewName2)
        assertThat(navigator.currentView).isNotNull().isInstanceOf(VerticalLayoutView::class.java)
        navigator.navigateTo(viewName1)
        assertThat(navigator.currentView).isNotNull().isInstanceOf(HorizontalLayoutView::class.java)
    }

    @Test
    fun `navigator can be set a single named view`() {
        val viewName = "dummy"
        val namedView = NamedView(viewName, HorizontalLayoutView())
        ui {
            navigator({ }) {
                view(namedView)
            }
        }
        val navigator = ui().navigator
        navigator.navigateTo(viewName)
        assertThat(navigator.currentView).isNotNull().isInstanceOf(HorizontalLayoutView::class.java)
    }

    @Test
    fun `navigator can be set multiple named view with a list`() {
        val list = IntRange(0, 9).map { NamedView("dummy$it", HorizontalLayoutView()) }
        ui {
            navigator({ }) {
                views(list)
                view("dummy3", VerticalLayoutView())
            }
        }
        val navigator = ui().navigator
        navigator.navigateTo("dummy1")
        assertThat(navigator.currentView).isNotNull().isInstanceOf(HorizontalLayoutView::class.java)
        navigator.navigateTo("dummy3")
        assertThat(navigator.currentView).isNotNull().isInstanceOf(VerticalLayoutView::class.java)
        navigator.navigateTo("dummy9")
        assertThat(navigator.currentView).isNotNull().isInstanceOf(HorizontalLayoutView::class.java)
    }

    @Test
    fun `navigator can be set multiple named view with varargs`() {
        val list = IntRange(0, 9).map { NamedView("dummy$it", HorizontalLayoutView()) }.toTypedArray()
        ui {
            navigator({ }) {
                views(* list)
                view("dummy3", VerticalLayoutView())
            }
        }
        val navigator = ui().navigator
        navigator.navigateTo("dummy1")
        assertThat(navigator.currentView).isNotNull().isInstanceOf(HorizontalLayoutView::class.java)
        navigator.navigateTo("dummy3")
        assertThat(navigator.currentView).isNotNull().isInstanceOf(VerticalLayoutView::class.java)
        navigator.navigateTo("dummy9")
        assertThat(navigator.currentView).isNotNull().isInstanceOf(HorizontalLayoutView::class.java)
    }

    @Test
    fun `navigator can be set a view provider`() {
        val viewName = "dummy"
        val provider = object : ViewProvider {
            override fun getViewName(viewAndParameters: String) = viewName
            override fun getView(viewName: String) = CssLayoutView()
        }
        ui {
            navigator({ }) {
                provider(provider)
            }
        }
        val navigator = ui().navigator
        navigator.navigateTo(viewName)
        assertThat(navigator.currentView).isNotNull().isInstanceOf(CssLayoutView::class.java)
    }
}