package com.khairul.bisindosignlanguangerecognition.ui.number

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.khairul.bisindosignlanguangerecognition.R
import com.khairul.bisindosignlanguangerecognition.data.firestore.FirestoreAdapter
import com.khairul.bisindosignlanguangerecognition.data.source.entity.Entity
import com.khairul.bisindosignlanguangerecognition.databinding.ItemRowsBinding
import com.khairul.bisindosignlanguangerecognition.ui.katasifat.SifatAdapter

class NumberAdapter(
    query: Query,
    private val listener: NumberAdapterListener
) : FirestoreAdapter<NumberAdapter.NumberViewHolder>(query) {

    class NumberViewHolder(
        private val binding: ItemRowsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(snapshot: DocumentSnapshot, listener: NumberAdapterListener) {

            with(binding) {
                val number: Entity? = snapshot.toObject(Entity::class.java)
                val letter = "Angka ${number?.label}"

                txtTitle.text = letter
                Glide.with(itemView.context)
                    .load(number?.gambar.toString())
                    .into(imgSketsa)

                item.setOnClickListener {
                    listener.onSportSelected(number)
                }
            }
        }
    }

    interface NumberAdapterListener {
        fun onSportSelected(sports: Entity?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val itemRowsBinding =
            ItemRowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NumberViewHolder(itemRowsBinding)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        getSnapshot(position)?.let { snapshot ->
            holder.bind(snapshot, listener)
        }
    }
}
