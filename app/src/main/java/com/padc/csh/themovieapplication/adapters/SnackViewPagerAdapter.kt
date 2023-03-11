package com.padc.csh.themovieapplication.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.padc.csh.themovieapplication.dummy.snackList
import com.padc.csh.themovieapplication.fragments.SnackListFragment
import com.padc.csh.themovieapplication.fragments.SnackListFragment.Companion.IEXTRA_SNACK_NAME

class SnackViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {


    override fun getItemCount(): Int {
        return snackList.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment= SnackListFragment()
        var bundle=Bundle()
        bundle.putString(IEXTRA_SNACK_NAME, snackList.get(position))
        fragment.arguments=bundle
       return fragment
    }
}