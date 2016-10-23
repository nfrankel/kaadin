package ch.frankel.kaadin.interaction.grid

import ch.frankel.kaadin.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

@Test(dependsOnGroups = arrayOf("baseGrid"))
class GridHeaderFooterTest {

    @Test
    fun `grid should add header rows`() {
        val layout = horizontalLayout {
            grid(dataSource = GridTestData.Companion.container) {
                headerRowAtStart()
                headerRowAtEnd()
                headerRowAt(1)
            }
        }
        val grid = layout.getGrid()
        assertThat(grid.headerRowCount).isEqualTo(3 + 1) // There's one default header row
    }

    @Test(dependsOnMethods = arrayOf("grid should add header rows"))
    fun `header row should manage cell`() {
        val cellValue = "foo"
        val layout = horizontalLayout {
            grid(dataSource = GridTestData.Companion.container) {
                headerRowAtStart() {
                    cell("string").text = cellValue
                }
            }
        }
        val grid = layout.getGrid()
        assertThat(grid.getHeaderRow(0).getCell("string").text).isEqualTo(cellValue)
    }

    @Test
    fun `grid should add footer rows`() {
        val layout = horizontalLayout {
            grid(dataSource = GridTestData.Companion.container) {
                footerRowAtStart()
                footerRowAtEnd()
                footerRowAt(1)
            }
        }
        val grid = layout.getGrid()
        assertThat(grid.footerRowCount).isEqualTo(3)
    }

    @Test(dependsOnMethods = arrayOf("grid should add footer rows"))
    fun `footer row should manage cell`() {
        val cellValue = "foo"
        val layout = horizontalLayout {
            grid(dataSource = GridTestData.Companion.container) {
                footerRowAtStart() {
                    cell("string").text = cellValue
                }
            }
        }
        val grid = layout.getGrid()
        assertThat(grid.getFooterRow(0).getCell("string").text).isEqualTo(cellValue)
    }
}