package com.example.task3.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task3.Data.Agent
import com.example.task3.Data.Client
import com.example.task3.R

class agentRecyclerViewAdapter(
    val agentList:ArrayList<Agent>,
    val clientList: ArrayList<Client>,
    var clickListener: OnUserClickListener
): RecyclerView.Adapter<agentRecyclerViewAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): agentRecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.agent_list_layout, parent, false)
        return ViewHolder(v, agentList)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: agentRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(agentList[position],clientList,clickListener)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return agentList.size
    }

    //the class is holding the list view
    class ViewHolder(itemView: View, agentList: ArrayList<Agent>) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(agent: Agent, clientList: ArrayList<Client>, action: OnUserClickListener) {
            //Common data binding
            val nameTxt = itemView.findViewById(R.id.nameTxt) as TextView
            val numTxt = itemView.findViewById(R.id.numTxt) as TextView

            nameTxt.text = agent.agentName
            numTxt.text = agent.agentID

            //For the user select the item in the RecyclerView
            itemView.setOnClickListener {
                action.onItemClick(agent,clientList, adapterPosition)
            }
        }
    }

    //Create interface for the select item in RecyclerView
    interface OnUserClickListener{
        fun onItemClick(
            agent: Agent,
            client: ArrayList<Client>,
            position: Int)
    }
}