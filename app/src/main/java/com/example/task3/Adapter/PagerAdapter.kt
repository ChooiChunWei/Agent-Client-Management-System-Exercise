package com.example.task3.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.task3.Data.Client
import com.example.task3.Fragments.newBusinessFragment
import com.example.task3.Fragments.policyServicingFragment

internal class PagerAdapter(fm:FragmentManager?, private val clientList:ArrayList<Client>):FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{
                newBusinessFragment(clientList)
            }
            1 ->{
                policyServicingFragment(clientList)
            }
            else->{
                newBusinessFragment(clientList)
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}