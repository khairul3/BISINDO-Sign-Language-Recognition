package com.khairul.bisindosignlanguangerecognition.ui.dictionary

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.khairul.bisindosignlanguangerecognition.data.source.entity.Entity
import com.khairul.bisindosignlanguangerecognition.databinding.ActivityDictionaryBinding
import com.khairul.bisindosignlanguangerecognition.ui.detail.DetailDictionaryActivity

class DictionaryActivity : AppCompatActivity(), DictionaryAdapter.DictionaryAdapterListener {

    private lateinit var adapter: DictionaryAdapter
    private lateinit var binding: ActivityDictionaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDictionaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showProgressBar(true)
        val actionbar = supportActionBar
        actionbar?.title = "ALFABET"
        actionbar?.setDisplayHomeAsUpEnabled(true)
        val query: Query = FirebaseFirestore.getInstance().collection("alfabet")

        adapter = DictionaryAdapter(query, this)
        with(binding) {
            rvDic.layoutManager = LinearLayoutManager(applicationContext)
            rvDic.setHasFixedSize(true)
            rvDic.adapter = adapter
            rvDic.layoutManager = GridLayoutManager(applicationContext, 2)
            showProgressBar(false)
        }
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.startListening()
    }

    override fun onSportSelected(entity: Entity?) {
        val intent = Intent(applicationContext, DetailDictionaryActivity::class.java)
        intent.putExtra("DETAIL_DATA", entity)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showProgressBar(state: Boolean) {
        with(binding) {
            progressBar.isVisible = state
            rvDic.isInvisible = state

        }

    }
}