package com.example.iotsystemparking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iotsystemparking.databinding.ActivityProfileBinding
import com.example.iotsystemparking.databinding.ActivitySettingBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        binding.btnEdit.setOnClickListener {
            callActivity()
        }
    }

    private fun callActivity(){
        val message = binding.nama2Profile.text.toString()
        val message1 = binding.nim2Profile.text.toString()
        val message2 = binding.email2Profile.text.toString()

        val intent = Intent(this, SettingActivity::class.java).also {
            it.putExtra("Nama", message)
            it.putExtra("NIM", message1)
            it.putExtra("Email", message2)
            startActivity(it)
        }
    }
}