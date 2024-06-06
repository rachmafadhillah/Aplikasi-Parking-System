package com.example.iotsystemparking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.iotsystemparking.databinding.ActivityMenuBinding
import com.example.iotsystemparking.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val message = intent.getStringExtra("Nama")
        val message1 = intent.getStringExtra("NIM")
        val message2 = intent.getStringExtra("Email")

        val namalengkap = findViewById<TextView>(R.id.username).apply {

            text = message
        }

        val email = findViewById<TextView>(R.id.nim).apply {

            text = message1
        }

        val nim = findViewById<TextView>(R.id.email).apply {

            text = message2
        }

        binding.back.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        binding.profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.cbmkg.setOnClickListener {
            val intent = Intent(this, GempaActivity::class.java)
            startActivity(intent)
        }

        binding.cbmkgList.setOnClickListener {
            val intent = Intent(this, ListGempaActivity::class.java)
            startActivity(intent)
        }

        binding.dataPengguna.setOnClickListener {
            val intent = Intent(this, CrudActivity::class.java)
            startActivity(intent)
        }

        binding.tmbPengguna.setOnClickListener {
            val intent = Intent(this, PenggunaActivity::class.java)
            startActivity(intent)
        }

        binding.taplikasi.setOnClickListener {
            val intent = Intent(this, TentangActivity::class.java)
            startActivity(intent)
        }

        binding.logout.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    private fun showLogoutConfirmationDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Logout")
        alertDialog.setMessage("Are you sure you want to log out?")
        alertDialog.setPositiveButton("Yes") { _, _ ->
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        alertDialog.setNegativeButton("No") { _, _ ->

        }
        alertDialog.show()
    }
}