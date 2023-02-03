package com.padc.csh.themovieapplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.activities.LoginChooseActivity
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpActionListener()
    }

    private fun setUpActionListener(){
        rlLogoutProfileScrn.setOnClickListener {
            startActivity(Intent(requireContext(),LoginChooseActivity::class.java))
           activity?.finish()
        }
    }


}