package com.example.iotsystemparking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.iotsystemparking.databinding.ActivityMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null){
            supportFragmentManager.commit{
                setReorderingAllowed(true)
                add<SlotFragment>(R.id.f_container)
            }
        }

        loadFragment(DataFragment())

        // definisi widget
        var bottomnav = findViewById<BottomNavigationView>(R.id.bottomnavview)
        bottomnav.setOnItemSelectedListener {

            when(it.itemId){

                R.id.bot_menu_dashboard -> {
                    loadFragment(DataFragment())
                    true
                }

                R.id.bot_menu_slot -> {
                    loadFragment(SlotFragment())
                    true
                }

                R.id.bot_menu_grafik -> {
                    loadFragment(GrafikFragment())
                    true
                }

                R.id.bot_menu_history -> {
                    loadFragment(PengunjungFragment())
                    true
                }

                else -> {false}
            }
        }
        binding.iv1.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.f_container,fragment)
        transaction.commit()
    }
}