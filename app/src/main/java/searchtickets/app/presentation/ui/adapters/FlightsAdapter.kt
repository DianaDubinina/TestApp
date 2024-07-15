package searchtickets.app.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import searchtickets.app.databinding.InfoFlightsBinding
import searchtickets.app.domain.models.Flights
import searchtickets.app.presentation.ui.utils.PriceFormatter

class FlightsAdapter(
    private val dataSource: FlightsAdapterDataSource
) : ListDelegationAdapter<List<Flights>>() {

    init {
        delegatesManager.addDelegate(FlightItemDelegate(dataSource))
    }

    fun updateData(newData: List<Flights>) {
        items = newData
        notifyDataSetChanged()
    }
}

class FlightItemDelegate(
    private val dataSource: FlightsAdapterDataSource
) : AdapterDelegate<List<Flights>>() {
    override fun isForViewType(items: List<Flights>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = InfoFlightsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlightViewHolder(binding)
    }

    override fun onBindViewHolder(
        items: List<Flights>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as FlightViewHolder).bind(items[position])
    }

    class FlightViewHolder(
        private val binding: InfoFlightsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Flights) {
            with(binding) {
                companyName.text = item.title
                timeline.text = item.timeRange.joinToString(", ")
                price.text = "${PriceFormatter.formatPrice(item.price)}₽"
                // Set other views
            }
        }
    }
}

private val itemComparator = object : DiffUtil.ItemCallback<Flights>() {
    override fun areItemsTheSame(oldItem: Flights, newItem: Flights): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Flights, newItem: Flights): Boolean {
        return oldItem == newItem
    }
}