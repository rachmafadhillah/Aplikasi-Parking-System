package com.example.iotsystemparking

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.iotsystemparking.config.ConfigPendapatan
import com.example.iotsystemparking.model.ModelPendapatan
import com.example.iotsystemparking.model.ModelSlot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.os.Handler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DataFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var slot1: TextView
    private lateinit var slot2: TextView
    private lateinit var slotjml: TextView

    private var totalSlot1: Int = 0
    private var totalSlot2: Int = 0

    private val handler = Handler()
    private val scope = CoroutineScope(Dispatchers.Main+Job())

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
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_data, container, false)

        // Initialize your TextViews and other views
        slot1 = rootView.findViewById(R.id.isi_lt1)
        slot2 = rootView.findViewById(R.id.isi_lt2)
        slotjml = rootView.findViewById(R.id.isi_jml)

        // ... (repeat for other views)

        return rootView
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startAutoRefresh()

        // Your code remains mostly the same
        ConfigPendapatan().getSlot1Service()
            .getSlot1Data()
            .enqueue(object : Callback<ModelSlot> {
                override fun onResponse(call: Call<ModelSlot>, response: Response<ModelSlot>) {
                    Log.d("rachmafadhillah", "data json: " + response.body())

                    totalSlot1 = response.body()?.totalKendaraan ?: 0
                    updateTotalTextView()

                }

                override fun onFailure(call: Call<ModelSlot>, t: Throwable) {
                    Log.d("rachmafadhillah", "error: " + t.message.toString())
                }


            })

        ConfigPendapatan().getSlot2Service()
            .getSlot2Data()
            .enqueue(object : Callback<ModelSlot> {
                override fun onResponse(call: Call<ModelSlot>, response: Response<ModelSlot>) {
                    Log.d("rachmafadhillah", "data json: " + response.body())

                    totalSlot2 = response.body()?.totalKendaraan ?: 0
                    updateTotalTextView()

                }

                override fun onFailure(call: Call<ModelSlot>, t: Throwable) {
                    Log.d("rachmafadhillah", "error: " + t.message.toString())
                }


            })

    }

    // Function to update the total in TextView
    fun updateTotalTextView() {
        val total = totalSlot1 + totalSlot2
        slot1.text = "$totalSlot1"
        slot2.text = "$totalSlot2"
        slotjml.text = total.toString()
    }

    companion object {
        private const val updateInterval = 60000L
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DataFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DataFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}