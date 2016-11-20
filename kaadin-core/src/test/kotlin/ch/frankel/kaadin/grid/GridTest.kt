package ch.frankel.kaadin.grid

import ch.frankel.kaadin.*
import ch.frankel.kaadin.grid.GridTestData.Companion.container
import com.vaadin.ui.*
import com.vaadin.ui.Grid.SelectionMode.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

@Test(groups = arrayOf("baseGrid"))
class GridTest {

    @Test
    fun `grid should be added to layout`() {
        val layout = horizontalLayout {
            grid()
        }
        assertThat(layout.componentCount).isEqualTo(1)
        val component = layout.getComponent(0)
        assertThat(component).isNotNull().isInstanceOf(Grid::class.java)
    }

    @Test(dependsOnMethods = arrayOf("grid should be added to layout"))
    fun `grid should display a specific caption`() {
        val caption = "Hello world"
        val layout = horizontalLayout {
            grid(caption)
        }
        val grid = layout.getGrid()
        assertThat(grid.caption).isEqualTo(caption)
    }

    @Test(dependsOnMethods = arrayOf("grid should be added to layout"))
    fun `grid should add data source as parameter`() {
        val layout = horizontalLayout {
            grid(dataSource = container)
        }
        val grid = layout.getGrid()
        assertThat(grid.columns.size).isEqualTo(4) // string + date + int + nested
    }

    @Test(dependsOnMethods = arrayOf("grid should be added to layout"))
    fun `grid should manage single column`() {
        val layout = horizontalLayout {
            grid(dataSource = container) {
                column("string").isHidden = true
            }
        }
        val grid = layout.getGrid()
        assertThat(grid.getStringColumn().isHidden).isEqualTo(true)
    }

    @Test(dependsOnMethods = arrayOf("grid should be added to layout"))
    fun `grid should manage multiple columns`() {
        val layout = horizontalLayout {
            grid(dataSource = container) {
                columns("string", "date") {
                    isHidden = true
                }
            }
        }
        val grid = layout.getGrid()
        assertThat(grid.getStringColumn().isHidden).isEqualTo(true)
        assertThat(grid.getDateColumn().isHidden).isEqualTo(true)
        assertThat(grid.getIntColumn().isHidden).isEqualTo(false)
        assertThat(grid.getNestedColumn().isHidden).isEqualTo(false)
    }

    @Test(dependsOnMethods = arrayOf("grid should be added to layout"))
    fun `grid should add cell style generator with simple syntax`() {
        val layout = horizontalLayout {
            grid(dataSource = container) {
                cellStyleGenerator("foo") { it.propertyId == "string" }
            }
        }
        val grid = layout.getGrid()
        assertThat(grid.cellStyleGenerator).isNotNull()
    }

    @Test(dependsOnMethods = arrayOf("grid should be added to layout"))
    fun `grid should add full-fledged cell style generator`() {
        val layout = horizontalLayout {
            grid(dataSource = container) {
                cellStyleGenerator { "foo" }
            }
        }
        val grid = layout.getGrid()
        assertThat(grid.cellStyleGenerator).isNotNull()
    }

    @Test(dependsOnMethods = arrayOf("grid should be added to layout"))
    fun `grid should add selection mode`() {
        val layout = horizontalLayout {
            grid(dataSource = container) {
                selectionMode(MULTI)
                this@grid.select(GridTestData.Companion.data[0])
                this@grid.select(GridTestData.Companion.data[1])
            }
        }
        val grid = layout.getGrid()
        assertThat(grid.selectedRows).hasSize(2)
    }
}
