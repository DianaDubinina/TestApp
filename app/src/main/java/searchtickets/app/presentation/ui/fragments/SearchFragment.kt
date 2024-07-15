package searchtickets.app.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import searchtickets.app.R
import searchtickets.app.databinding.FragmentSearchBinding
import searchtickets.app.presentation.ui.adapters.FlightsAdapter
import searchtickets.app.presentation.ui.adapters.FlightsAdapterDataSource
import searchtickets.app.presentation.ui.adapters.FlightsAdapterDataSourceImpl
import searchtickets.app.presentation.ui.utils.AppTextWatcher
import searchtickets.app.presentation.ui.utils.DatePickerManager
import searchtickets.app.presentation.ui.utils.FragmentsHolder
import searchtickets.app.presentation.ui.utils.LastValueManager
import searchtickets.app.presentation.ui.viewModels.FlightsViewModel

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val fragmentHolder by lazy { FragmentsHolder() }
    private lateinit var adapterDataSource: FlightsAdapterDataSource

    private val viewModel: FlightsViewModel by activityViewModels()
    private lateinit var adapter: FlightsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.fetchData()
        updateData()
        initUi()
    }

    private fun initUi() {
        configureTextFields()
        saveLastDepartureValue()
        setupDatePickers()
        navigateToAllFragment()
    }

    private fun setupDatePickers() {
        val datePickerManager = DatePickerManager(
            requireContext(),
            binding.dateDeparture,
            binding.dateBack
        )
        datePickerManager.setupDatePicker()
        datePickerManager.initDateBackButton()
    }

    private fun saveLastDepartureValue() {
        with(binding) {
            val sharedPrefs = context?.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            val lastDepartureValue = sharedPrefs?.getString("last_departure_value", "Москва")
            val lastArrivalValue = LastValueManager.getLastArrivalValue(requireContext())
            fromCityEdit.setText(lastDepartureValue)
            toCityEdit.setText(lastArrivalValue)
            fromCityEdit.addTextChangedListener(AppTextWatcher { editable ->
                sharedPrefs?.edit()?.putString("last_departure_value", editable.toString())?.apply()
            })
            toCityEdit.addTextChangedListener(AppTextWatcher { editable ->
                LastValueManager.saveLastArrivalValue(requireContext(), editable.toString())
            })
            binding.changeButt.setOnClickListener {
                val fromText = fromCityEdit.text.toString()
                val toText = toCityEdit.text.toString()
                fromCityEdit.setText(toText)
                toCityEdit.setText(fromText)
            }
        }
    }

    private fun navigateToAllFragment() {
        binding.showAll.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_showAllFragment)
        }
    }

    private fun configureTextFields() {
        with(binding) {
            fromCityEdit.inputType =
                InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS or InputType.TYPE_TEXT_VARIATION_PERSON_NAME
            toCityEdit.inputType =
                InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS or InputType.TYPE_TEXT_VARIATION_PERSON_NAME
        }
    }

    private fun updateData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.info.observe(viewLifecycleOwner) { newItems ->
                adapter.updateData(newItems)
            }
        }
    }

    private fun setupRecyclerView() {
        adapterDataSource = FlightsAdapterDataSourceImpl(viewModel)
        adapter = FlightsAdapter(adapterDataSource)
        binding.flightsRecycler.adapter = adapter
        binding.flightsRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentHolder.clear()
        _binding = null
    }
}
