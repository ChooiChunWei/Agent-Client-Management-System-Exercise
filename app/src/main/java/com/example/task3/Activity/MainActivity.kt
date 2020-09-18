package com.example.task3.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.task3.Adapter.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.task3.Data.*
import com.example.task3.R
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    //initialize the components for tablayout and viewpager
    private lateinit var mViewPager: ViewPager
    private lateinit var nbNum:TextView
    private lateinit var nbLabel:TextView
    private lateinit var psNum:TextView
    private lateinit var psLabel:TextView
    private lateinit var mPagerAdapter: PagerAdapter

    //Create realm
    private lateinit var realm: Realm
    var AgentRepo = AgentRepo()
    var ClientRepo = ClientRepo()

    var clients = ArrayList<Client>()
    var agents = ArrayList<Agent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar()

        //Init Realm
        realm = Realm.getDefaultInstance()

        //Create default dataset
        clientDefData()
        agentDefData()

        //init
        mViewPager = findViewById(R.id.view_pager)
        nbNum = findViewById(R.id.nbNum)
        nbLabel = findViewById(R.id.nbLabel)
        psNum = findViewById(R.id.psNum)
        psLabel = findViewById(R.id.psLabel)

        //searchview function
        mPagerAdapter = PagerAdapter(supportFragmentManager,clients)
        mViewPager.adapter = mPagerAdapter
        mViewPager.offscreenPageLimit = 2

        //add page change listener
        mViewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                changingTabs(position)
            }
        })

        //Default tab
        mViewPager.currentItem = 0
        getItemCount(clients)
        nbNum.setTextColor(Color.RED)
        psNum.setTextColor(Color.parseColor("#55FF0000"))

        nbNum.setOnClickListener{
            mViewPager.currentItem = 0
        }

        nbLabel.setOnClickListener{
            mViewPager.currentItem = 0
        }

        psNum.setOnClickListener{
            mViewPager.currentItem = 1
        }

        psLabel.setOnClickListener{
            mViewPager.currentItem = 1
        }

    }

    private fun toolbar(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
    }

    private fun clientDefData(){
        var clientsArrayList = createClientList()
        //Add into the realm database
        clientsArrayList.forEach{
            ClientRepo.addClient(it)

            //delete data
            /*ClientRepo.delClient(it)*/
        }

        //get default dataset from realm database
        var clientResults = ClientRepo.getClients()
        clients.addAll(realm.copyFromRealm(clientResults))
    }

    private fun agentDefData(){
        var agentsArrayList = createAgentList()
        //Add into the realm database
        agentsArrayList.forEach{
            AgentRepo.addAgent(it)

            //Delete data
            /*AgentRepo.delAgent(it)*/
        }

        //get default dataset from realm database
        var agentResults = AgentRepo.getAgents()
        agents.addAll(realm.copyFromRealm(agentResults))
    }

    //Calculate the total cases
    private fun getItemCount(clients: ArrayList<Client>) {
        var nbSum = 0
        var psSum = 0

        for(i in 0 until clients.size){
            if(clients[i].category == "NB"){
                nbSum++
            }else if(clients[i].category == "PS"){
                psSum++
            }
        }
        nbNum.setText(""+ nbSum)
        psNum.setText(""+ psSum)
    }

    private fun changingTabs(position: Int) {
        if(position == 0){
            nbNum.setTextColor(Color.RED)
            psNum.setTextColor(Color.parseColor("#55FF0000"))
        }

        if(position == 1){
            psNum.setTextColor(Color.RED)
            nbNum.setTextColor(Color.parseColor("#55FF0000"))
        }
    }

    //Add Search Icon
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.top_menu,menu)
        return true
    }
    //When Search Icon is being clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        if (id == R.id.search){
            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra("Clients",clients)
            intent.putExtra("Agents",agents)

            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

}
