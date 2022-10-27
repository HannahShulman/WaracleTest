package com.hanna.waracle.test.presenter.ui.cakelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hanna.waracle.test.databinding.ItemCakeBinding
import com.hanna.waracle.test.domain.models.entities.CakeItem
import com.hanna.waracle.test.presenter.ui.BindableViewHolder
import com.hanna.waracle.test.utils.di.ImageModule
import com.hanna.waracle.test.utils.di.ImageModule_ProvideImageLoaderFactory.provideImageLoader

class CakesRecyclerViewAdapter(private val onItemClick: (description: CakeItem) -> Unit) :
    ListAdapter<CakeItem, BindableViewHolder<CakeItem>>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CakeViewHolder {

        val binding: ItemCakeBinding =
            ItemCakeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CakeViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: BindableViewHolder<CakeItem>, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object :
            DiffUtil.ItemCallback<CakeItem>() {
            override fun areItemsTheSame(
                oldItem: CakeItem,
                newItem: CakeItem
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: CakeItem,
                newItem: CakeItem
            ): Boolean {
                return oldItem.title == newItem.title && oldItem.description == newItem.description && oldItem.url == newItem.url
            }
        }
    }

    inner class CakeViewHolder(
        private val binding: ItemCakeBinding, val onItemClick: (description: CakeItem) -> Unit
    ) : BindableViewHolder<CakeItem>(binding.root) {

        var imageLoader = provideImageLoader(ImageModule())

        init {
            binding.itemCakeTitle.setOnClickListener {
                onItemClick(currentList[adapterPosition])
            }
        }

        override fun bind(data: CakeItem) {
            imageLoader.loadImage(data.url, binding.itemCakeImage)

            //accessibility
            binding.itemCakeImage.contentDescription = "Image of ${data.title}"
            binding.itemCakeTitle.text = data.title
        }
    }
}

