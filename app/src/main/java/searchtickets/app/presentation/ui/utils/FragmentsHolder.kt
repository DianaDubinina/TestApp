package searchtickets.app.presentation.ui.utils

import androidx.fragment.app.Fragment

class FragmentsHolder {
    private val holder = mutableMapOf<String, Fragment>()

    fun add(str: String, fragment: Fragment) {
        holder[str] = fragment
    }

    fun get(str: String): Fragment {
        return holder.getValue(str)
    }

    fun clear() {
        holder.clear()
    }
}
