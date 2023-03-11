package com.padc.csh.themovieapplication.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.fragments.CinemaFragment
import com.padc.csh.themovieapplication.fragments.MovieFragment
import com.padc.csh.themovieapplication.fragments.MovieFragment.Companion.SELECTED_CITY
import com.padc.csh.themovieapplication.fragments.ProfileFragment
import com.padc.csh.themovieapplication.fragments.TicketFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var selectedCity = ""

    companion object {
        const val CITY_PARAM = "CITY PARAM"
        fun newIntent(context: Context, city: String): Intent {
            var intent = Intent(context, MainActivity::class.java)
            intent.putExtra(CITY_PARAM, city)
            return intent

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectedCity = intent.getStringExtra(CITY_PARAM) ?: ""

        //initially auto select homefragment
        switchFragment(MovieFragment())

        bottomNav.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
            return@setOnNavigationItemSelectedListener when (menuItem.itemId) {
                R.id.navigation_movie -> {
                    switchFragment(MovieFragment())
                    true
                }
                R.id.navigation_cinema -> {
                    switchFragment(CinemaFragment())
                    true
                }
                R.id.navigation_ticket -> {
                    switchFragment(TicketFragment())
                    true
                }
                R.id.navigation_profile -> {
                    switchFragment(ProfileFragment())
                    true
                }
                else -> false
            }

        }

    }

    private fun switchFragment(fragment: Fragment) {

        var bundle = Bundle()
        bundle.putString(SELECTED_CITY, selectedCity)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, fragment).commit()
    }

}