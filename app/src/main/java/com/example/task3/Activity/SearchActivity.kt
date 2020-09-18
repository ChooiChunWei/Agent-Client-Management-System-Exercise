package com.example.task3.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.LinearLayout.*
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task3.Adapter.agentRecyclerViewAdapter
import com.example.task3.Adapter.generalRecyclerViewAdapter
import com.example.task3.Data.Agent
import com.example.task3.Data.Client
import com.example.task3.R
import java.util.*
import kotlin.collections.ArrayList

class SearchActivity : AppCompatActivity(), generalRecyclerViewAdapter.OnUserClickListener,agentRecyclerViewAdapter.OnUserClickListener {
    var adapter: generalRecyclerViewAdapter? = null
    var adapter2: agentRecyclerViewAdapter? = null
    var clients = ArrayList<Client>()
    var agents = ArrayList<Agent>()
    var displayList = ArrayList<Client>()
    var displayAgentList = ArrayList<Agent>()

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        toolbar()

        //get data from previous activity using bundle
        clients = intent.getSerializableExtra("Clients") as ArrayList<Client>
        agents = intent.getSerializableExtra("Agents") as ArrayList<Agent>

        //getting recyclerview from xml
        val recyclerView = findViewById(R.id.searchRecyclerView) as RecyclerView
        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)

        //creating our adapters
        adapter = generalRecyclerViewAdapter(displayList,null,null,this,null,false,this)
        adapter2 = agentRecyclerViewAdapter(displayAgentList,clients,this)

        //Searchview Function
        val search = findViewById<SearchView>(R.id.search)
        val searchImg = findViewById<ImageView>(R.id.searchImg)
        val searchTxt = findViewById<TextView>(R.id.searchTxt)
        val searchCountTxt = findViewById<TextView>(R.id.searchCountTxt)

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(input: String?): Boolean {
                if(input!!.isNotEmpty()){
                    searchImg.visibility = View.GONE
                    searchTxt.visibility = View.GONE
                    searchTxt.text = "Enter at least the first three characters to search for Policy No./ Agent Name."


                    val searchInput = input.toLowerCase(Locale.getDefault())
                    var check = isLetters(searchInput)

                    //if check == true, then generate first
                    //else generate second
                    if(check == true){

                        //now adding the adapter to recyclerview
                        recyclerView.adapter = adapter2
                        displayAgentList.clear()

                        agents.forEach{ it ->
                            if(it.agentName.toLowerCase(Locale.getDefault()).contains(searchInput)){
                                displayAgentList.add(it)
                            }
                        }

                        adapter2!!.notifyDataSetChanged()

                        var total = displayAgentList.size.toString()
                        searchCountTxt.text = "Showing " + total + " agent(s)"
                        searchCountTxt.visibility = View.VISIBLE

                        if(displayAgentList.size == 0){
                            searchImg.visibility = View.VISIBLE
                            searchTxt.visibility = View.VISIBLE
                            searchTxt.text = "No Results"
                            searchCountTxt.visibility = View.GONE

                        }

                    }else{
                        //now adding the adapter to recyclerview
                        recyclerView.adapter = adapter

                        displayList.clear()

                        clients.forEach{ it ->
                            if(it.policyNo.toLowerCase(Locale.getDefault()).contains(searchInput)){
                                displayList.add(it)
                            }
                        }
                        adapter!!.notifyDataSetChanged()

                        var total = displayList.size.toString()
                        searchCountTxt.text = "Showing " + total + " pending case(s)"
                        searchCountTxt.visibility = View.VISIBLE

                        if(displayList.size == 0){
                            searchImg.visibility = View.VISIBLE
                            searchTxt.visibility = View.VISIBLE
                            searchTxt.text = "No Results"
                            searchCountTxt.visibility = View.GONE

                        }
                    }

                }else{
                    searchImg.visibility = View.VISIBLE
                    searchTxt.visibility = View.VISIBLE
                    searchTxt.text = "Enter at least the first three characters to search for Policy No./ Agent Name."
                    searchCountTxt.visibility = View.GONE

                    displayList.clear()
                    adapter!!.notifyDataSetChanged()

                    displayAgentList.clear()
                    adapter2!!.notifyDataSetChanged()
                }
                return true
            }
        })

    }

    private fun toolbar(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

        //add a close icon in the toolbar
        val close = findViewById<ImageView>(R.id.template_close)
        close.setOnClickListener {
            finish()
        }
    }

    private fun isLetters(input:String): Boolean {
        for(c in input){
            if(c !in 'A'..'Z' && c !in 'a'..'z'){
                return false
            }
        }
        return true
    }

    override fun onItemClick(clientList: Client, position: Int) {
        //Send data using intent and bundle
        val intent = Intent(this, DetailActivity::class.java)
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

    override fun onItemClick(agent: Agent, clientList: ArrayList<Client>, position: Int) {
        //Send data using intent and bundle
        val intent = Intent(this, AgentMainActivity::class.java)
        val bundle = bundleOf(
            "id" to position.toString(),
            "name" to agent.agentName,
            "ID" to agent.agentID,
            "clientList" to clientList
        )
        intent.putExtras(bundle)

        //Want to get return from the next activity, then use this way
        startActivityForResult(intent,999)
    }
}