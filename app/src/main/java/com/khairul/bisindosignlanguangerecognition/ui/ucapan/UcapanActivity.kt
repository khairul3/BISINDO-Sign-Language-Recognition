package com.khairul.bisindosignlanguangerecognition.ui.ucapan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.khairul.bisindosignlanguangerecognition.R
import com.khairul.bisindosignlanguangerecognition.data.source.entity.Entity
import com.khairul.bisindosignlanguangerecognition.databinding.ActivityUcapanBinding
import com.khairul.bisindosignlanguangerecognition.ui.detail.DetailSifatActivity

class UcapanActivity : AppCompatActivity(), UcapanAdapter.UcapanAdapterListener {

    private lateinit var adapter: UcapanAdapter
    private lateinit var binding: ActivityUcapanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUcapanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showProgressBar(true)

        val actionbar = supportActionBar
        actionbar?.title = "KATA SAPA"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val query: Query = FirebaseFirestore.getInstance().collection(getString(R.string.ucapan))
        adapter = UcapanAdapter(query, this)

        with(binding) {
            rvUcapan.layoutManager = LinearLayoutManager(applicationContext)
            rvUcapan.setHasFixedSize(true)
            rvUcapan.adapter = adapter
            rvUcapan.layoutManager = GridLayoutManager(applicationContext, 2)
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

    override fun onSportSelected(sifat: Entity?) {
        val intent = Intent(applicationContext, DetailSifatActivity::class.java)
        intent.putExtra("DETAIL_DATA", sifat)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showProgressBar(state: Boolean) {
        with(binding) {
            progressBar.isVisible = state
            rvUcapan.isInvisible = state
        }

    }
}