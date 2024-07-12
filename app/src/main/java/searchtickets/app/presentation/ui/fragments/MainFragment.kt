package searchtickets.app.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import dagger.hilt.android.AndroidEntryPoint
import searchtickets.app.R
import searchtickets.app.databinding.FragmentMainBinding
import searchtickets.app.presentation.ui.utils.FragmentsHolder
import searchtickets.app.presentation.ui.utils.switchFragment

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val fragmentHolder by lazy { FragmentsHolder() }
    private var selectedTabPosition: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.tabLayout.getTabAt(selectedTabPosition)?.select()
//        setupNavigation()
//    }
//
//    private fun setupNavigation() {
//
//        val tickets = getString(R.string.tickets)
//        val hotels = getString(R.string.hotels)
//        val shortly = getString(R.string.shortly)
//        val subscriptions = getString(R.string.subscriptions)
//        val profile = getString(R.string.profile)
//
//        fragmentHolder.apply {
//            add(tickets, TicketsFragment())
//            add(hotels, TicketsFragment())
//            add(shortly, TicketsFragment())
//            add(subscriptions, TicketsFragment())
//            add(profile, TicketsFragment())
//
//        }
//
//        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
//            val fm = childFragmentManager
//            val container = R.id.main_fragment_container
//
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                when (tab?.text) {
//
//                    tickets -> switchFragment(
//                        fm,
//                        container,
//                        fragmentHolder.get(tickets)
//                    )
//
//                    hotels -> switchFragment(
//                        fm,
//                        container,
//                        fragmentHolder.get(tickets)
//                    )
//
//                    shortly -> switchFragment(
//                        fm,
//                        container,
//                        fragmentHolder.get(tickets)
//                    )
//
//                    subscriptions -> switchFragment(
//                        fm,
//                        container,
//                        fragmentHolder.get(tickets)
//                    )
//                    profile -> switchFragment(
//                        fm,
//                        container,
//                        fragmentHolder.get(tickets)
//                    )
//                }
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//            }
//        })
//    }

    override fun onStop() {
        super.onStop()
//        selectedTabPosition = binding.tabLayout.selectedTabPosition
    }

    override fun onDestroyView() {
        super.onDestroyView()

        fragmentHolder.clear()
    }
}
