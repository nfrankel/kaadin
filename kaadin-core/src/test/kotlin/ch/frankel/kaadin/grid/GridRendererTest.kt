package ch.frankel.kaadin.grid

import ch.frankel.kaadin.*
import ch.frankel.kaadin.grid.GridTestData
import ch.frankel.kaadin.grid.getGrid
import ch.frankel.kaadin.grid.getStringColumn
import com.vaadin.ui.renderers.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

@Test(dependsOnGroups = arrayOf("baseGrid"))
class GridRendererTest {

    @Test
    fun `column should add button renderer`() {
        val layout = horizontalLayout {
            grid(dataSource = GridTestData.container) {
                column("string").buttonRenderer()
            }
        }
        val grid = layout.getGrid()
        assertThat(grid.getStringColumn().renderer)
                .isNotNull()
                .isInstanceOf(ButtonRenderer::class.java)
    }

    @Test
    fun `column should add custom renderer`() {
        val layout = horizontalLayout {
            grid(dataSource = GridTestData.container) {
                column("string").renderer(TextRenderer())
            }
        }
        val grid = layout.getGrid()
        assertThat(grid.getStringColumn().renderer)
                .isNotNull()
                .isInstanceOf(TextRenderer::class.java)
    }
}