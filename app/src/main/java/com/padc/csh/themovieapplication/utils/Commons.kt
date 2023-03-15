package com.padc.csh.themovieapplication.utils

import android.app.Dialog
import android.content.Context
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import com.google.android.material.snackbar.Snackbar
import com.padc.csh.themovieapplication.R
import kotlinx.android.synthetic.main.fragment_movie.*
import java.text.SimpleDateFormat
import java.util.*

var loadingDialog:Dialog?=null
var isLoading=false
fun showErrorMsg(error:String,view: View){
    Snackbar.make(view,"$error", Snackbar.LENGTH_SHORT).show()
}

fun showLoading(context: Context){
    loadingDialog= Dialog(context)
    var view=LayoutInflater.from(context).inflate(R.layout.view_holder_loading_dialog,null)
    loadingDialog?.setContentView(view)
    loadingDialog?.show()

    isLoading=true
}

fun hideLoading(){
    if(isLoading && loadingDialog!=null){
        loadingDialog?.hide()
    }
}

fun changeStringToMedimnDateFormat(context: Context,date:String):String{
    val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val date = formattedDate.parse(date)
    val dateString = DateFormat.getMediumDateFormat(context).format(date)
    return dateString
}