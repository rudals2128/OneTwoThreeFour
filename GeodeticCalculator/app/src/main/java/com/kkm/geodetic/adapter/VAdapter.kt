package com.kkm.geodetic.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.kkm.geodetic.R
import com.kkm.geodetic.data.Data

class VAdapter(private val context: Context) : RecyclerView.Adapter<VAdapter.ViewHolder>() {
    var itemList = mutableListOf<Data.itemV>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_v_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val east: TextView = itemView.findViewById(R.id.tv_rec_east)
        private val north: TextView = itemView.findViewById(R.id.tv_rec_north)
        private val v: TextView = itemView.findViewById(R.id.tv_rec_v)


        fun bind(item: Data.itemV) {

            east.text = item.east.toString()
            north.text = item.north.toString()
            v.text = item.v.toString()

        }
    }


}