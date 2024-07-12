package searchtickets.app.presentation.ui.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import searchtickets.app.R
import searchtickets.app.databinding.FragmentSearchBinding
import searchtickets.app.presentation.ui.adapters.FlightsAdapterDataSource
import searchtickets.app.presentation.ui.adapters.FlightsAdapterDataSourceImpl
import searchtickets.app.presentation.ui.adapters.FllightsAdapter
import searchtickets.app.presentation.ui.utils.FragmentsHolder
import searchtickets.app.presentation.ui.viewModels.FlightsViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val fragmentHolder by lazy { FragmentsHolder() }
    private lateinit var adapterDataSource: FlightsAdapterDataSource

    private val viewModel: FlightsViewModel by activityViewModels()
    private lateinit var adapter: FllightsAdapter

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
        viewModel.fetchData(requireContext())
        updateData()
        initUi()
    }

    private fun initUi() {
        configureTextFields()
        showAdditionalInfo()
        saveLastDepartureValue()
    }

    private fun saveLastDepartureValue() {
        with(binding) {
            val sharedPrefs = context?.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            val lastDepartureValue = sharedPrefs?.getString("last_departure_value", "Москва")
            fromCityEdit.setText(lastDepartureValue)

            fromCityEdit.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    sharedPrefs?.edit()?.putString("last_departure_value", s.toString())?.apply()
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }

    private fun showAdditionalInfo() {
        binding.toCityEdit.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
                .setTitle("Information")
                .setMessage("Тут может быть инфо о дате и времени отправления")
                .setPositiveButton("OK") { _, _ -> }
                .create()
            dialog.show()
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
        adapter = FllightsAdapter(adapterDataSource)
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