package com.khairul.bisindosignlanguangerecognition

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ComingSoonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coming_soon_activity)
        val actionbar = supportActionBar
        actionbar!!.title = "Coming soon feature"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}