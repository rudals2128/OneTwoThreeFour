package com.kkm.geodetic.fragment

import android.os.Bundle
import android.view.View
import com.kkm.geodetic.R
import com.kkm.geodetic.base.UtilityBase
import com.kkm.geodetic.databinding.MetastasisFragmentBinding
import com.kkm.geodetic.databinding.SynthesisFragmentBinding

class SynthesisFragment : UtilityBase.BaseFragment<SynthesisFragmentBinding>(R.layout.synthesis_fragment) {

    override fun SynthesisFragmentBinding.onCreateView() {

    }

    override fun SynthesisFragmentBinding.onViewCreated() {
        binding.btnGo.setOnClickListener {  }

    }
}