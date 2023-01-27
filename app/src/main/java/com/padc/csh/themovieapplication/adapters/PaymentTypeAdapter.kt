package com.padc.csh.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.delegates.PaymentDelegate
import com.padc.csh.themovieapplication.dummy.paymentTypeList
import com.padc.csh.themovieapplication.viewholders.PaymentTypeViewHolder
import kotlinx.android.synthetic.main.view_holder_payment_type.view.*

class PaymentTypeAdapter(var delegate: PaymentDelegate): RecyclerView.Adapter<PaymentTypeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentTypeViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_payment_type,parent,false)
        return PaymentTypeViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: PaymentTypeViewHolder, position: Int) {
           var typeName= paymentTypeList.get(position)


            if(typeName.contains("upi",true)){
                holder.itemView.ivPaymentTypeIcon.setImageResource(R.drawable.ic_upi)
            }else if(typeName.contains("gift voucher",true)) {
                holder.itemView.ivPaymentTypeIcon.setImageResource(R.drawable.ic_gift_voucher)
            }else if(typeName.contains("Quick Pay",true)) {
                holder.itemView.ivPaymentTypeIcon.setImageResource(R.drawable.ic_quick_pay)
            }else if(typeName.contains("Credit Card / Debit Card",true)) {
                holder.itemView.ivPaymentTypeIcon.setImageResource(R.drawable.ic_credit_card)
            }else if(typeName.contains("Redeem Point",true)) {
                holder.itemView.ivPaymentTypeIcon.setImageResource(R.drawable.ic_redeem_point)
            }else if(typeName.contains("Mobile Wallet",true)) {
                holder.itemView.ivPaymentTypeIcon.setImageResource(R.drawable.ic_mobile_wallet)
            }else if(typeName.contains("Net Banking",true)) {
                holder.itemView.ivPaymentTypeIcon.setImageResource(R.drawable.ic_net_banking)
            }else {
                holder.itemView.ivPaymentTypeIcon.setImageResource(R.drawable.ic_upi)
            }

        holder.itemView.tvPaymentTypeName.text= paymentTypeList.get(position)

    }

    override fun getItemCount(): Int {
       return 7
    }
}