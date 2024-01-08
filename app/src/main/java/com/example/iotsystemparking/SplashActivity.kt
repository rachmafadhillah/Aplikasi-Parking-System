package com.example.iotsystemparking

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME:Long=3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_splash)

        val progressBar: ProgressBar = findViewById(R.id.progress)

        // Atur warna ProgressBar menjadi putih
        progressBar.indeterminateDrawable.setColorFilter(
            resources.getColor(android.R.color.white),
            PorterDuff.Mode.MULTIPLY
        )

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME)
    }
}