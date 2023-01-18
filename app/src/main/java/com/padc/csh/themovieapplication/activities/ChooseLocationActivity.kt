package com.padc.csh.themovieapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.CitySpinnerCustomAdapter
import kotlinx.android.synthetic.main.activity_choose_location.*
import kotlinx.android.synthetic.main.activity_login_choose.*
import kotlinx.android.synthetic.main.view_item_custom_spinner_dropdown.*

class ChooseLocationActivity : AppCompatActivity() {

    lateinit var cityList: Array<String>
    var spinnerFlag = 0 //to prevent first auto select of spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_location)


        cityList = resources.getStringArray(R.array.cities)


        setUpSpinnerListener()
        setUpListener()
    }

    private fun setUpListener() {
        btnMapChooseLocationScrn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun setUpSpinnerListener() {

        /*thisis ok also
                var customAdapter= ArrayAdapter(this,
            R.layout.view_item_custom_spinner_dropdown,R.id.tvSpinnerSelectedValue,resources.getStringArray(R.array.cities))
        spinnerTest.adapter=customAdapter*/

        var customAdapter = CitySpinnerCustomAdapter(this, resources.getStringArray(R.array.cities))
        spinnerTest.adapter = customAdapter

        spinnerTest.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                p1: View?,
                position: Int,
                p3: Long
            ) {
                spinnerFlag = spinnerFlag + 1
                if (spinnerFlag == 1) { //prevent first auto select
                    tvSelectedCity.text = "Cities"
                } else {
                    tvSelectedCity.text = cityList.get(position)
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }


}