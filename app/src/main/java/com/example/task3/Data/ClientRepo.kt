package com.example.task3.Data

import io.realm.Realm
import io.realm.RealmResults

class ClientRepo {
    private lateinit var realm: Realm

    fun addClient(client: Client): Boolean {
        try {
            realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(client)
            realm.commitTransaction()
            realm.close()

            return true
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

    fun delClient(client: Client): Boolean {
        try {
            realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            val result: Client? = realm.where(
                Client::class.java).equalTo("id", client.id).findFirst()
            result?.deleteFromRealm()
            realm.commitTransaction()
            realm.close()

            return true
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

    fun getClients(): RealmResults<Client> {
        return realm.where(Client::class.java).findAll()
    }
}