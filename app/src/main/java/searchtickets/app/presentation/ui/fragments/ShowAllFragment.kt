package searchtickets.app.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import searchtickets.app.R
import searchtickets.app.databinding.FragmentShowAllBinding
import searchtickets.app.presentation.ui.adapters.ShowAllAdapter
import searchtickets.app.presentation.ui.adapters.ShowAllAdapterDataSource
import searchtickets.app.presentation.ui.adapters.ShowAllAdapterDataSourceImpl
import searchtickets.app.presentation.ui.utils.DateTimeConverterImpl
import searchtickets.app.presentation.ui.utils.LastValueManager
import searchtickets.app.presentation.ui.viewModels.ShowAllViewModel

@AndroidEntryPoint
class ShowAllFragment : Fragment(R.layout.fragment_show_all) {
    private var _binding: FragmentShowAllBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShowAllViewModel by activityViewModels()
    private lateinit var adapterDataSource: ShowAllAdapterDataSource
    private lateinit var adapter: ShowAllAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowAllBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dateTimeConverter = DateTimeConverterImpl()
        adapterDataSource = ShowAllAdapterDataSourceImpl(viewModel)
        adapter = ShowAllAdapter(adapterDataSource, dateTimeConverter)
        setupRecyclerView()
        viewModel.fetchData()
        viewModel.info.observe(viewLifecycleOwner) { tickets ->
            adapter.updateData(tickets)
        }
        initClickListener()
        restoreLastValue()
    }

    private fun restoreLastValue() {
        with(binding) {
            val lastDepartureValue = LastValueManager.getLastDepartureValue(requireContext())
            val lastArrivalValue = LastValueManager.getLastArrivalValue(requireContext())
            binding.wayFrom.text = "$lastDepartureValue-"
            binding.wayTo.text = lastArrivalValue
        }
    }


    private fun initClickListener() {
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_showAllFragment_to_searchFragment)
        }
    }

    private fun setupRecyclerView() {
        binding.showAll.adapter = adapter
        binding.showAll.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}