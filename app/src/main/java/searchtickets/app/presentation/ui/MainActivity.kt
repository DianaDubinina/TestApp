package searchtickets.app.presentation.ui

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import searchtickets.app.R


@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigation()
        setupBottomNav()
        setupBackPressed()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setupBottomNav() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)

        val menuItemToFragmentMap = mapOf(
            R.id.tickets to R.id.ticketsFragment,
            R.id.hotels to R.id.emptyFragment,
            R.id.shortly to R.id.emptyFragment,
            R.id.subscriptions to R.id.emptyFragment,
            R.id.profile to R.id.emptyFragment
        )
        bottomNavigationView.setOnItemSelectedListener { item ->
            val destinationId = menuItemToFragmentMap[item.itemId]
            if (destinationId != null) {
                navController.navigate(destinationId)
                true
            } else {
                false
            }
        }
    }

    private fun setupBackPressed() {
        onBackPressedDispatcher.addCallback(this) {
            if (navController.currentDestination?.id == R.id.ticketsFragment) {
                MaterialAlertDialogBuilder(this@MainActivity)
                    .setIcon(R.drawable.ic_launcher_background)
                    .setTitle(R.string.exit)
                    .setMessage(R.string.question_exit)
                    .setPositiveButton(R.string.possitive) { _, _ -> finish() }
                    .setNegativeButton(R.string.negative, null)
                    .show()
            } else {
                navController.popBackStack()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}