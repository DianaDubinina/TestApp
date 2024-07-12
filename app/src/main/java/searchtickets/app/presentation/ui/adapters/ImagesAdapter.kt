package searchtickets.app.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import searchtickets.app.R
import searchtickets.app.databinding.InfoImagesBinding
import searchtickets.app.domain.models.Info

class ImagesAdapter(
    private val dataSource: ImagesAdapterDataSource
) : ListAdapter<Info, ImagesAdapter.ImagesViewHolder>(itemComparator) {

    private var items: List<Info> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = InfoImagesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun updateData(newItems: List<Info>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class ImagesViewHolder(
        private val binding: InfoImagesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Info) {
            with(binding) {
                name.text = item.title
                cityName.text = item.town
                cost.text = "[${item.price}]"
                image.setImageResource(getImageResourceId(item.id))
            }
        }

        @DrawableRes
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