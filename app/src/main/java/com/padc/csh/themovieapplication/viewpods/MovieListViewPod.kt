package com.padc.csh.themovieapplication.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.padc.csh.themovieapplication.adapters.MovieListAdapter
import com.padc.csh.themovieapplication.delegates.MovieListDelegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var mDelegate: MovieListDelegate
    lateinit var mAdapter: MovieListAdapter

    override fun onFinishInflate() {

        super.onFinishInflate()
    }

    public fun setUpViewPod(delegate: MovieListDelegate) {
        setUpDelegate(delegate)
        setUpRecyclerAdapter()
    }

    private fun setUpDelegate(delegate: MovieListDelegate) {
        mDelegate = delegate
    }

    private fun setUpRecyclerAdapter() {
        mAdapter = MovieListAdapter(mDelegate)
        rvMovieListViewPod.adapter = mAdapter
        rvMovieListViewPod.layoutManager = GridLayoutManager(context, 2)
    }

}