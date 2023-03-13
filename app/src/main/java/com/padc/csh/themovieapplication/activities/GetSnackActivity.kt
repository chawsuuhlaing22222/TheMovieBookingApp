package com.padc.csh.themovieapplication.activities

import android.content.Context
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
import com.padc.csh.themovieapplication.data.models.MovieBookingModel
import com.padc.csh.themovieapplication.data.models.MovieBookingModelImpl
import com.padc.csh.themovieapplication.data.vos.SnackCategoryVO
import com.padc.csh.themovieapplication.data.vos.SnackVO
import com.padc.csh.themovieapplication.delegates.OrderedFoodDetailAdapter
import com.padc.csh.themovieapplication.delegates.SnackItemDelegate
import com.padc.csh.themovieapplication.dummy.snackList
import com.padc.csh.themovieapplication.utils.showErrorMsg
import kotlinx.android.synthetic.main.activity_get_snack.*
import kotlinx.android.synthetic.main.fragment_snack_list.*

class GetSnackActivity : AppCompatActivity(), SnackItemDelegate {
    private var openShowFoodOrderedDetail = false
    lateinit var mSnackListAdapter: SnackListAdapter

    //model
    private var mTheMovieBookingModel:MovieBookingModel=MovieBookingModelImpl
    private var snackList:List<SnackVO> = listOf()
    private var snackCategoryList:List<SnackCategoryVO> = listOf()
    private var token:String=""

    companion object{
        const val SEAT_LIST="seat list"
        fun newIntent(context: Context, seatList:String):Intent{
            var intent=Intent(context,GetSnackActivity::class.java)
            intent.putExtra(SEAT_LIST,seatList)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_snack)

        requestToken()
        setUpListener()
        setUpRecycerView()
        requestData()
    }

    private fun requestToken() {
        mTheMovieBookingModel.getProfile {
            token="Bearer "+ it.token.toString()
        }
    }

    private fun requestData() {

        mTheMovieBookingModel.getSnackCategory(token,{
            snackCategoryList=it
            setUpTabLayout(it)
        },{

            showErrorMsg(it,snackView)
        })

        mTheMovieBookingModel.getSnackAll(token,{
            snackList=it
            mSnackListAdapter.setNewData(it)
        },{
            showErrorMsg(it,snackView)
        })


    }

    private fun setUpTabLayout(snackList:List<SnackCategoryVO>){

        tabLayoutGetSnackScrn.newTab().apply {
            this.text="All"
            tabLayoutGetSnackScrn.addTab(this)
        }

        snackList.forEach {
            tabLayoutGetSnackScrn.newTab().apply {
                this.text=it.title
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
                if(position !=0){
                    getSnackByCategoryId(snackCategoryList[position-1].id?.toInt() ?: 0)
                    Toast.makeText(this@GetSnackActivity, "${snackCategoryList.get(position-1).id} ${
                        snackCategoryList.get(position-1).title
                    }", Toast.LENGTH_SHORT).show()
                }else{
                    mSnackListAdapter.setNewData(snackList)
                }


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

    private fun getSnackByCategoryId(categoryId: Int) {
        mTheMovieBookingModel.getSnackByCategoryId(token,categoryId,{
            mSnackListAdapter.setNewData(it)
        },{
            showErrorMsg(it,snackView)
        })
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



    override fun updateSnack(snackVO: SnackVO) {
        snackList.forEach {
            if(snackVO.name.equals(it.name,true) && snackVO.categoryId?.equals(it.categoryId) == true){
                it.count=snackVO.count
                return
            }
        }
        mSnackListAdapter.setNewData(snackList)
    }


}