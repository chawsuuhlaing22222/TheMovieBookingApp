package com.padc.csh.themovieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.delegates.PaymentDelegate

class PaymentTypeViewHolder(itemView: View,delegate: PaymentDelegate) :RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            delegate.onChoosePayment()
        }
    }
}