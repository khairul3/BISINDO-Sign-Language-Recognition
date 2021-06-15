package com.khairul.bisindosignlanguangerecognition.ui.katasifat

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
import com.khairul.bisindosignlanguangerecognition.databinding.ActivitySifatBinding
import com.khairul.bisindosignlanguangerecognition.ui.detail.DetailSifatActivity

class SifatActivity : AppCompatActivity(), SifatAdapter.SifatAdapterListener {

    private lateinit var adapter: SifatAdapter
    private lateinit var binding: ActivitySifatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySifatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showProgressBar(true)


        val actionbar = supportActionBar
        actionbar?.title = "KATA SIFAT"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val query: Query = FirebaseFirestore.getInstance().collection(getString(R.string.kata_sifat))

        adapter = SifatAdapter(query, this)
        with(binding) {
            rvSifat.layoutManager = LinearLayoutManager(applicationContext)
            rvSifat.setHasFixedSize(true)
            rvSifat.adapter = adapter
            rvSifat.layoutManager = GridLayoutManager(applicationContext, 2)
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
            rvSifat.isInvisible = state
        }

    }
}