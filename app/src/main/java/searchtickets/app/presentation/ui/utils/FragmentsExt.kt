package searchtickets.app.presentation.ui.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

fun Fragment.switchFragment(fm: FragmentManager, @IdRes container: Int, fragment: Fragment) {
    val transaction = fm.beginTransaction()
    val tag = fragment.javaClass.simpleName
    val existingFragment = fm.findFragmentByTag(tag)

    if (existingFragment != null) {
        transaction.show(existingFragment)
    } else {
        transaction.add(container, fragment, tag)
    }

    for (addedFragment in fm.fragments) {
        if (addedFragment != existingFragment) {
            transaction.hide(addedFragment)
        }
    }

    transaction.addToBackStack(null)
    transaction.commit()
}

fun Fragment.replaceFragment(fm: FragmentManager, @IdRes container: Int, fragment: Fragment) {
    fm.commit {
        addToBackStack(null)
        replace(container, fragment)
    }
}