package com.padc.csh.themovieapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.CityAdapter
import com.padc.csh.themovieapplication.adapters.CitySpinnerCustomAdapter
import com.padc.csh.themovieapplication.data.models.MovieBookingModel
import com.padc.csh.themovieapplication.data.models.MovieBookingModelImpl
import com.padc.csh.themovieapplication.data.vos.CityVO
import com.padc.csh.themovieapplication.delegates.CityDelegate
import com.padc.csh.themovieapplication.utils.ConfigUtils
import kotlinx.android.synthetic.main.activity_choose_location.*
import kotlinx.android.synthetic.main.activity_login_choose.*
import kotlinx.android.synthetic.main.view_item_custom_spinner_dropdown.*

class ChooseLocationActivity : AppCompatActivity(), CityDelegate {

//model
    private var movieBookingModel: MovieBookingModel = MovieBookingModelImpl
    lateinit var cityAdapter: CityAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_location)

        // cityList = resources.getStringArray(R.array.cities)
        setUpListener()
        setUpCityRecyclerView()
        requestData()
    }

    private fun requestData() {
        movieBookingModel.getCityFromDB {
            cityAdapter.setNewData(it)

        }
    }

    private fun setUpCityRecyclerView() {
        cityAdapter = CityAdapter(this)
        rvSelectedCity.adapter = cityAdapter
        rvSelectedCity.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpListener() {
        btnMapChooseLocationScrn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onCityItemClick(cityVO: CityVO) {

        ConfigUtils.getInstance().saveCity(cityVO.name.toString())
        startActivity(MainActivity.newIntent(this, cityVO.name.toString()))
    }


}