package searchtickets.app.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import searchtickets.app.R
import searchtickets.app.databinding.InfoFlightsBinding
import searchtickets.app.domain.models.Flights


class FllightsAdapter(
    private val dataSource: FlightsAdapterDataSource
) : ListAdapter<Flights, FllightsAdapter.FlightsViewHolder>(itemComparator) {

    private var items: List<Flights> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightsViewHolder {
        val binding = InfoFlightsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FlightsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlightsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun updateData(newItems: List<Flights>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class FlightsViewHolder(
        private val binding: InfoFlightsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Flights) {
            with(binding) {
                companyName.text = item.title
                timeline.text = item.time_range
                price.text = "[${item.price}]"
                icFlight.setImageResource(getImageResourceId(item.id))
            }
        }

        @DrawableRes
        private fun getImageResourceId(id: Int): Int {
            return when (id) {
                1 -> R.drawable.ic_red_rect
                2 -> R.drawable.ic_blue_rect
                3 -> R.drawable.ic_white_rect
                else -> R.drawable.ic_red_rect
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