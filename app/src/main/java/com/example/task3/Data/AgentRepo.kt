package com.example.task3.Data

import io.realm.Realm
import io.realm.RealmResults

class AgentRepo {
    private lateinit var realm: Realm

    fun addAgent(agent: Agent): Boolean {
        try {
            realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(agent)
            realm.commitTransaction()
            realm.close()

            return true
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

    fun delAgent(agent: Agent): Boolean {
        try {
            realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            val result: Agent? = realm.where(
                Agent::class.java).equalTo("id", agent.id).findFirst()
            result?.deleteFromRealm()
            realm.commitTransaction()
            realm.close()

            return true
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

    fun getAgents(): RealmResults<Agent> {
        return realm.where(Agent::class.java).findAll()
    }

}