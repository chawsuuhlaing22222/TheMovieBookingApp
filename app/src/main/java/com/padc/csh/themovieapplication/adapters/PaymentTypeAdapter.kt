package com.padc.csh.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.data.vos.PaymentTypeVO
import com.padc.csh.themovieapplication.delegates.PaymentDelegate
import com.padc.csh.themovieapplication.dummy.paymentTypeList
import com.padc.csh.themovieapplication.viewholders.PaymentTypeViewHolder
import kotlinx.android.synthetic.main.view_holder_payment_type.view.*

class PaymentTypeAdapter(var delegate: PaymentDelegate) :
    RecyclerView.Adapter<PaymentTypeViewHolder>() {

    private var payments: List<PaymentTypeVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentTypeViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_payment_type, parent, false)
        return PaymentTypeViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: PaymentTypeViewHolder, position: Int) {

        var paymentType = payments.get(position)
        holder.itemView.tvPaymentTypeName.text = paymentType.title
        Glide.with(holder.itemView.context).load(paymentType.icon)
            .into(holder.itemView.ivPaymentTypeIcon)

        holder.itemView.setOnClickListener {
            delegate.onChoosePayment(paymentType)
        }
    }

    override fun getItemCount(): Int {
        return payments.size
    }

    fun setNewData(paymentList: List<PaymentTypeVO>?) {
        if (paymentList != null) {
            payments = paymentList
        }
        notifyDataSetChanged()
    }
}