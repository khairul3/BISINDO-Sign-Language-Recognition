package com.khairul.bisindosignlanguangerecognition.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.khairul.bisindosignlanguangerecognition.data.source.entity.Entity
import com.khairul.bisindosignlanguangerecognition.databinding.ActivityDetailBinding
import com.skydoves.transformationlayout.TransformationAppCompatActivity

class DetailSifatActivity : TransformationAppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showProgressBar(true)
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
            showProgressBar(false)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showProgressBar(state: Boolean) {
        with(binding) {
            progressBar.isVisible = state
            imgBackdrop.isInvisible = state
            cardView.isInvisible = state
        }
    }

}