package com.example.task3.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout.VERTICAL
import android.widget.SearchView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task3.Adapter.generalRecyclerViewAdapter
import com.example.task3.Data.Client
import com.example.task3.Activity.DetailActivity
import com.example.task3.R
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [policyServicingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class policyServicingFragment(private val clients:ArrayList<Client>) : Fragment(),generalRecyclerViewAdapter.OnUserClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var filteredData =ArrayList<Client>()
    var displayList = ArrayList<Client>()

    //Declare global variable for recyclerView
    var adapter: generalRecyclerViewAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_policy_servicing, container,false)

        //getting recyclerview from xml
        val recyclerView = v.findViewById(R.id.psRecyclerView) as RecyclerView
        val searchView = v.findViewById(R.id.psSearchView) as SearchView

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(activity, VERTICAL, false)

        filterData()
        displayList.addAll(filteredData)

        //Update the total of items in this fragment
        val psCountTxt = v.findViewById<TextView>(R.id.psCountTxt)
        psCountTxt.setText("Showing " +filteredData.size + " pending case(s)")

        //creating our adapter
        adapter = generalRecyclerViewAdapter(displayList,null,this,null,null,false,this)

        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter

        //SearchView function
        val psNotFoundImg = v.findViewById<ImageView>(R.id.psNotFoundImg)
        val psNotFoundTxt = v.findViewById<TextView>(R.id.psNotFoundTxt)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(input: String?): Boolean {

                if(input!!.isNotEmpty()){
                    psCountTxt.visibility = View.GONE
                    psNotFoundImg.visibility = View.INVISIBLE
                    psNotFoundTxt.visibility = View.INVISIBLE

                    displayList.clear()

                    val searchInput = input.toLowerCase(Locale.getDefault())

                    filteredData.forEach{ it ->
                        if(it.name.toLowerCase(Locale.getDefault()).contains(searchInput)){
                            displayList.add(it)
                        }else if(it.NRIC.toLowerCase(Locale.getDefault()).contains(searchInput)||it.Passport.toLowerCase(
                                Locale.getDefault()).contains(searchInput)){
                            displayList.add(it)
                        }else if(it.policyNo.toLowerCase(Locale.getDefault()).contains(searchInput)){
                            displayList.add(it)
                        }


                    }

                    adapter!!.notifyDataSetChanged()

                    if(displayList.size == 0){
                        psNotFoundImg.visibility = View.VISIBLE
                        psNotFoundTxt.visibility = View.VISIBLE

                    }


                }
                else{
                    psCountTxt.visibility = View.VISIBLE
                    psNotFoundImg.visibility = View.INVISIBLE
                    psNotFoundTxt.visibility = View.INVISIBLE

                    displayList.clear()
                    displayList.addAll(filteredData)
                    adapter!!.notifyDataSetChanged()
                }
                return true
            }
        })

        // Inflate the layout for this fragment
        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment policyServicingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(
            param1: String,
            param2: String,
            clients: ArrayList<Client>
        ) =
            policyServicingFragment(clients).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onItemClick(clientList: Client, position: Int) {
        //Send data using intent and bundle
        val intent = Intent(activity, DetailActivity::class.java)
        val bundle = bundleOf(
            "id" to position.toString(),
            "name" to clientList.name,
            "NRIC" to clientList.NRIC,
            "Passport" to clientList.Passport,
            "Policy" to clientList.policyNo,
            "Process" to clientList.process,
            "Category" to clientList.category,
            "LA2" to clientList.lifeAssured,
            "profilePic" to clientList.profilePic

        )
        intent.putExtras(bundle)

        //Want to get return from the next activity, then use this way
        startActivityForResult(intent,999)
    }

    fun filterData() {
        //filter the data
        for(i in 0 until clients.size){
            if(clients[i].category == "PS"){
                filteredData.add(clients[i])
            }
        }
    }
}