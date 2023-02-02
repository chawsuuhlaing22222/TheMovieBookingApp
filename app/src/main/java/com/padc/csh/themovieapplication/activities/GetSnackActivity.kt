package com.padc.csh.themovieapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.SnackListAdapter
import com.padc.csh.themovieapplication.adapters.SnackViewPagerAdapter
import com.padc.csh.themovieapplication.delegates.OrderedFoodDetailAdapter
import com.padc.csh.themovieapplication.delegates.SnackItemDelegate
import com.padc.csh.themovieapplication.dummy.snackList
import kotlinx.android.synthetic.main.activity_get_snack.*
import kotlinx.android.synthetic.main.fragment_snack_list.*

class GetSnackActivity : AppCompatActivity(), SnackItemDelegate {
    private var openShowFoodOrderedDetail = false
    lateinit var mSnackListAdapter: SnackListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_snack)

        setUpTabLayout()
        setUpListener()
        setUpRecycerView()
    }

    private fun setUpTabLayout(){
        snackList.forEach {
            tabLayoutGetSnackScrn.newTab().apply {
                this.text=it
                tabLayoutGetSnackScrn.addTab(this)
            }
        }
    }

    private fun setUpRecycerView() {
        rvOrderedFoodList.adapter = OrderedFoodDetailAdapter()
        rvOrderedFoodList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mSnackListAdapter = SnackListAdapter(this)
        rvAllSnackListGetSnackScrn.adapter = mSnackListAdapter
        rvAllSnackListGetSnackScrn.layoutManager = GridLayoutManager(this, 2)


    }

    private fun setUpListener() {

        tabLayoutGetSnackScrn.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                var position=tab?.position ?: 0
                Toast.makeText(this@GetSnackActivity, "${snackList.get(position)}", Toast.LENGTH_SHORT).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        ivDropDownArrow.setOnClickListener {

            if (!openShowFoodOrderedDetail) {
                openShowFoodOrderedDetail = true
                rvOrderedFoodList.visibility = View.VISIBLE
                ivDropDownArrow.setImageResource(R.drawable.ic_uparrow)
            } else {
                openShowFoodOrderedDetail = false
                rvOrderedFoodList.visibility = View.GONE
                ivDropDownArrow.setImageResource(R.drawable.down_arrow)
            }

        }

        rlFoodCheckOutGetSnackScrn.setOnClickListener {
            startActivity(Intent(CheckOutAcitivy.newIntent(this,"snack")))
        }
    }


    private fun setUpViewPager() {

//        snackAdapter=SnackViewPagerAdapter(this)
//        //viewPagerGetSnackScrn.adapter=snackAdapter
//
//        TabLayoutMediator(tabLayoutGetSnackScrn,viewPagerGetSnackScrn){tab,position->
//
//            tab.text= snackList.get(position)
//
//        }.attach()
    }

    override fun onAddSnackItem() {

    }
}