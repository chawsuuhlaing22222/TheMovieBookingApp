package com.padc.csh.themovieapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.PaymentTypeAdapter
import com.padc.csh.themovieapplication.delegates.PaymentDelegate
import kotlinx.android.synthetic.main.activity_choose_payment_type.*

class ChoosePaymentTypeActivity : AppCompatActivity(),PaymentDelegate {
    lateinit var mPaymentAdapter: PaymentTypeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_payment_type)

        setUpPaymentRecyclerView()
    }

    private fun setUpPaymentRecyclerView() {
      mPaymentAdapter=PaymentTypeAdapter(this)
        rvPaymentType.adapter=mPaymentAdapter
        rvPaymentType.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

    }

    override fun onChoosePayment() {
        Toast.makeText(this,"Choose Payment",Toast.LENGTH_SHORT).show()
    }
}