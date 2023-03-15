package com.padc.csh.themovieapplication.delegates

import com.padc.csh.themovieapplication.data.vos.PaymentTypeVO

interface PaymentDelegate {
fun onChoosePayment(paymentTypeVO: PaymentTypeVO)
}
