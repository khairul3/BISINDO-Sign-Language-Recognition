package com.khairul.bisindosignlanguangerecognition.ui.number

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khairul.bisindosignlanguangerecognition.data.source.entity.NumberEntity
import com.khairul.bisindosignlanguangerecognition.databinding.ItemRowsNumberBinding
import java.util.ArrayList

class NumberAdapter : RecyclerView.Adapter<NumberAdapter.NumberViewHolder>() {
    private var listData = ArrayList<NumberEntity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NumberAdapter.NumberViewHolder {
        val itemRowBinding = ItemRowsNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NumberViewHolder(itemRowBinding)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class NumberViewHolder(private val binding: ItemRowsNumberBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(number: NumberEntity) {
            with(binding) {
                txtTitle.text = number.number
                Glide.with(itemView.context)
                    .load(number.image)
                    .into(imgSketsa)
            }
        }

//        init {
//            binding.root.setOnClickListener {
//                onItemClick?.invoke(listData[adapterPosition])
//            }
    }
}
