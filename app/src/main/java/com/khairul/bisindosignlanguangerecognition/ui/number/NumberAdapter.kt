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

class NumberAdapter(
    query: Query,
    private val listener: NumberAdapterListener
) : FirestoreAdapter<NumberAdapter.SportsViewHolder>(query) {

    class SportsViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val cardView: MaterialCardView = itemView.findViewById(R.id.item_number)
        private val label: TextView = itemView.findViewById(R.id.txt_title)
        private val image: ImageView = itemView.findViewById(R.id.img_sketsa)

        fun bind(snapshot: DocumentSnapshot, listener: NumberAdapterListener) {
            val sports: Entity? = snapshot.toObject(Entity::class.java)

            val angka = "Angka ${sports?.label}"
            label.text = angka
            Glide.with(image)
                .load(sports?.gambar.toString())
                .into(image)

            cardView.setOnClickListener {
                listener.onSportSelected(sports)
            }
        }
    }

    interface NumberAdapterListener {
        fun onSportSelected(sports: Entity?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsViewHolder {
        return SportsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_rows_number, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SportsViewHolder, position: Int) {
        getSnapshot(position)?.let { snapshot ->
            holder.bind(snapshot, listener)
        }
    }
}
