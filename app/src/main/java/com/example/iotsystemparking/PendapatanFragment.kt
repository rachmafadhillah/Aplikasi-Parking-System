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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PendapatanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PendapatanFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var tgl: TextView
    private lateinit var total: TextView

    private lateinit var tgl2: TextView
    private lateinit var total2: TextView

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
        val rootView = inflater.inflate(R.layout.fragment_pendapatan, container, false)

        // Initialize your TextViews and other views
        tgl = rootView.findViewById(R.id.tv_mtanggal)
        total = rootView.findViewById(R.id.tv_mtotal)

        tgl2 = rootView.findViewById(R.id.tv_tanggal)
        total2 = rootView.findViewById(R.id.tv_total)
        // ... (repeat for other views)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Your code remains mostly the same
        ConfigPendapatan().getMotorService()
            .getMotorData()
            .enqueue(object : Callback<ModelPendapatan>{
                override fun onResponse(
                    call: Call<ModelPendapatan>,
                    response: Response<ModelPendapatan>
                ) {
                    Log.d("rachmafadhillah", "data json: " + response.body())

                    tgl.text = response.body()?.data?.tanggal
                    total.text = response.body()?.data?.totalHarga
                }

                override fun onFailure(call: Call<ModelPendapatan>, t: Throwable) {
                    Log.d("rachmafadhillah", "error: " + t.message.toString())

                }

            })

        ConfigPendapatan().getMobilService()
            .getMobilData()
            .enqueue(object : Callback<ModelPendapatan>{
                override fun onResponse(
                    call: Call<ModelPendapatan>,
                    response: Response<ModelPendapatan>
                ) {
                    Log.d("rachmafadhillah", "data json: " + response.body())

                    tgl2.text = response.body()?.data?.tanggal
                    total2.text = response.body()?.data?.totalHarga
                }

                override fun onFailure(call: Call<ModelPendapatan>, t: Throwable) {
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
         * @return A new instance of fragment PendapatanFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PendapatanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}