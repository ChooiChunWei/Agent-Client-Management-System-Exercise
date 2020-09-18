package com.example.task3

import Migration
import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        //Init Realm
        Realm.init(applicationContext)

        var configuration = RealmConfiguration.Builder()
            .name("client")
            /*.deleteRealmIfMigrationNeeded()*/
            .schemaVersion(2)
            .migration(Migration())
            .build()

        Realm.setDefaultConfiguration(configuration)
        var version = Realm.getDefaultConfiguration()?.schemaVersion
        Log.d("Schema Version",""+ version.toString())

        /*val realm = Realm.getInstance(configuration)*/

    }
}