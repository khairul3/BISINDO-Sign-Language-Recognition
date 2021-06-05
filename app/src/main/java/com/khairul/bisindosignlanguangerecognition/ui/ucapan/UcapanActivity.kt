package com.khairul.bisindosignlanguangerecognition.ui.ucapan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.khairul.bisindosignlanguangerecognition.R
import com.khairul.bisindosignlanguangerecognition.data.source.entity.Entity
import com.khairul.bisindosignlanguangerecognition.ui.detail.DetailSifatActivity
import com.khairul.bisindosignlanguangerecognition.ui.katasifat.SifatAdapter

class UcapanActivity : AppCompatActivity(), SifatAdapter.SifatAdapterListener {

    private lateinit var adapter: SifatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ucapan)

        val actionbar = supportActionBar
        actionbar?.title = "KATA SAPA"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val query: Query = FirebaseFirestore.getInstance().collection("Ucapan")
        val recyclerView: RecyclerView = findViewById(R.id.rv_ucapan)

        adapter = SifatAdapter(query, this)
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

    override fun onSportSelected(sifat: Entity?) {
        val intent = Intent(applicationContext, DetailSifatActivity::class.java)
        intent.putExtra("DETAIL_DATA", sifat)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}