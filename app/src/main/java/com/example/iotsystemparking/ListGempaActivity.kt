package com.example.iotsystemparking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.example.iotsystemparking.adapter.AdapterBuatanSaya
import com.example.iotsystemparking.config.DataConfig2
import com.example.iotsystemparking.databinding.ActivityGempaBinding
import com.example.iotsystemparking.databinding.ActivityListGempaBinding
import com.example.iotsystemparking.model.ModelListGempa
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListGempaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListGempaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListGempaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        val _listview = findViewById<ListView>(R.id.list_gempa)

        DataConfig2().getService()
            .getgempa()
            .enqueue(object : Callback<ModelListGempa>{
                override fun onResponse(
                    call: Call<ModelListGempa>,
                    response: Response<ModelListGempa>
                ) {
                    Log.d("rachmafadhillah", "data json: " + response.body())
                    _listview.adapter = AdapterBuatanSaya(response.body(), this@ListGempaActivity,
                        response.body()?.infogempa?.gempa!!
                    )
                }

                override fun onFailure(call: Call<ModelListGempa>, t: Throwable) {
                    Log.d("rachmafadhillah", "error: " + t.message.toString())
                }
            })
    }
}