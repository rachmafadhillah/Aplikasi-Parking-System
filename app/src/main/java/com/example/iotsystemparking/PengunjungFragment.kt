package com.example.iotsystemparking

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import com.example.iotsystemparking.adapter.AdapterBuatanSaya
import com.example.iotsystemparking.config.ConfigPendapatan
import com.example.iotsystemparking.config.DataConfig2
import com.example.iotsystemparking.model.ModelAverage
import com.example.iotsystemparking.model.ModelListGempa
import com.example.iotsystemparking.model.ModelSlot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PengunjungFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PengunjungFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var senin: TextView
    private lateinit var selasa: TextView
    private lateinit var rabu: TextView
    private lateinit var kamis: TextView
    private lateinit var jumat: TextView
    private lateinit var sabtu: TextView
    private lateinit var minggu: TextView

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
        val rootView = inflater.inflate(R.layout.fragment_pengunjung, container, false)

        // Initialize your TextViews and other views
        senin = rootView.findViewById(R.id.tv_senin)
        selasa = rootView.findViewById(R.id.tv_selasa)
        rabu = rootView.findViewById(R.id.tv_rabu)
        kamis = rootView.findViewById(R.id.tv_kamis)
        jumat = rootView.findViewById(R.id.tv_jumat)
        sabtu = rootView.findViewById(R.id.tv_sabtu)
        minggu = rootView.findViewById(R.id.tv_minggu)

        // ... (repeat for other views)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Your code remains mostly the same
        ConfigPendapatan().getAverageService()
            .getAverageData()
            .enqueue(object : Callback<ModelAverage> {
                override fun onResponse(call: Call<ModelAverage>, response: Response<ModelAverage>) {
                    Log.d("rachmafadhillah", "data json: " + response.body())

                    senin.setText(response.body()?.data?.senin)
                    selasa.setText(response.body()?.infogempa?.gempa?.jam)
                    rabu.setText(response.body()?.infogempa?.gempa?.dateTime)
                    kamis.setText(response.body()?.infogempa?.gempa?.coordinates)
                    jumat.setText(response.body()?.infogempa?.gempa?.lintang)
                    sabtu.setText(response.body()?.infogempa?.gempa?.bujur)
                    minggu.setText(response.body()?.infogempa?.gempa?.magnitude)

                }

                override fun onFailure(call: Call<ModelAverage>, t: Throwable) {
                    Log.d("rachmafadhillah", "error: " + t.message.toString())
                }


            })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PengunjungFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PengunjungFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}