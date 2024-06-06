package com.example.iotsystemparking

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.iotsystemparking.databinding.FragmentSlotBinding
import com.example.iotsystemparking.model.ModelSlot
import info.mqtt.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SlotFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SlotFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentSlotBinding
    private lateinit var mqttAndroidClient: MqttAndroidClient
    private lateinit var _car1: LinearLayout
    private lateinit var _car2: LinearLayout

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
        binding = FragmentSlotBinding.inflate(layoutInflater)

        _car1 = binding.card1
        _car2 = binding.card2

        // Set click listener for the registration button
        binding.btnLt2.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.f_container, Slot2Fragment()).commit()
        }

        connect(requireContext())
        return binding.root
    }

    fun connect(applicationContext : Context) {

        mqttAndroidClient = MqttAndroidClient ( applicationContext,
            "tcp://broker.hivemq.com",
            "45565" )

        mqttAndroidClient.setCallback(object : MqttCallback {
            override fun connectionLost(cause: Throwable?) {
                Log.d("rachmafadhillah", "connection lost: " + cause?.message.toString())
            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                Log.d("rachmafadhillah", "message: " + message.toString())
                if (topic.equals("lantai1")) {
                    // Ubah latar belakang berdasarkan nilai payload
                    when (message?.toString()) {
                        "1,1" -> {
                            _car1.setBackgroundResource(R.drawable.card_border_off)
                        }

                        "1,0" -> {
                            _car1.setBackgroundResource(R.drawable.card_border_on)
                        }

                        "2,1" -> {
                            _car2.setBackgroundResource(R.drawable.card_border_off)
                        }

                        "2,0" -> {
                            _car2.setBackgroundResource(R.drawable.card_border_on)
                        }
                    }
                }
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                Log.d("rachmafadhillah", "complete: " + token.toString())
            }

        })

        try {
            val token = mqttAndroidClient.connect()
            token.actionCallback = object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken){
                    Log.d("rachmafadhillah", "Broker Connected")
                    subscribe("lantai1")
                    //connectionStatus = true
                    // Give your callback on connection established here
                }
                override fun onFailure(asyncActionToken: IMqttToken, exception: Throwable) {
                    //connectionStatus = false
                    Log.d("rachmafadhillah", "Broker Failure")
                    // Give your callback on connection failure here
                    exception.printStackTrace()
                }
            }
        } catch (e: MqttException) {
            // Give your callback on connection failure here
            e.printStackTrace()
        }
    }

    fun subscribe(topic: String, qos: Int = 1) {
        try {
            mqttAndroidClient.subscribe(topic, qos, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.d("rachmafadhillah", "Subscribed to $topic")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.d("rachmafadhillah", "Failed to subscribe $topic")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun publish(topic: String, msg: String, qos: Int = 1, retained: Boolean = false) {
        try {
            val message = MqttMessage()
            message.payload = msg.toByteArray()
            message.qos = qos
            message.isRetained = retained
            mqttAndroidClient.publish(topic, message, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.d("rachmafadhillah", "$msg published to $topic")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.d("rachmafadhillah", "Failed to publish $msg to $topic")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SlotFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SlotFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}