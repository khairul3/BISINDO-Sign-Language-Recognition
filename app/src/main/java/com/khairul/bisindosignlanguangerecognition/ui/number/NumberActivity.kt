package com.khairul.bisindosignlanguangerecognition.ui.number

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.khairul.bisindosignlanguangerecognition.R
import com.khairul.bisindosignlanguangerecognition.data.source.entity.Entity
import com.khairul.bisindosignlanguangerecognition.ui.detail.DetailActivity

class NumberActivity : AppCompatActivity(), NumberAdapter.NumberAdapterListener {

    private lateinit var adapter: NumberAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number)
        val query: Query = FirebaseFirestore.getInstance().collection("angka")
        val recyclerView: RecyclerView = findViewById(R.id.rv_number)
        adapter = NumberAdapter(query, this)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)

    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.startListening()
    }

    override fun onSportSelected(sports: Entity?) {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra("SPORTS_DETAIL_DATA", sports)
        startActivity(intent)
    }
}