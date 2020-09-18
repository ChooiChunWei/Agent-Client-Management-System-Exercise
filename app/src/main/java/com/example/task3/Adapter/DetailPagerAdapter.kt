package com.example.task3.Adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.task3.Fragments.caseDetailsFragment
import com.example.task3.Fragments.infoFragment
import com.example.task3.Fragments.policyServicingCaseDetailsFragment
import com.example.task3.Fragments.scanDocFragment

internal class DetailPagerAdapter(fm: FragmentManager?, private val bundle: Bundle): FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                caseDetailsFragment(bundle)
            }
            1 -> {
                scanDocFragment(bundle)
            }
            2->{
                infoFragment(bundle)
            }
            3->{
                policyServicingCaseDetailsFragment(bundle)
            }
            else -> {
                //caseDetailsFragment()
                policyServicingCaseDetailsFragment(bundle)
            }
        }
    }

    override fun getCount(): Int {
        return 4
    }
}