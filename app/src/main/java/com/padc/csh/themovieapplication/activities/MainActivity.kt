package com.padc.csh.themovieapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.padc.csh.themovieapplication.delegates.BannerDelegate
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.BannerAdapter
import com.padc.csh.themovieapplication.fragments.CinemaFragment
import com.padc.csh.themovieapplication.fragments.MovieFragment
import com.padc.csh.themovieapplication.fragments.ProfileFragment
import com.padc.csh.themovieapplication.fragments.TicketFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initially auto select homefragment
        switchFragment(MovieFragment())

        bottomNav.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
            return@setOnNavigationItemSelectedListener when(menuItem.itemId){
                R.id.navigation_movie->{
                    switchFragment(MovieFragment())
                    true
                }
                R.id.navigation_cinema->{
                    switchFragment(CinemaFragment())
                    true
                }
                R.id.navigation_ticket->{
                    switchFragment(TicketFragment())
                    true
                }
                R.id.navigation_profile->{
                    switchFragment(ProfileFragment())
                    true
                }
                else->false
            }

        }

    }
    private fun switchFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container,fragment).commit()
    }

}