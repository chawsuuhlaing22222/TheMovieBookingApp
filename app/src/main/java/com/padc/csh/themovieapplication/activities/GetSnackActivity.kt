package com.padc.csh.themovieapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.SnackViewPagerAdapter
import com.padc.csh.themovieapplication.dummy.snackList
import kotlinx.android.synthetic.main.activity_get_snack.*

class GetSnackActivity : AppCompatActivity() {
    lateinit var snackAdapter:SnackViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_snack)


        setUpViewPager()
    }


    private fun setUpViewPager(){

        snackAdapter=SnackViewPagerAdapter(this)
        viewPagerGetSnackScrn.adapter=snackAdapter

        TabLayoutMediator(tabLayoutGetSnackScrn,viewPagerGetSnackScrn){tab,position->

            tab.text= snackList.get(position)

        }.attach()
    }
}