package com.kkm.geodetic.fragment

import com.kkm.geodetic.R
import com.kkm.geodetic.base.UtilityBase
import com.kkm.geodetic.databinding.MetastasisFragmentBinding
import com.kkm.geodetic.databinding.SynthesisFragmentBinding
import com.kkm.geodetic.databinding.TriangleFragmentBinding

class TriangleFragment : UtilityBase.BaseFragment<TriangleFragmentBinding>(R.layout.triangle_fragment) {

    override fun TriangleFragmentBinding.onCreateView() {

    }

    override fun TriangleFragmentBinding.onViewCreated() {
        binding.btnGo.setOnClickListener {  }

        binding.cbOneLeft.setOnClickListener {
            binding.cbOneLeft.isChecked = true
            binding.cbOneRight.isChecked= false
        }
        binding.cbOneRight.setOnClickListener {
            binding.cbOneLeft.isChecked = false
            binding.cbOneRight.isChecked= true
        }

    }
}