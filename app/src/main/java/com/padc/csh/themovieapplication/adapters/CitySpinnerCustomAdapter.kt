package com.padc.csh.themovieapplication.adapters

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import com.padc.csh.themovieapplication.R
import kotlinx.android.synthetic.main.view_item_custom_spinner_dropdown.view.*
import java.util.zip.Inflater
//Not use //but useful
class CitySpinnerCustomAdapter(val context: Context,val cities:Array<String>): BaseAdapter() {
    override fun getCount(): Int {

        return cities.size
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(position: Int, p1: View?, parent: ViewGroup?): View {
        val view=LayoutInflater.from(context).inflate(R.layout.view_item_custom_spinner_dropdown,null)
        if(position>0){
            view.tvSpinnerSelectedValue.text=cities.get(position).toString()
        }else{
            view.tvSpinnerSelectedValue.visibility=View.GONE
           // view.viewLineSeparator.visibility=View.GONE
        }
        return view
    }
}