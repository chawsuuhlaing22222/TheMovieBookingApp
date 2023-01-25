package com.padc.csh.themovieapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.SnackViewPagerAdapter
import com.padc.csh.themovieapplication.delegates.OrderedFoodDetailAdapter
import com.padc.csh.themovieapplication.dummy.snackList
import kotlinx.android.synthetic.main.activity_get_snack.*

class GetSnackActivity : AppCompatActivity() {
    private var openShowFoodOrderedDetail=false
    lateinit var snackAdapter:SnackViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_snack)

        setUpListener()
        setUpViewPager()
        setUpRecycerView()
    }

    private fun setUpRecycerView() {
        rvOrderedFoodList.adapter=OrderedFoodDetailAdapter()
        rvOrderedFoodList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    private fun setUpListener(){
        ivDropDownArrow.setOnClickListener {

            if(!openShowFoodOrderedDetail){
                openShowFoodOrderedDetail=true
                rvOrderedFoodList.visibility=View.VISIBLE
                ivDropDownArrow.setImageResource(R.drawable.ic_uparrow)
            }else{
                openShowFoodOrderedDetail=false
                rvOrderedFoodList.visibility=View.GONE
                ivDropDownArrow.setImageResource(R.drawable.down_arrow)
            }

        }

        rlFoodCheckOutGetSnackScrn.setOnClickListener {
            startActivity(Intent(this,CheckOutAcitivy::class.java))
        }
    }


    private fun setUpViewPager(){

        snackAdapter=SnackViewPagerAdapter(this)
        viewPagerGetSnackScrn.adapter=snackAdapter

        TabLayoutMediator(tabLayoutGetSnackScrn,viewPagerGetSnackScrn){tab,position->

            tab.text= snackList.get(position)

        }.attach()
    }
}