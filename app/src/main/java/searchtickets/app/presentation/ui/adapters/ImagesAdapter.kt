package searchtickets.app.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import searchtickets.app.R
import searchtickets.app.databinding.InfoImagesBinding
import searchtickets.app.domain.models.Info
import searchtickets.app.presentation.ui.utils.PriceFormatter

class ImagesAdapter(
    private val dataSource: ImagesAdapterDataSource
) : ListDelegationAdapter<List<Info>>() {

    init {
        delegatesManager.addDelegate(ImageItemDelegate(dataSource))
    }

    fun updateData(newItems: List<Info>) {
        items = newItems
        notifyDataSetChanged()
    }
}

class ImageItemDelegate(
    private val dataSource: ImagesAdapterDataSource
) : AdapterDelegate<List<Info>>() {
    override fun isForViewType(items: List<Info>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = InfoImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(
        items: List<Info>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ImageViewHolder).bind(items[position])
    }

    class ImageViewHolder(
        private val binding: InfoImagesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Info) {
            with(binding) {
                name.text = item.title
                cityName.text = item.town
                cost.text = "от ${PriceFormatter.formatPrice(item.price)}₽"
                image.setImageResource(getImageResourceId(item.id))
            }
        }

        private fun getImageResourceId(id: Int): Int {
            return when (id) {
                1 -> R.drawable.first
                2 -> R.drawable.second
                3 -> R.drawable.third
                else -> R.drawable.first
            }
        }
    }
}

private val itemComparator = object : DiffUtil.ItemCallback<Info>() {
    override fun areItemsTheSame(oldItem: Info, newItem: Info): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Info, newItem: Info): Boolean {
        return oldItem == newItem
    }
}
