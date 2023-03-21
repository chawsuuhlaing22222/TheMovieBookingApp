package com.padc.csh.themovieapplication.fragments

import android.content.res.Resources
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.android.material.snackbar.Snackbar
import com.padc.csh.themovieapp.data.vos.COMMING_SOON
import com.padc.csh.themovieapp.data.vos.NOW_PLAYING
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.activities.MainActivity.Companion.CITY_PARAM
import com.padc.csh.themovieapplication.activities.MovieDetailActivity
import com.padc.csh.themovieapplication.activities.MovieSearchActivity
import com.padc.csh.themovieapplication.adapters.BannerAdapter
import com.padc.csh.themovieapplication.adapters.CommingSoonMovieAdapter
import com.padc.csh.themovieapplication.adapters.NowShowingMovieAdapter
import com.padc.csh.themovieapplication.data.models.MovieBookingModel
import com.padc.csh.themovieapplication.data.models.MovieBookingModelImpl
import com.padc.csh.themovieapplication.data.vos.BannerVO
import com.padc.csh.themovieapplication.delegates.BannerDelegate
import com.padc.csh.themovieapplication.delegates.MovieListDelegate
import com.padc.csh.themovieapplication.dummy.setPreviewBothSide
import com.padc.csh.themovieapplication.utils.showErrorMsg
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.view_item_toolbar_movie.view.*
import java.lang.Math.abs

class MovieFragment : Fragment(), BannerDelegate,MovieListDelegate {
    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mNowShowingMovieAdapter: NowShowingMovieAdapter
    lateinit var mCommingSoonMovieAdapter:CommingSoonMovieAdapter

    //model
    private val mMovieModel:MovieBookingModel=MovieBookingModelImpl
    private var movieType="now"

    companion object{
     const val SELECTED_CITY="SELECTED_CITY"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get arguments
        var bundel=arguments.takeIf { bundle ->  bundle?.containsKey(SELECTED_CITY) ?: false}
        var selectedCity=bundel?.getString(SELECTED_CITY) ?: ""
        viewItemToolbarMovieFrag.tvSelectedCity.text=selectedCity

        btnNowShowing.setBackgroundColor(resources.getColor(R.color.colorAccent,null))

        setUpBanner()
        setUpListener()
        setUpRecycler()
        requestData()

    }

    private fun requestData() {
        mMovieModel.getBanners(
            {
                mBannerAdapter.setNewData(it)
            },{

            }
        )
        mMovieModel.getMovieList(
            NOW_PLAYING,
            {
                mNowShowingMovieAdapter.setNewData(it)
            },{
                showErrorMsg(it,movieFrag)
            }
        )

        mMovieModel.getMovieList(
            COMMING_SOON,
            {
                mCommingSoonMovieAdapter.setNewData(it)
            },{
                showErrorMsg(it,movieFrag)
            }
        )


    }

    private fun setUpBannerViewPagerPadding() {
        viewPagerBanner.apply {
            clipChildren = false  // No clipping the left and right items
            clipToPadding = false  // Show the viewpager in full width without clipping the padding
            offscreenPageLimit = 3  // Render the left and right items
            (getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER // Remove the scroll effect
        }

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((10 * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        viewPagerBanner.setPageTransformer(compositePageTransformer)


    }

    private fun setUpRecycler() {
       mNowShowingMovieAdapter= NowShowingMovieAdapter(this)
        mCommingSoonMovieAdapter= CommingSoonMovieAdapter(this)

        rvNowShowingMovies.adapter=mNowShowingMovieAdapter
        rvNowShowingMovies.layoutManager=GridLayoutManager(context,2)

        rvCommingSoonMovies.adapter=mCommingSoonMovieAdapter
        rvCommingSoonMovies.layoutManager=GridLayoutManager(context,2)

    }

    private fun setUpListener() {

        //now showing movie btn action
        btnNowShowing.setOnClickListener {
           rvNowShowingMovies.visibility=View.VISIBLE
            rvCommingSoonMovies.visibility=View.GONE
            btnNowShowing.setBackgroundColor(resources.getColor(R.color.colorAccent,null))
            btnCommingSoon.setBackgroundColor(resources.getColor(com.google.android.material.R.color.mtrl_btn_transparent_bg_color,null))
            movieType="now"
        }

        //comming soon movie btn action
        btnCommingSoon.setOnClickListener {
            rvNowShowingMovies.visibility=View.GONE
            rvCommingSoonMovies.visibility=View.VISIBLE
            btnCommingSoon.setBackgroundColor(resources.getColor(R.color.colorAccent,null))
            btnNowShowing.setBackgroundColor(resources.getColor(com.google.android.material.R.color.mtrl_btn_transparent_bg_color,null))
            movieType="comming"
        }

        //search
        viewItemToolbarMovieFrag.ivSearch.setOnClickListener {
            startActivity(MovieSearchActivity.newIntent(requireContext(),movieType))
        }
    }

    private fun setUpBanner() {

        mBannerAdapter= BannerAdapter(this)
        viewPagerBanner.adapter=mBannerAdapter
        dotsIndicatorBanner.attachTo(viewPagerBanner)
        viewPagerBanner.setPreviewBothSide(R.dimen.margin_large,R.dimen.margin_large)
        viewPagerBanner.currentItem=1

    }

    override fun onTapBanner(movie:BannerVO) {
        //Toast.makeText(context, "banner", Toast.LENGTH_SHORT).show()
    }

    override fun onTapNowShowingMovie(movieId:String) {
        var id=movieId
       startActivity(MovieDetailActivity.newIntent(context,"now",movieId))
    }

    override fun onTapCommingSoonMovie(movieId:String) {
        startActivity(MovieDetailActivity.newIntent(context,"comming",movieId))
    }




}