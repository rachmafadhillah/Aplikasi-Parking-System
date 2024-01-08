package com.example.iotsystemparking.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.iotsystemparking.ListGempaActivity
import com.example.iotsystemparking.R
import com.example.iotsystemparking.model.GempaItem
import com.example.iotsystemparking.model.ModelListGempa

class AdapterBuatanSaya(val data: ModelListGempa?, val context: ListGempaActivity, val _g: List<GempaItem?>)
    : ArrayAdapter<GempaItem>(context, R.layout.custom_listview, _g) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_listview, null, true)

        //indexing
        var _idx = rowView.findViewById<TextView>(R.id.lst_nomor)
        //tanggal
        var _tgl = rowView.findViewById<TextView>(R.id.lst_tanggal)
        //koordinat
        var _koordinat = rowView.findViewById<TextView>(R.id.lst_koordinat)
        //wilayah
        var _wilayah = rowView.findViewById<TextView>(R.id.lst_wilayah)
        //potensi
        var _potensi = rowView.findViewById<TextView>(R.id.lst_potensi)

        //pengisian data
        _idx.setText((position + 1).toString())
        Log.d("rachmafadhillah", "posisi: " + (position + 1))

        _tgl.setText(data?.infogempa?.gempa?.get(position)?.tanggal)
        Log.d("rachmafadhillah", "Tanggal: " + data?.infogempa?.gempa?.get(position)?.tanggal)

        _koordinat.setText(data?.infogempa?.gempa?.get(position)?.coordinates)
        Log.d("rachmafadhillah", "Koordinat: " + data?.infogempa?.gempa?.get(position)?.coordinates)

        _wilayah.setText(data?.infogempa?.gempa?.get(position)?.wilayah)
        Log.d("rachmafadhillah", "Wilayah: " + data?.infogempa?.gempa?.get(position)?.wilayah)

        _potensi.setText(data?.infogempa?.gempa?.get(position)?.potensi)
        Log.d("rachmafadhillah", "Wilayah: " + data?.infogempa?.gempa?.get(position)?.potensi)

        return rowView
    }
}