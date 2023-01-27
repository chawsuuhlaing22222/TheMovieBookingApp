package com.padc.csh.themovieapplication.fragments

import android.content.res.Resources
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.BannerAdapter
import com.padc.csh.themovieapplication.delegates.BannerDelegate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movie.*
import java.lang.Math.abs

class MovieFragment : Fragment(), BannerDelegate {
    lateinit var mBannerAdapter: BannerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNowShowing.setBackgroundColor(resources.getColor(R.color.colorAccent,null))
        replaceMovieList(NowShowingMovieListFragment())
        setUpBanner()
        setUpListener()

    }

    private fun setUpListener() {
        //now showing movie btn action
        btnNowShowing.setOnClickListener {
            replaceMovieList(NowShowingMovieListFragment())
            btnNowShowing.setBackgroundColor(resources.getColor(R.color.colorAccent,null))
            btnCommingSoon.setBackgroundColor(resources.getColor(com.google.android.material.R.color.mtrl_btn_transparent_bg_color,null))
        }

        //comming soon movie btn action
        btnCommingSoon.setOnClickListener {
           replaceMovieList(CommingSoonMovieListFragment())
            btnCommingSoon.setBackgroundColor(resources.getColor(R.color.colorAccent,null))
            btnNowShowing.setBackgroundColor(resources.getColor(com.google.android.material.R.color.mtrl_btn_transparent_bg_color,null))

        }
    }

    private fun setUpBanner() {
        mBannerAdapter= BannerAdapter(this)
        viewPagerBanner.adapter=mBannerAdapter
        dotsIndicatorBanner.attachTo(viewPagerBanner)

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((40 * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        viewPagerBanner.setPageTransformer(compositePageTransformer)

    }

    override fun onTapBanner() {
        Toast.makeText(context, "banner", Toast.LENGTH_SHORT).show()
    }

    private fun replaceMovieList(fragment: Fragment){
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id
            .flShowingCommingMovies,fragment)?.commit()
    }

       /* private fun setUpToolBar() {
        activity?.setActionBar(toolBarMovieScrn)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        activity?.actionBar?.setHomeAsUpIndicator(R.drawable.ic_location_white)
        activity?.actionBar?.title="Yangon"


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_main_scrn,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }*/
}