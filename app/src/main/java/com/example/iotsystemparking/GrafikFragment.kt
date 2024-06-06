package com.example.iotsystemparking

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.db.williamchart.view.BarChartView
import com.example.iotsystemparking.config.ConfigPendapatan
import com.example.iotsystemparking.databinding.FragmentGrafikBinding
import com.example.iotsystemparking.model.ModelAverage
import com.example.iotsystemparking.model.ModelSlot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GrafikFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GrafikFragment : Fragment() {
    private var _binding: FragmentGrafikBinding? = null
    private val binding get() = _binding!!

    private val lineSet = mutableListOf<Pair<String, Float>>()
    private val barSet = mutableListOf<Pair<String, Float>>()

    private val handler = Handler()
    private val scope = CoroutineScope(Dispatchers.Main+ Job())

    private var param1: String? = null
    private var param2: String? = null

    private val animationDuration = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGrafikBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCharts()
        fetchDataFromApi()
        startAutoRefresh()
    }

    private fun startAutoRefresh() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                scope.launch {
                    startAutoRefresh()
                }
            }
        }, updateInterval)
    }

    private fun setupCharts() {
        binding.apply {
            barChart.animation.duration = animationDuration
            barChart.animate(barSet)
            lineChart.gradientFillColors = intArrayOf(
                Color.parseColor("#64B5F6"),
                Color.TRANSPARENT
            )
            lineChart.animation.duration = animationDuration
            lineChart.animate(lineSet)
            lineChart.onDataPointTouchListener = { index, _, _ ->
                tvChartData.text = lineSet.toList()[index].second.toString()
            }
        }
    }

    private fun fetchDataFromApi() {
        // Assuming you have a Retrofit service for fetching earthquake data
        // Make sure to replace ApiService and getDataGempa with your actual Retrofit service
        ConfigPendapatan().getAverageService()
            .getAverageData()
            .enqueue(object : Callback<ModelAverage> {
                override fun onResponse(
                    call: Call<ModelAverage>,
                    response: Response<ModelAverage>
                ) {
                    if (response.isSuccessful) {
                        parseDataLine(response.body())
                        updateCharts()
                    }
                }

                override fun onFailure(call: Call<ModelAverage>, t: Throwable) {

                }

            })

        // Your code for getSlot1Service remains the same
        ConfigPendapatan().getSlot1Service()
            .getSlot1Data()
            .enqueue(object : Callback<ModelSlot> {
                override fun onResponse(call: Call<ModelSlot>, response: Response<ModelSlot>) {
                    Log.d("rachmafadhillah", "data json: " + response.body())

                    updateBarChart(0, response.body()?.totalKendaraan ?: 0)
                }

                override fun onFailure(call: Call<ModelSlot>, t: Throwable) {
                    Log.d("rachmafadhillah", "error: " + t.message.toString())
                }
            })

        // Your code for getSlot2Service remains the same
        ConfigPendapatan().getSlot2Service()
            .getSlot2Data()
            .enqueue(object : Callback<ModelSlot> {
                override fun onResponse(call: Call<ModelSlot>, response: Response<ModelSlot>) {
                    Log.d("rachmafadhillah", "data json: " + response.body())

                    updateBarChart(0, response.body()?.totalKendaraan ?: 0)
                }

                override fun onFailure(call: Call<ModelSlot>, t: Throwable) {
                    Log.d("rachmafadhillah", "error: " + t.message.toString())
                }
            })
    }

    private fun parseDataLine(modelAverage: ModelAverage?) {
        modelAverage?.let {
            it.data?.let { data ->
                lineSet.apply {
                    clear()
                    add("Senin" to (data.senin ?: 0).toFloat())
                    add("Selasa" to (data.selasa ?: 0).toFloat())
                    add("Rabu" to (data.rabu ?: 0).toFloat())
                    add("Kamis" to (data.kamis ?: 0).toFloat())
                    add("Jumat" to (data.jumat ?: 0).toFloat())
                    add("Sabtu" to (data.sabtu ?: 0).toFloat())
                    add("Minggu" to (data.minggu ?: 0).toFloat())
                }
            }
        }
    }

    private fun updateBarChart(slot1Value: Int, slot2Value: Int) {
        val entries = linkedMapOf<String, Float>()
        entries["Mobil"] = slot1Value.toFloat()
        entries["Motor"] = slot2Value.toFloat()

        val barChartView = binding.barChart  // Replace 'barChart' with the actual name of your bar chart view

        barChartView.show(entries)
    }

    private fun updateCharts() {
        binding.apply {
            // Add new data
            lineChart.animate(lineSet)
            barChart.animate(barSet)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val updateInterval = 60000L

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GrafikFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GrafikFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
