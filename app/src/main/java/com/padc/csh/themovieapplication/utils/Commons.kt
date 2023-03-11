package com.padc.csh.themovieapplication.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import com.google.android.material.snackbar.Snackbar
import com.padc.csh.themovieapplication.R
import kotlinx.android.synthetic.main.fragment_movie.*

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