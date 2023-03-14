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
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.SnackListAdapter
import com.padc.csh.themovieapplication.adapters.SnackViewPagerAdapter
import com.padc.csh.themovieapplication.data.models.MovieBookingModel
import com.padc.csh.themovieapplication.data.models.MovieBookingModelImpl
import com.padc.csh.themovieapplication.data.vos.SeatVO
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
    lateinit var mOrderedFoodDetailAdapter: OrderedFoodDetailAdapter

    //model
    private var mTheMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    private var snackList: List<SnackVO> = listOf()
    private var orderSnackList: MutableList<SnackVO> = mutableListOf()
    var snackListForCategory: MutableList<SnackVO> = mutableListOf()
    private var totalSnackCount:Int=0
    private var totalPrice:Int=0
    private var selectedCategoryId: Int = 0

    //private var orderSnackList:List<SnackVO> = listOf()
    private var snackCategoryList: List<SnackCategoryVO> = listOf()
    private var token: String = ""

    companion object {
        const val SEAT_LIST = "seat list"
        const val MOVIE="MOVIE"
        const val CINEMA="CINEMA"
        const val IEXTRA_TIMESLOT_ID = "ID"
        const val BOOKING_DATE = "BOOKING DATE"

        fun newIntent(context: Context, timeSlotIdd: String, bookingDate: String,movie:String,cinema:String,seatList: String): Intent {
            var intent = Intent(context, GetSnackActivity::class.java)
            intent.putExtra(IEXTRA_TIMESLOT_ID, timeSlotIdd)
            intent.putExtra(BOOKING_DATE, bookingDate)
            intent.putExtra(MOVIE,movie)
            intent.putExtra(CINEMA,cinema)
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
            token = "Bearer " + it.token.toString()
        }
    }

    private fun requestData() {

        mTheMovieBookingModel.getSnackCategory(token, {
            snackCategoryList = it
            setUpTabLayout(it)
        }, {

            showErrorMsg(it, snackView)
        })

        mTheMovieBookingModel.getSnackAll(token, {
            snackList = it
            mSnackListAdapter.setNewData(it)
        }, {
            showErrorMsg(it, snackView)
        })


    }

    private fun setUpTabLayout(snackList: List<SnackCategoryVO>) {

        tabLayoutGetSnackScrn.newTab().apply {
            this.text = "All"
            tabLayoutGetSnackScrn.addTab(this)
        }

        snackList.forEach {
            tabLayoutGetSnackScrn.newTab().apply {
                this.text = it.title
                tabLayoutGetSnackScrn.addTab(this)
            }
        }
    }

    private fun setUpRecycerView() {
        mOrderedFoodDetailAdapter = OrderedFoodDetailAdapter(this)
        rvOrderedFoodList.adapter = mOrderedFoodDetailAdapter
        rvOrderedFoodList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mSnackListAdapter = SnackListAdapter(this)
        rvAllSnackListGetSnackScrn.adapter = mSnackListAdapter
        rvAllSnackListGetSnackScrn.layoutManager = GridLayoutManager(this, 2)


    }

    private fun setUpListener() {

        tabLayoutGetSnackScrn.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                var position = tab?.position ?: 0
                if (position != 0) {
                    getSnackByCategoryId(snackCategoryList[position - 1].id?.toInt() ?: 0)
                    Toast.makeText(
                        this@GetSnackActivity, "${snackCategoryList.get(position - 1).id} ${
                            snackCategoryList.get(position - 1).title
                        }", Toast.LENGTH_SHORT
                    ).show()
                } else {
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

            var movieVo=intent.getStringExtra(MOVIE) ?: ""
            var selectedcinema=intent.getStringExtra(CINEMA) ?: ""
            var selectedDate=intent.getStringExtra(BOOKING_DATE) ?: ""
            var timeSlotId=intent.getStringExtra(IEXTRA_TIMESLOT_ID) ?: ""
            var seatList =intent.getStringExtra(SEAT_LIST)
            var type=object : TypeToken<List<SnackVO>>(){}.type
            var snackList= Gson().toJson(orderSnackList,type)
            startActivity(CheckOutAcitivy.newIntentFromSnack(this,"sncak",timeSlotId, bookingDate = selectedDate,movieVo,selectedcinema,seatList ?: "",snackList))
        }
    }

    private fun getSnackByCategoryId(categoryId: Int) {
//        mTheMovieBookingModel.getSnackByCategoryId(token,categoryId,{
//            mSnackListAdapter.setNewData(it)
//        },{
//            showErrorMsg(it,snackView)
//        })
        selectedCategoryId = categoryId
        snackListForCategory= mutableListOf()
        snackList.forEach {
            if (it.categoryId == categoryId) snackListForCategory.add(it)
        }
        mSnackListAdapter.setNewData(snackListForCategory)
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
        orderSnackList = mutableListOf()
        totalPrice=0
        totalSnackCount=0

        snackList.forEach {
            if (snackVO.name.equals(
                    it.name,
                    true
                ) && snackVO.categoryId?.equals(it.categoryId) == true
            ) {
                it.count = snackVO.count
            }

            if (it.count > 0) {
                orderSnackList.add(it)
                totalPrice +=it.price.times(it.count)
                totalSnackCount +=it.count
            }
        }
        // orderSnackList= snackList.map { snackVO ->snackVO.count > 0  }.jo
        mOrderedFoodDetailAdapter.setNewData(orderSnackList)
        if(totalSnackCount > 0){
            tvNotiFlagText.visibility=View.VISIBLE
            tvNotiFlagText.text=totalSnackCount.toString()
            tvTotalAmount.text=getString(R.string.order_snack_price,totalPrice.toString())
        }else{
            tvNotiFlagText.visibility=View.GONE
            tvTotalAmount.text=getString(R.string.order_snack_price,totalPrice.toString())
        }

        if (tabLayoutGetSnackScrn.selectedTabPosition != 0) {
            getSnackByCategoryId(selectedCategoryId)
        } else {
            mSnackListAdapter.setNewData(snackList)
        }

    }


}