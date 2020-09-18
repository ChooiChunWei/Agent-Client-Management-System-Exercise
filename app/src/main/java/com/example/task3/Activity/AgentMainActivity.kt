package com.example.task3.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout.*
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task3.Adapter.generalRecyclerViewAdapter
import com.example.task3.Data.Client
import com.example.task3.R
import java.util.*
import kotlin.collections.ArrayList

class AgentMainActivity : AppCompatActivity(), generalRecyclerViewAdapter.OnUserClickListener {
    private lateinit var nbNum:TextView
    private lateinit var nbLabel:TextView
    private lateinit var psNum:TextView
    private lateinit var psLabel:TextView
    private lateinit var CountTxt:TextView
    private lateinit var recyclerView:RecyclerView
    private lateinit var search:SearchView

    var adapter: generalRecyclerViewAdapter? = null
    var adapter2: generalRecyclerViewAdapter? = null
    var clients = ArrayList<Client>()
    var displayList = ArrayList<Client>()
    var displayList2 = ArrayList<Client>()
    var arrayList = ArrayList<Client>()
    var arrayList1 = ArrayList<Client>()
    var position:Int = 0
    var agentName:String = ""

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_main)
        toolbar()

        //get data from previous activity using bundle
        val bundle = intent.extras

        if(bundle != null){
            agentName = bundle.getString("name").toString()
            clients = intent.getSerializableExtra("clientList") as ArrayList<Client>

        //Set the title in the toolbar
        val title = findViewById<TextView>(R.id.template_title)
        title.text = agentName

            //getting recyclerview from xml
            recyclerView = findViewById(R.id.agentRecyclerView) as RecyclerView
            //adding a layoutmanager
            recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)

            //creating our adapters
            adapter = generalRecyclerViewAdapter(displayList,null,null,null,this,true,this)
            adapter2 = generalRecyclerViewAdapter(displayList2,null,null,null,this,false,this)


        CountTxt = findViewById<TextView>(R.id.CountTxt)
        nbNum = findViewById<TextView>(R.id.nbNum)
        nbLabel = findViewById<TextView>(R.id.nbLabel)
        psNum = findViewById<TextView>(R.id.psNum)
        psLabel = findViewById<TextView>(R.id.psLabel)

            //Default tab
            defaultTab()

            nbNum.setOnClickListener{
                position = 0
                changingTabs(position)
            }

            nbLabel.setOnClickListener{
                position = 0
                changingTabs(position)

            }

            psNum.setOnClickListener{
                position = 1
                changingTabs(position)

            }

            psLabel.setOnClickListener{
                position = 1
                changingTabs(position)

            }
        }

        //Search
        search = findViewById<SearchView>(R.id.agentSearchView)
        val NotFoundImg = findViewById<ImageView>(R.id.NotFoundImg)
        val NotFoundTxt = findViewById<TextView>(R.id.NotFoundTxt)
        val CountTxt = findViewById<TextView>(R.id.CountTxt)

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(input: String?): Boolean {
                if(input!!.isNotEmpty()){
                    CountTxt.visibility = View.GONE
                    NotFoundImg.visibility = View.INVISIBLE
                    NotFoundTxt.visibility = View.INVISIBLE

                    var searchInput = input.toLowerCase(Locale.getDefault())

                    if(position == 0){
                        displayList.clear()

                        arrayList.forEach{ it ->
                            if(it.name.toLowerCase(Locale.getDefault()).contains(searchInput)){
                                displayList.add(it)
                            }else if(it.NRIC.toLowerCase(Locale.getDefault()).contains(searchInput)||it.Passport.toLowerCase(Locale.getDefault()).contains(searchInput)){
                                displayList.add(it)
                            }else if(it.policyNo.toLowerCase(Locale.getDefault()).contains(searchInput)){
                                displayList.add(it)
                            }
                        }

                        adapter!!.notifyDataSetChanged()

                        if(displayList.size == 0){
                            NotFoundImg.visibility = View.VISIBLE
                            NotFoundTxt.visibility = View.VISIBLE
                        }
                    }

                    if(position == 1){
                        displayList2.clear()

                        arrayList1.forEach{ it ->
                            if(it.name.toLowerCase(Locale.getDefault()).contains(searchInput)){
                                displayList2.add(it)
                            }else if(it.NRIC.toLowerCase(Locale.getDefault()).contains(searchInput)||it.Passport.toLowerCase(Locale.getDefault()).contains(searchInput)){
                                displayList2.add(it)
                            }else if(it.policyNo.toLowerCase(Locale.getDefault()).contains(searchInput)){
                                displayList2.add(it)
                            }
                        }

                        adapter2!!.notifyDataSetChanged()

                        if(displayList2.size == 0){
                            NotFoundImg.visibility = View.VISIBLE
                            NotFoundTxt.visibility = View.VISIBLE
                        }
                    }

                }else{
                    //Close
                    CountTxt.visibility = View.VISIBLE
                    NotFoundImg.visibility = View.INVISIBLE
                    NotFoundTxt.visibility = View.INVISIBLE

                    displayList.clear()
                    displayList.addAll(arrayList)
                    displayList2.clear()
                    displayList2.addAll(arrayList1)

                    adapter!!.notifyDataSetChanged()
                    adapter2!!.notifyDataSetChanged()

                }
                return true
            }
        })

    }

    private fun toolbar(){
        //Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            //back
            onBackPressed()
        }
    }

    fun defaultTab(){
        nbNum.setTextColor(Color.RED)
        psNum.setTextColor(Color.parseColor("#55FF0000"))

        displayList.clear()
        nbFilterData()

        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter

        CountTxt.text = "Showing " + displayList.size.toString() + " pending case(s)"

        nbNum.text = displayList.size.toString()
        psFilterData()
        psNum.text = displayList2.size.toString()
    }

    fun changingTabs(position: Int) {
        if(position == 0){
            nbNum.setTextColor(Color.RED)
            psNum.setTextColor(Color.parseColor("#55FF0000"))

            displayList.clear()
            nbFilterData()

            //now adding the adapter to recyclerview
            recyclerView.adapter = adapter

            CountTxt.text = "Showing " + displayList.size.toString() + " pending case(s)"
            search.setQuery("",false)
        }

        if(position == 1){
            psNum.setTextColor(Color.RED)
            nbNum.setTextColor(Color.parseColor("#55FF0000"))

            displayList2.clear()
            psFilterData()

            Log.d("EA1",recyclerView.adapter.toString())

            //now adding the adapter to recyclerview
            recyclerView.adapter = adapter2
            Log.d("EA2",recyclerView.adapter.toString())

            CountTxt.text = "Showing " + displayList2.size.toString() + " pending case(s)"
            search.setQuery("",false)

        }
    }

    fun nbFilterData(){
        displayList.clear()
        arrayList.clear()
        for(i in 0 until clients.size){
            if(clients[i].category == "NB" && clients[i].agentName == agentName){
                displayList.add(clients[i])
                arrayList.add(clients[i])
            }
        }
    }

    fun psFilterData(){
        displayList2.clear()
        arrayList1.clear()
        for(i in 0 until clients.size){
            if(clients[i].category == "PS" && clients[i].agentName == agentName){
                displayList2.add(clients[i])
                arrayList1.add(clients[i])
            }
        }
    }

    override fun onItemClick(client: Client, position: Int) {
        //Send data using intent and bundle
        val intent = Intent(this, DetailActivity::class.java)
        val bundle = bundleOf(
            "name" to client.name,
            "NRIC" to client.NRIC,
            "Passport" to client.Passport,
            "Policy" to client.policyNo,
            "Process" to client.process,
            "Category" to client.category,
            "LA2" to client.lifeAssured,
            "profilePic" to client.profilePic
        )
        intent.putExtras(bundle)

        //Want to get return from the next activity, then use this way
        startActivityForResult(intent,999)
    }
}