package com.khairul.bisindosignlanguangerecognition.ui.number

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.khairul.bisindosignlanguangerecognition.R
import com.khairul.bisindosignlanguangerecognition.data.source.entity.Entity
import com.khairul.bisindosignlanguangerecognition.databinding.ActivityNumberBinding
import com.khairul.bisindosignlanguangerecognition.databinding.ActivitySifatBinding
import com.khairul.bisindosignlanguangerecognition.ui.detail.DetailNumberActivity

class NumberActivity : AppCompatActivity(), NumberAdapter.NumberAdapterListener {

    private lateinit var adapter: NumberAdapter
    private lateinit var binding:ActivityNumberBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showProgressBar(true)

        val actionbar = supportActionBar
        actionbar?.title = "ANGKA"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val query: Query = FirebaseFirestore.getInstance().collection(getString(R.string.angka))
        adapter = NumberAdapter(query, this)

        with(binding){
        rvNumber.layoutManager = LinearLayoutManager(applicationContext)
        rvNumber.setHasFixedSize(true)
        rvNumber.adapter = adapter
        rvNumber.layoutManager = GridLayoutManager(applicationContext, 2)
        showProgressBar(false)}
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
        val intent = Intent(applicationContext, DetailNumberActivity::class.java)
        intent.putExtra("DETAIL_DATA", sports)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showProgressBar(state: Boolean) {
        with(binding) {
            progressBar.isVisible = state
            rvNumber.isInvisible = state
        }

    }
}