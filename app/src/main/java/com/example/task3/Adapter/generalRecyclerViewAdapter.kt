package com.example.task3.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task3.Activity.AgentMainActivity
import com.example.task3.Data.Client
import com.example.task3.Fragments.newBusinessFragment
import com.example.task3.Fragments.policyServicingFragment
import com.example.task3.R
import com.example.task3.Activity.SearchActivity

class generalRecyclerViewAdapter(
    val clientList: ArrayList<Client>,
    val newBusinessFragment1: newBusinessFragment?,
    val policyServicingFragment: policyServicingFragment?,
    val searchActivity: SearchActivity?,
    val agentMainActivity: AgentMainActivity?,
    val isNBinAgentMainActivity:Boolean,
    var clickListener: OnUserClickListener
): RecyclerView.Adapter<generalRecyclerViewAdapter.ViewHolder>() {


    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): generalRecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v,clientList)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: generalRecyclerViewAdapter.ViewHolder, position: Int) {
        var isNBFragment :Boolean?
        isNBFragment = newBusinessFragment1 != null

        var isSearchActivity:Boolean?
        isSearchActivity = searchActivity != null

        var isAgentMainActivity:Boolean?
        isAgentMainActivity = agentMainActivity != null

        holder.bindItems(clientList[position],isNBFragment,isSearchActivity,isAgentMainActivity,isNBinAgentMainActivity,clickListener)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return clientList.size
    }

    //the class is holding the list view
    class ViewHolder(itemView: View, clientList: ArrayList<Client>) : RecyclerView.ViewHolder(itemView) {
        private lateinit var agentTxt:TextView

        fun bindItems(client: Client, isNBFragment: Boolean, isSearchActivity: Boolean, isAgentMainActivity:Boolean, isNBinAgentMainActivity:Boolean, action: OnUserClickListener) {
            //Common data binding
            val nameTxt = itemView.findViewById(R.id.nameTxt) as TextView
            val numTxt = itemView.findViewById(R.id.numTxt) as TextView
            val policyNumTxt = itemView.findViewById(R.id.policyNumTxt) as TextView
            val numlbl = itemView.findViewById(R.id.numLbl) as TextView
            val iconImage = itemView.findViewById(R.id.iconImage) as ImageView
            val agentLbl = itemView.findViewById(R.id.agentLbl) as TextView
            val categoryTxt = itemView.findViewById(R.id.categoryTxt) as TextView
            val processLbl = itemView.findViewById(R.id.processLbl) as TextView
            val processTxt = itemView.findViewById(R.id.processTxt) as TextView
            agentTxt = itemView.findViewById(R.id.agentTxt) as TextView

            iconImage.setImageResource(client.profilePic)
            nameTxt.text = client.name
            if (client.Passport == ""){
                numTxt.text = client.NRIC
            }else if(client.NRIC == ""){
                numTxt.text = client.Passport
                numlbl.text = "Passport No."
            }
            policyNumTxt.text = client.policyNo



            //if the recyclerview is called from SearchActivity then diff setup
            if(isSearchActivity == true){
                agentLbl.visibility = View.VISIBLE
                agentTxt.visibility = View.VISIBLE
                categoryTxt.visibility = View.VISIBLE

                //Trim the name if too long
                trim(client)

                //check the category of the user
                if(client.category == "PS"){
                    processLbl.visibility = View.VISIBLE
                    processTxt.visibility = View.VISIBLE

                    processTxt.text = client.process
                    categoryTxt.text = "Policy Servicing"
                }else{
                    categoryTxt.text = "New Business"
                }

            }

            //if the recyclerview is called from AgentMainActivity then diff setup
            else if(isAgentMainActivity == true){
                if(isNBinAgentMainActivity == true){
                    //make process invisible
                    processLbl.visibility = View.GONE
                    processTxt.visibility = View.GONE
                }else{
                    processLbl.visibility = View.VISIBLE
                    processTxt.visibility = View.VISIBLE
                    processTxt.text = client.process
                }

                agentLbl.visibility = View.VISIBLE
                agentTxt.visibility = View.VISIBLE

                //Trim the name if too long
                trim(client)

            }

            //if the recyclerview is called from PolicyServicing then diff setup
            else if(isNBFragment == false){
                val processLbl = itemView.findViewById(R.id.processLbl) as TextView
                val processTxt = itemView.findViewById(R.id.processTxt) as TextView
                processLbl.visibility = View.VISIBLE
                processTxt.visibility = View.VISIBLE

                processTxt.text = client.process
            }


            //For the user select the item in the RecyclerView
            itemView.setOnClickListener {
                action.onItemClick(client, adapterPosition)
            }
        }

        private fun trim(client: Client) {
            //Trim the name if too long
            if(client.agentName.length > 13){
                var s = client.agentName.toString().substring(0,13)
                agentTxt.text = s + "... (" + client.agentID + ")"

            }else{
                agentTxt.text = client.agentName + " (" + client.agentID + ")"
            }
        }

    }

    //Create interface for the select item in RecyclerView
    interface OnUserClickListener{
        fun onItemClick(client: Client, position: Int)
    }

}