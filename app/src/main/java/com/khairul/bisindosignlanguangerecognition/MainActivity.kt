package com.khairul.bisindosignlanguangerecognition

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.khairul.bisindosignlanguangerecognition.databinding.ActivityMain1Binding
import com.khairul.bisindosignlanguangerecognition.ui.CameraFragment
import com.khairul.bisindosignlanguangerecognition.ui.dictionary.DictionaryFragment
import com.khairul.bisindosignlanguangerecognition.ui.number.NumberFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMain1Binding

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> MainActivity()
                R.id.nav_camera -> loadFragment(CameraFragment())
            }
            true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)
        val navigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        binding.imgCam.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }

        binding.btnDictionary.setOnClickListener {
            val fragment: Fragment = DictionaryFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.rv_dic, fragment)
                .commit()
        }

        binding.btnNumber.setOnClickListener {
            val fragment: Fragment = NumberFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.rv_number, fragment)
                .commit()
        }

    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
            return true
        }
        return false
    }
}
