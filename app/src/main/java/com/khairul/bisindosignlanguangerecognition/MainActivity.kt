package com.khairul.bisindosignlanguangerecognition

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.khairul.bisindosignlanguangerecognition.ui.dictionary.DictionaryActivity
import com.khairul.bisindosignlanguangerecognition.ui.number.NumberActivity

class MainActivity : AppCompatActivity() {
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> MainActivity()
                R.id.nav_camera -> loadCam()
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

        imgCam.setOnClickListener {
            loadCam()
        }

        btnDictionary.setOnClickListener {
            val intent = Intent(this, DictionaryActivity::class.java)
            startActivity(intent)
        }

        btnNumber.setOnClickListener {
            val intent = Intent(this, NumberActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadCam() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent)
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerID, fragment)
                .commit()
            return true
        }
        return false
    }
}
