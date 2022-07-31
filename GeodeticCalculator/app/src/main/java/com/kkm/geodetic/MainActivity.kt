package com.kkm.geodetic

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.kkm.geodetic.databinding.MainDrawerLayoutBinding
import com.kkm.geodetic.fragment.DirectionFragment
import com.kkm.geodetic.fragment.MetastasisFragment
import com.kkm.geodetic.fragment.SynthesisFragment
import com.kkm.geodetic.fragment.TriangleFragment
import com.kkm.geodetic.room.HistoryDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainDrawerLayoutBinding

    var db: HistoryDatabase? = null
    private var backKeyPressedTime: Long = 0;

    private var metastasisFragment: MetastasisFragment? = null
    private var directionFragment: DirectionFragment? = null
    private var triangleFragment: TriangleFragment? = null
    private var synthesisFragment: SynthesisFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainDrawerLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        init()

    }

    private fun init() {

        if(metastasisFragment == null){
            metastasisFragment = MetastasisFragment()
            supportFragmentManager.beginTransaction().add(R.id.ll_fragment,metastasisFragment!!).commit()
            supportFragmentManager.beginTransaction().show(metastasisFragment!!).commit()
        }

        binding.mainLayout.btnSideMenuOpen.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        initNavigationBar()

        db = HistoryDatabase.getInstance(this)

        val savedContacts = db!!.destRoomDao().getAll()

        if (savedContacts.isNotEmpty()) {
            //destList.addAll(savedContacts)

        }
    }

    private fun initNavigationBar() {
        binding.mainLayout.bnvMain.run {
            setOnItemSelectedListener { item ->
                if (item.itemId != binding.mainLayout.bnvMain.selectedItemId) {
                    when (item.itemId) {
                        R.id.navigation_metastasis -> {
                            if(metastasisFragment == null){
                                metastasisFragment = MetastasisFragment()
                                supportFragmentManager.beginTransaction().add(R.id.ll_fragment,metastasisFragment!!).commit()
                            }
                            if(metastasisFragment != null) supportFragmentManager.beginTransaction().show(metastasisFragment!!).commit()
                            if(directionFragment != null) supportFragmentManager.beginTransaction().hide(directionFragment!!).commit()
                            if(triangleFragment != null) supportFragmentManager.beginTransaction().hide(triangleFragment!!).commit()
                            if(synthesisFragment != null) supportFragmentManager.beginTransaction().hide(synthesisFragment!!).commit()

                        }
                        R.id.navigation_direction -> {
                            if(directionFragment == null){
                                directionFragment = DirectionFragment()
                                supportFragmentManager.beginTransaction().add(R.id.ll_fragment,directionFragment!!).commit()
                            }
                            if(metastasisFragment != null) supportFragmentManager.beginTransaction().hide(metastasisFragment!!).commit()
                            if(directionFragment != null) supportFragmentManager.beginTransaction().show(directionFragment!!).commit()
                            if(triangleFragment != null) supportFragmentManager.beginTransaction().hide(triangleFragment!!).commit()
                            if(synthesisFragment != null) supportFragmentManager.beginTransaction().hide(synthesisFragment!!).commit()
                        }
                        R.id.navigation_triangle -> {
                            if(triangleFragment == null){
                                triangleFragment = TriangleFragment()
                                supportFragmentManager.beginTransaction().add(R.id.ll_fragment,triangleFragment!!).commit()
                            }
                            if(metastasisFragment != null) supportFragmentManager.beginTransaction().hide(metastasisFragment!!).commit()
                            if(directionFragment != null) supportFragmentManager.beginTransaction().hide(directionFragment!!).commit()
                            if(triangleFragment != null) supportFragmentManager.beginTransaction().show(triangleFragment!!).commit()
                            if(synthesisFragment != null) supportFragmentManager.beginTransaction().hide(synthesisFragment!!).commit()
                        }
                        R.id.navigation_synthesis -> {
                            if(synthesisFragment == null){
                                synthesisFragment = SynthesisFragment()
                                supportFragmentManager.beginTransaction().add(R.id.ll_fragment,synthesisFragment!!).commit()
                            }
                            if(metastasisFragment != null) supportFragmentManager.beginTransaction().hide(metastasisFragment!!).commit()
                            if(directionFragment != null) supportFragmentManager.beginTransaction().hide(directionFragment!!).commit()
                            if(triangleFragment != null) supportFragmentManager.beginTransaction().hide(triangleFragment!!).commit()
                            if(synthesisFragment != null) supportFragmentManager.beginTransaction().show(synthesisFragment!!).commit()
                        }
                    }
                }
                true
            }
            selectedItemId = R.id.navigation_metastasis
        }
    }


    override fun onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            Toast.makeText(this, "뒤로가기를 한 번 더 누르면 앱이 종료합니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish()
        }
    }

}