package searchtickets.app.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import searchtickets.app.R
import searchtickets.app.databinding.InfoPathesBinding
import searchtickets.app.domain.models.All
import searchtickets.app.presentation.ui.utils.DateTimeConverter
import searchtickets.app.presentation.ui.utils.PriceFormatter
import java.text.SimpleDateFormat
import java.util.Locale

class ShowAllAdapter(
    private val dataSource: ShowAllAdapterDataSource,
    private val dateTimeConverter: DateTimeConverter
) : ListAdapter<All, ShowAllAdapter.AllViewHolder>(itemComparator) {

    private var items: List<All> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllViewHolder {
        val binding = InfoPathesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AllViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun updateData(newItems: List<All>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class AllViewHolder(
        private val binding: InfoPathesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: All) {
            with(binding) {
                if (item.has_transfer == false) {
                    transplants.text = "Без пересадок"
                }
                if (item.badge.isNullOrEmpty()) {
                    badge.visibility = View.GONE
                } else {
                    badge.text = item.badge
                }
                val depDate = dateTimeConverter.convertDateTime(item.departure.date)
                val arrDate = dateTimeConverter.convertDateTime(item.arrival.date)
                val depDateTime = SimpleDateFormat("HH:mm", Locale.getDefault()).parse(depDate)
                val arrDateTime = SimpleDateFormat("HH:mm", Locale.getDefault()).parse(arrDate)
                val flightDuration = arrDateTime.time - depDateTime.time
                val flightDurationInMinutes = (flightDuration / 1000 / 60).toInt()
                val flightDurationHours = flightDurationInMinutes / 60
                val flightDurationMinutes = flightDurationInMinutes % 60
                val flightDurationText = "${flightDurationHours}ч ${flightDurationMinutes}мин"
                info.text = flightDurationText
                depCode.text = item.departure.airport
                arrCode.text = item.arrival.airport
                dateTo.text = arrDate
                dateFrom.text = "$depDate - "
                overallPrice.text = "${PriceFormatter.formatPrice(item.price.value)}₽"
                icFlight.setImageResource(getImageResourceId(item.id))
            }
        }

        @DrawableRes
        private fun getImageResourceId(id: Int): Int {
            return when (id % 3) {
                0 -> R.drawable.ic_red_rect
                1 -> R.drawable.ic_blue_rect
                2 -> R.drawable.ic_white_rect
                else -> R.drawable.ic_red_rect
            }
        }
    }
}

private val itemComparator = object : DiffUtil.ItemCallback<All>() {
    override fun areItemsTheSame(oldItem: All, newItem: All): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: All, newItem: All): Boolean {
        return oldItem == newItem
    }
}