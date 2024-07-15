package searchtickets.app.presentation.ui.utils

object PriceFormatter {
    fun formatPrice(price: Int): String {
        return price.toString().reversed().chunked(3)
            .asReversed()
            .joinToString(" ")
    }
}