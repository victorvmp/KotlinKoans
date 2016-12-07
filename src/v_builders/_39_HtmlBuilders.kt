package v_builders

import util.TODO
import util.doc39
import v_builders.data.Product
import v_builders.data.getProducts
import v_builders.htmlLibrary.*

fun getTitleColor() = "#b9c9fe"
fun getCellColor(row: Int, column: Int) = if ((row + column) % 2 == 0) "#dce4ff" else "#eff2ff"

fun todoTask39(): Nothing = TODO(
        """
        Task 39.
        1) Fill the table with the proper values from products.
        2) Color the table like a chess board (using getTitleColor() and getCellColor() functions above).
        Pass a color as an argument to functions 'tr', 'td'.
        You can run the 'Html Demo' configuration in IntelliJ IDEA to see the rendered table.
    """,
        documentation = doc39()
)

fun renderProductTable(): String {
    return html {
        table {
            tr(getTitleColor()) {
                td(color = getCellColor(0, 0)) {
                    text("Product")
                }
                td(color = getCellColor(0, 1)) {
                    text("Price")
                }
                td(color = getCellColor(0, 2)) {
                    text("Popularity")
                }
            }
            val products = getProducts()

            for ((index, product) in products.withIndex()) {

                table {
                    tr {
                        td(color = getCellColor(index, 0)) {
                            text(product.description)
                        }
                        td(color = getCellColor(index, 1)) {
                            text(product.price)
                        }
                        td(color = getCellColor(index, 2)) {
                            text(product.popularity)
                        }
                    }
                }
            }
        }
    }.toString()
}
