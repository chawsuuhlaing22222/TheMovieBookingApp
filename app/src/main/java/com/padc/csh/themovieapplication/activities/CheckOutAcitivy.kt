package com.padc.csh.themovieapplication.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.OrderedFoodDetailCheckOutAdapter
import com.padc.csh.themovieapplication.delegates.OrderFoolListChekoutDelegate
import com.padc.csh.themovieapplication.dummy.orderedFoodList
import kotlinx.android.synthetic.main.activity_check_out_acitivy.*
import kotlinx.android.synthetic.main.custom_view_ticket_cancel_policy.view.*

class CheckOutAcitivy : AppCompatActivity(), OrderFoolListChekoutDelegate {
    lateinit var mOrderFoodAdapter: OrderedFoodDetailCheckOutAdapter
    private var openOrderFoodDetailFlag = true

    companion object{
        const val IEXTRA_DATA="from which activity"
        fun newIntent(context: Context,fromAction:String):Intent{
            val intent=Intent(context,CheckOutAcitivy::class.java)
            intent.putExtra(IEXTRA_DATA,fromAction)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out_acitivy)

        setUpViewVisibility()
        setUpRecyclerFoodList()
        bindData()
        setUpActionListener()

    }

    private fun setUpViewVisibility() {
        var fromAction=intent.getStringExtra(IEXTRA_DATA).toString()
        when(fromAction){
            "ticket"->{
                tvCheckOut.text="Ticekt Details"
                btnContinueCheckoutScrn.visibility=View.GONE
                rlTicketCancel.visibility=View.VISIBLE
                llTicketCancelPolicy.setBackgroundResource(R.drawable.bg_ticket_cancelation_policy_from_ticket)
            }
            else->{

            }
        }
    }

    private fun setUpActionListener() {

        ivDropDownArrowCheckoutScrn.setOnClickListener {
            if (!openOrderFoodDetailFlag) {
                openOrderFoodDetailFlag = true
                rvOrderedFoodListCheckoutScrn.visibility = View.VISIBLE
            } else {
                openOrderFoodDetailFlag = false
                rvOrderedFoodListCheckoutScrn.visibility = View.GONE
            }
        }

        llTicketCancelPolicy.setOnClickListener {
            createPolicyAlertDialog()
        }

        btnContinueCheckoutScrn.setOnClickListener {
            startActivity(Intent(this, ChoosePaymentTypeActivity::class.java))
        }

    }

    private fun createPolicyAlertDialog() {
        var dialog = AlertDialog.Builder(this).create()
        var view =
            LayoutInflater.from(this).inflate(R.layout.custom_view_ticket_cancel_policy, null)
        dialog.setView(view)
        view.btnCloseTicketCancelationDialog.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun setUpRecyclerFoodList() {
        mOrderFoodAdapter = OrderedFoodDetailCheckOutAdapter(this)
        rvOrderedFoodListCheckoutScrn.adapter = mOrderFoodAdapter
        rvOrderedFoodListCheckoutScrn.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


    private fun bindData() {
        tvMovieNameCheckoutScrn.text =
            Html.fromHtml("<font color='#ffffff'><b>Black White</b> </font><font color='#888888'>(3D)(U/A)</font>")
        tvCinemaNameCheckoutScrn.setText(Html.fromHtml("<font color='#00ff6a'>JCGV : Junction City </font><font color='#aaaaaa'>(SCREEN2)</font>"))
        tvTicketCountCheckoutScrn.text =
            Html.fromHtml("<font color='#aaaaaa'>M-Ticket (</font><font color='#00ff6a'>2</font><font color='#888888'>)</font>")
    }

    override fun onFoodCancel(position: Int) {

        orderedFoodList.removeAt(position)        //Toast.makeText(this,"cancel",Toast.LENGTH_SHORT).show()
        mOrderFoodAdapter.notifyDataSetChanged()

    }
}