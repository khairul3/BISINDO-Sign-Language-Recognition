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

class DictionaryAdapter(
    query: Query,
    private val listener: DictionaryAdapterListener
) : FirestoreAdapter<DictionaryAdapter.SportsViewHolder>(query) {

    class SportsViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val cardView: MaterialCardView = itemView.findViewById(R.id.item_letter)
        private val label: TextView = itemView.findViewById(R.id.txt_title)
        private val image: ImageView = itemView.findViewById(R.id.img_sketsa)

        fun bind(snapshot: DocumentSnapshot, listener: DictionaryAdapterListener) {
            val sports: Entity? = snapshot.toObject(Entity::class.java)
            val letter = "Huruf ${sports?.label}"

            label.text = letter
            Glide.with(image)
                .load(sports?.gambar.toString())
                .into(image)

            cardView.setOnClickListener {
                listener.onSportSelected(sports)
            }
        }
    }

    interface DictionaryAdapterListener {
        fun onSportSelected(sports: Entity?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsViewHolder {
        return SportsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_rows_dictionary, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SportsViewHolder, position: Int) {
        getSnapshot(position)?.let { snapshot ->
            holder.bind(snapshot, listener)
        }
    }
}