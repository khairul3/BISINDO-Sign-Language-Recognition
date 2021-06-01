package com.khairul.bisindosignlanguangerecognition.ui.dictionary

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

class DictionaryActivity : AppCompatActivity(), DictionaryAdapter.DictionaryAdapterListener {

    private lateinit var adapter: DictionaryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)
        val query: Query = FirebaseFirestore.getInstance().collection("alfabet")
        val recyclerView: RecyclerView = findViewById(R.id.rv_dic)
        adapter = DictionaryAdapter(query, this)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
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