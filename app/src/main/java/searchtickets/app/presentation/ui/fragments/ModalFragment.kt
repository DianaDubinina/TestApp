package searchtickets.app.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import searchtickets.app.R
import searchtickets.app.databinding.FragmentModalBinding
import searchtickets.app.presentation.ui.adapters.ImagesAdapter
import searchtickets.app.presentation.ui.adapters.ImagesAdapterDataSource
import searchtickets.app.presentation.ui.utils.FragmentsHolder
import searchtickets.app.presentation.ui.viewModels.InfoViewModel

class ModalFragment : Fragment(R.layout.fragment_modal) {
    private var _binding: FragmentModalBinding? = null
    private val binding get() = _binding!!
    private val fragmentHolder by lazy { FragmentsHolder() }
    private lateinit var adapterDataSource: ImagesAdapterDataSource

    private val viewModel: InfoViewModel by activityViewModels()
    private lateinit var adapter: ImagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupRecyclerView()
        viewModel.fetchData(requireContext())
        updateData()
        initUi()
    }

    private fun initUi() {
        clickListener()

    }

    private fun clickListener() {
        binding.closeButton.setOnClickListener {
            binding.toCityEdit.setText("")
        }
    }

    private fun updateData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.info.observe(viewLifecycleOwner) { newItems ->
                adapter.updateData(newItems)
            }
        }
    }

//    private fun setupRecyclerView() {
//        adapterDataSource = ImagesAdapterDataSourceImpl(viewModel)
//        adapter = ImagesAdapter(adapterDataSource)
//        binding.adsRecycler.adapter = adapter
//        binding.adsRecycler.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentHolder.clear()
        _binding = null
    }
}