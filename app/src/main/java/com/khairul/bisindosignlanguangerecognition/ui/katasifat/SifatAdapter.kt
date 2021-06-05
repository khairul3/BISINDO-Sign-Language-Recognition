package com.khairul.bisindosignlanguangerecognition.ui.katasifat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.khairul.bisindosignlanguangerecognition.data.firestore.FirestoreAdapter
import com.khairul.bisindosignlanguangerecognition.data.source.entity.Entity
import com.khairul.bisindosignlanguangerecognition.databinding.ItemRowsBinding

class SifatAdapter(
    query: Query,
    private val listener: SifatAdapterListener
) : FirestoreAdapter<SifatAdapter.SifatViewHolder>(query) {

    class SifatViewHolder(
        private val binding: ItemRowsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(snapshot: DocumentSnapshot, listener: SifatAdapterListener) {

            with(binding) {
                val sifat: Entity? = snapshot.toObject(Entity::class.java)
                val letter = sifat?.label

                txtTitle.text = letter
                Glide.with(itemView.context)
                    .load(sifat?.gambar.toString())
                    .into(imgSketsa)

                item.setOnClickListener {
                    listener.onSportSelected(sifat)
                }
            }
        }
    }

    interface SifatAdapterListener {
        fun onSportSelected(sifat: Entity?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SifatViewHolder {
        val itemRowsBinding =
            ItemRowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SifatViewHolder(itemRowsBinding)
    }

    override fun onBindViewHolder(holder: SifatViewHolder, position: Int) {
        getSnapshot(position)?.let { snapshot ->
            holder.bind(snapshot, listener)
        }
    }
}