package com.example.task3.Data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Agent(
    @PrimaryKey
    var id:Int = 0,
    var agentName: String = "",
    var agentID: String = ""
):Serializable,RealmObject(){}

//Create dummy data
fun createAgentList():ArrayList<Agent>{
    var agents = ArrayList<Agent>()
    //adding some dummy data to the list
    agents.add(Agent(0,"Joseph Lee Mook Weng","18476253"))
    agents.add(Agent(1, "Kee Li Li", "18476255"))
    agents.add(Agent(2, "Joseph Leong", "18676254"))
    agents.add(Agent(3, "Joseph Chin", "18776259"))
    agents.add(Agent(4, "Ang Chin Lee", "18573954"))

    return agents
}