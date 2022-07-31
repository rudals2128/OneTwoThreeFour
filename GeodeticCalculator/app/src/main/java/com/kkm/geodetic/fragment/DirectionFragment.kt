package com.kkm.geodetic.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.kkm.geodetic.R
import com.kkm.geodetic.base.UtilityBase
import com.kkm.geodetic.databinding.DirectionFragmentBinding
import kotlin.Exception
import kotlin.math.*

class DirectionFragment : UtilityBase.BaseFragment<DirectionFragmentBinding>(R.layout.direction_fragment) {

    override fun DirectionFragmentBinding.onCreateView() {

    }

    override fun DirectionFragmentBinding.onViewCreated() {

        binding.btnGo.setOnClickListener {
//            try {
                var east_1 = 0.0
                var east_2 = 0.0
                var north_1 = 0.0
                var north_2 = 0.0
                var type = 0

                if (binding.edtOneEast.text.toString() != "") {
                    east_1 = binding.edtOneEast.text.toString().toDouble()
                    Log.d("klm", " east_1 = ${east_1}")
                }
                if (binding.edtTwoEast.text.toString() != "") {
                    east_2 = binding.edtTwoEast.text.toString().toDouble()
                    Log.d("klm", " east_2 = ${east_2}")
                }
                if (binding.edtOneNorth.text.toString() != "") {
                    north_1 = binding.edtOneNorth.text.toString().toDouble()
                }
                if (binding.edtTwoNorth.text.toString() != "") {
                    north_2 = binding.edtTwoNorth.text.toString().toDouble()
                }

                var east = east_2 - east_1
                var north = north_2 - north_1

                binding.tvEastValue.text = "${east}"
                binding.tvNorthValue.text = "${north}"

                var d =
                        sqrt(
                    (binding.tvEastValue.text.toString().toDouble() * binding.tvEastValue.text.toString().toDouble())
                            + (binding.tvNorthValue.text.toString().toDouble() * binding.tvNorthValue.text.toString().toDouble()))


                binding.tvDValue.text = "${d}"

                if (east >= 0 && north >= 0) {
                    tvLimitValue.text = "1상한"
                    type = 1
                } else if (east >= 0 && north < 0) {
                    tvLimitValue.text = "2상한"
                    type = 2
                } else if (east < 0 && north < 0) {
                    tvLimitValue.text = "3상한"
                    type = 3
                } else if (east < 0 && north >= 0) {
                    tvLimitValue.text = "4상한"
                    type = 4
                }

            Log.d("kkkkk"," a = ${abs(east)}   , b  = ${abs(north)}    ,  c = ${abs(east)/abs(north)}")

                var be = (Math.atan(abs(east) / abs(north))* 17.7777777 * 57.295779513)


                binding.tvBeValue.text = "${be}"

                var az = when (type) {
                    1 -> {
                        be
                    }
                    2 -> {
                        3200 - be
                    }
                    3 -> {
                        3200 + be
                    }
                    4 -> {
                        6400 - be
                    }
                    else -> {
                        0
                    }
                }

                binding.tvAzValue.text = "${az}"

            Log.d("kkkkkk"," d = ${d}  , limit = ${type} , az = ${az}  , be = ${be} , east = ${east}  , north =${north} ")
//
//            } catch (ex :Exception){
//                ex.printStackTrace()
//            }
        }

        binding.btnReset.setOnClickListener {
            try {
                binding.apply {
                    tvDValue.text = "0"
                    tvBeValue.text = "0"
                    tvAzValue.text = "0"
                    tvLimitValue.text = "0"
                    tvNorthValue.text = "0"
                    tvEastValue.text = "0"

                    edtOneEast.setText("")
                    edtTwoEast.setText("")
                    edtOneNorth.setText("")
                    edtTwoNorth.setText("")
                }
            }catch (ex : Exception){
                ex.printStackTrace()
            }
        }


    }
}