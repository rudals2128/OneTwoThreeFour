package com.kkm.geodetic.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.kkm.geodetic.R
import com.kkm.geodetic.adapter.VAdapter
import com.kkm.geodetic.base.UtilityBase
import com.kkm.geodetic.data.Data
import com.kkm.geodetic.databinding.MetastasisFragmentBinding
import kotlin.math.*

class MetastasisFragment : UtilityBase.BaseFragment<MetastasisFragmentBinding>(R.layout.metastasis_fragment) {

    private lateinit var mAdapter : VAdapter

    var item = mutableListOf<Data.itemV>()

    override fun MetastasisFragmentBinding.onCreateView() {

    }

    override fun MetastasisFragmentBinding.onViewCreated() {
        //binding.btnGo.setOnClickListener {  }
        mAdapter = VAdapter(requireActivity())
        binding.recMetastasis.adapter = mAdapter

        binding.btnCal.setOnClickListener {
            try {
                if (binding.edtBackAngle.text.toString() == "" || binding.edtRightAngle.text.toString() == "") {
                    Log.d(
                        "asdasd",
                        "${binding.edtV.text}  // ${binding.edtV.text.toString() == ""}"
                    )
                    return@setOnClickListener
                }
                if (binding.edtD.text.toString() != "" && binding.edtBackAngle.text.toString() != "" && binding.edtRightAngle.text.toString() != "") {
                    var x = binding.edtBackAngle.text.toString()
                        .toDouble() + binding.edtRightAngle.text.toString().toDouble()

                    if (x > 6400) {
                        x = x - 6400
                    }
                    Log.d("sin == ", " ${Math.sin(Math.toRadians(x / 17.7777777))}  // x = ${x} ")

                    var eastX = Math.round(
                        (sin(Math.toRadians(x / 17.7777777)) * binding.edtD.text.toString()
                            .toDouble()) * 10000
                    ) / 10000.0

                    var northX = Math.round(
                        (cos(Math.toRadians(x / 17.7777777)) * binding.edtD.text.toString()
                            .toDouble()) * 10000
                    ) / 10000.0

                    if (binding.edtV.text.toString() == "" || binding.edtV.text.toString() == "-") {
                        binding.edtV.setText("0")
                    }
                    var v = binding.edtV.text.toString().toDouble()
                    if (v > 1000) {
                        v = 1600 - v
                    }

                    var vv = Math.round(
                        (tan(Math.toRadians(v / 17.7777777)) * binding.edtD.text.toString()
                            .toDouble()) * 10000
                    ) / 10000.0

                    item.add(Data.itemV(eastX, northX, vv))

                    Log.d("kkkk", "${v} , ${vv}")

                    mAdapter.itemList = item
                    mAdapter.notifyDataSetChanged()

                    var back = binding.edtBackAngle.text.toString()
                        .toDouble() + binding.edtRightAngle.text.toString().toDouble()
                    if (back > 6400) {
                        back = back - 6400
                        if (back >= 3200) {
                            back = back - 3200
                        } else {
                            back = back + 3200
                        }
                        binding.edtBackAngle.setText("$back")
                        binding.edtRightAngle.setText("")
                        binding.edtD.setText("")
                        binding.edtV.setText("")

                    } else {
                        if (back >= 3200) {
                            back = back - 3200
                        } else {
                            back = back + 3200
                        }
                        binding.edtBackAngle.setText("$back")
                        binding.edtRightAngle.setText("")
                        binding.edtD.setText("")
                        binding.edtV.setText("")
                    }


                } else {

                    var back = binding.edtBackAngle.text.toString()
                        .toDouble() + binding.edtRightAngle.text.toString().toDouble()
                    if (back > 6400) {
                        back = back - 6400
                        if (back >= 3200) {
                            back = back - 3200
                        } else {
                            back = back + 3200
                        }
                        binding.edtBackAngle.setText("$back")
                        binding.edtRightAngle.setText("")

                    } else {
                        if (back >= 3200) {
                            back = back - 3200
                        } else {
                            back = back + 3200
                        }
                        binding.edtBackAngle.setText("$back")
                        binding.edtRightAngle.setText("")
                    }
                }
            } catch (ex : Exception){
                ex.printStackTrace()
            }
        }

        binding.btnGo.setOnClickListener {
            try {
                var east = 0.0
                var north = 0.0
                var v = 0.0
                if (binding.edtEast.text.toString() != "") {
                    east = binding.edtEast.text.toString().toDouble()
                }
                if (binding.edtNorth.text.toString() != "") {
                    north = binding.edtNorth.text.toString().toDouble()
                }
                if (binding.edtElevation.text.toString() != "") {
                    v = binding.edtElevation.text.toString().toDouble()
                }

                for (data in item) {
                    Log.d("kkkkk", "east = ${east}  , north = ${north} ,  v = ${v}")
                    east += data.east
                    north += data.north
                    v += data.v
                }

                binding.tvEastValue.text = east.toString()
                binding.tvNorthValue.text = north.toString()
                binding.tvElevationValue.text = v.toString()
            } catch (ex : Exception){
                ex.printStackTrace()
            }
        }

        binding.btnReset.setOnClickListener {
            try {
                item.clear()
                binding.tvElevationValue.text = "0"
                binding.tvEastValue.text = "0"
                binding.tvNorthValue.text = "0"

                binding.edtRightAngle.setText("")
                binding.edtBackAngle.setText("")
                binding.edtD.setText("")
                binding.edtV.setText("")

                binding.edtEast.setText("")
                binding.edtNorth.setText("")
                binding.edtElevation.setText("")
            }catch (ex : Exception){
                ex.printStackTrace()
            }
        }
    }
}