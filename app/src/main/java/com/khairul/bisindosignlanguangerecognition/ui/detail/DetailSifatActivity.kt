package com.khairul.bisindosignlanguangerecognition.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.khairul.bisindosignlanguangerecognition.R
import com.khairul.bisindosignlanguangerecognition.data.source.entity.Entity
import com.khairul.bisindosignlanguangerecognition.databinding.ActivityDetailBinding

class DetailSifatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val entity: Entity? = intent.getParcelableExtra("DETAIL_DATA")
        val actionbar = supportActionBar
        val sif = entity?.label
        actionbar?.title = sif
        actionbar?.setDisplayHomeAsUpEnabled(true)

        with(binding) {
            tvTitle.text = sif
            tvDesc.text = entity?.keterangan
            Glide.with(this@DetailSifatActivity)
                .load(entity?.gambar.toString())
                .into(imgBackdrop)

        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}