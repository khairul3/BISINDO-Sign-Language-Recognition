package com.khairul.bisindosignlanguangerecognition

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.khairul.bisindosignlanguangerecognition.ui.dictionary.DictionaryActivity
import com.khairul.bisindosignlanguangerecognition.ui.katasifat.SifatActivity
import com.khairul.bisindosignlanguangerecognition.ui.number.NumberActivity
import com.khairul.bisindosignlanguangerecognition.ui.ucapan.UcapanActivity


class MainActivity : AppCompatActivity() {
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> MainActivity()
                R.id.nav_camera -> comingSoon()
                R.id.nav_lesson -> comingSoon()
            }
            true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val imgCam = findViewById<ImageView>(R.id.img_cam)
        val btnDictionary = findViewById<Button>(R.id.btn_dictionary)
        val btnNumber = findViewById<Button>(R.id.btn_number)
        val btnSapaan = findViewById<Button>(R.id.btn_sapaan)
        val btnkerja = findViewById<Button>(R.id.btn_sifat)


        imgCam.setOnClickListener {
           comingSoon()
        }

        btnDictionary.setOnClickListener {
            val intent = Intent(this, DictionaryActivity::class.java)
            startActivity(intent)
        }

        btnNumber.setOnClickListener {
            val intent = Intent(this, NumberActivity::class.java)
            startActivity(intent)
        }

        btnSapaan.setOnClickListener {
            val intent = Intent(this, UcapanActivity::class.java)
            startActivity(intent)
        }
        btnkerja.setOnClickListener {
            val intent = Intent(this, SifatActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadCam() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent)
    }

    private fun comingSoon() {
        val intent = Intent(this, ComingSoonActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_top, menu)
        return super.onCreateOptionsMenu(menu)
    }


}
