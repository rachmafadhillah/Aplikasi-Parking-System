package com.example.iotsystemparking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.iotsystemparking.config.DataConfig
import com.example.iotsystemparking.databinding.ActivityGempaBinding
import com.example.iotsystemparking.databinding.ActivityProfileBinding
import com.example.iotsystemparking.model.ModelGempa
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GempaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGempaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGempaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        var tgl = findViewById<TextView>(R.id.tv_tanggal)
        var jam = findViewById<TextView>(R.id.tv_jam)
        var dtm = findViewById<TextView>(R.id.tv_datetime)
        var koo = findViewById<TextView>(R.id.tv_koordinat)
        var lin = findViewById<TextView>(R.id.tv_lintang)
        var buj = findViewById<TextView>(R.id.tv_bujur)
        var mag = findViewById<TextView>(R.id.tv_magnitude)
        var kdl = findViewById<TextView>(R.id.tv_kedalaman)
        var wlh = findViewById<TextView>(R.id.tv_wilayah)
        var pts = findViewById<TextView>(R.id.tv_potensi)
        var drs = findViewById<TextView>(R.id.tv_dirasakan)
        var shk = findViewById<ImageView>(R.id.foto_shakemap)

        DataConfig().getService()
            .getDataGempa()
            .enqueue(object : Callback<ModelGempa> {
                override fun onResponse(call: Call<ModelGempa>, response: Response<ModelGempa>) {
                    Log.d("rachmafadhillah", "data json: " + response.body())

                    //parsing
                    tgl.setText(response.body()?.infogempa?.gempa?.tanggal)
                    jam.setText(response.body()?.infogempa?.gempa?.jam)
                    dtm.setText(response.body()?.infogempa?.gempa?.dateTime)
                    koo.setText(response.body()?.infogempa?.gempa?.coordinates)
                    lin.setText(response.body()?.infogempa?.gempa?.lintang)
                    buj.setText(response.body()?.infogempa?.gempa?.bujur)
                    mag.setText(response.body()?.infogempa?.gempa?.magnitude)
                    kdl.setText(response.body()?.infogempa?.gempa?.kedalaman)
                    wlh.setText(response.body()?.infogempa?.gempa?.wilayah)
                    pts.setText(response.body()?.infogempa?.gempa?.potensi)
                    drs.setText(response.body()?.infogempa?.gempa?.dirasakan)
                    Picasso.get()
                        .load("https://data.bmkg.go.id/DataMKG/TEWS/"
                            + response.body()?.infogempa?.gempa?.shakemap).into(shk)
                }

                override fun onFailure(call: Call<ModelGempa>, t: Throwable) {
                    Log.d("rachmafadhillah", "error: " + t.message.toString())
                }

            })
    }
}