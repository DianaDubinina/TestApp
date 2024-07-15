package searchtickets.app.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import searchtickets.app.R
import searchtickets.app.databinding.FragmentEmptyBinding
import searchtickets.app.presentation.ui.utils.FragmentsHolder

@AndroidEntryPoint
class EmptyFragment : Fragment(R.layout.fragment_empty) {
    private var _binding: FragmentEmptyBinding? = null
    private val binding get() = _binding!!
    private val fragmentHolder by lazy { FragmentsHolder() }
    private val navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmptyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
    }

    private fun initClickListener() {
        binding.backButt.setOnClickListener {
            navController.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentHolder.clear()
        _binding = null
    }
}