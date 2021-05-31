package com.khairul.bisindosignlanguangerecognition.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.khairul.bisindosignlanguangerecognition.R

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NUMBER = "extra_number"
        const val EXTRA_LETTER = "extra_letter"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}