package com.padc.csh.themovieapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.SnackListAdapter
import com.padc.csh.themovieapplication.data.vos.SnackVO
import com.padc.csh.themovieapplication.delegates.SnackItemDelegate
import kotlinx.android.synthetic.main.fragment_snack_list.*


class SnackListFragment : Fragment(),SnackItemDelegate {
lateinit var mSnackListAdapter: SnackListAdapter

    companion object{
        const val IEXTRA_SNACK_NAME="IEXTRA_SNACK_NAME"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_snack_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bundle=arguments.takeIf {bundle: Bundle? ->  bundle?.containsKey(IEXTRA_SNACK_NAME) ?: false }
        val snackName=bundle?.getString(IEXTRA_SNACK_NAME) ?: " "
        Toast.makeText(context, "$snackName", Toast.LENGTH_SHORT).show()
        setUpSnackRecyclerView()
    }

    private fun setUpSnackRecyclerView() {
        mSnackListAdapter= SnackListAdapter(this)
        rvSnackList.adapter=mSnackListAdapter
        rvSnackList.layoutManager=GridLayoutManager(context,2)
    }



    override fun updateSnack(snackVO: SnackVO) {
        TODO("Not yet implemented")
    }

}