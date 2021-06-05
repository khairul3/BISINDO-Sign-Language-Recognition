package com.khairul.bisindosignlanguangerecognition.ui.dictionary

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

class DictionaryAdapter(
    query: Query,
    private val listener: DictionaryAdapterListener
) : FirestoreAdapter<DictionaryAdapter.DictionaryViewHolder>(query) {

    class DictionaryViewHolder(
        private val binding: ItemRowsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(snapshot: DocumentSnapshot, listener: DictionaryAdapterListener) {

            with(binding) {
                val dictionary: Entity? = snapshot.toObject(Entity::class.java)
                val letter = "Huruf ${dictionary?.label}"

                txtTitle.text = letter
                Glide.with(itemView.context)
                    .load(dictionary?.gambar.toString())
                    .into(imgSketsa)

                item.setOnClickListener {
                    listener.onSportSelected(dictionary)
                }
            }
        }
    }

    interface DictionaryAdapterListener {
        fun onSportSelected(dictionary: Entity?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryViewHolder {
        val itemRowsBinding =
            ItemRowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DictionaryViewHolder(itemRowsBinding)
    }

    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        getSnapshot(position)?.let { snapshot ->
            holder.bind(snapshot, listener)
        }
    }
}