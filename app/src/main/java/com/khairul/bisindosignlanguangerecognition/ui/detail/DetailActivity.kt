package com.khairul.bisindosignlanguangerecognition.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.khairul.bisindosignlanguangerecognition.R
import com.khairul.bisindosignlanguangerecognition.data.source.entity.Entity

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NUMBER = "extra_number"
        const val EXTRA_LETTER = "extra_letter"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val entity: Entity? = intent.getParcelableExtra("SPORTS_DETAIL_DATA")
        val image: ImageView = findViewById(R.id.img_backdrop)
        findViewById<TextView>(R.id.tv_title).text = entity?.label
        findViewById<TextView>(R.id.tv_desc).text = entity?.keterangan
        Glide.with(this)
            .load(entity?.gambar.toString())
            .into(image)
    }
}