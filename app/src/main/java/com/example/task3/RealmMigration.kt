import android.util.Log
import io.realm.DynamicRealm
import io.realm.RealmConfiguration
import io.realm.RealmMigration
import io.realm.annotations.RealmModule


class Migration : RealmMigration {
    override fun migrate(
        realm: DynamicRealm,
        oldVersion: Long,
        newVersion: Long
    ) {

        // DynamicRealm exposes an editable schema
        var oldVersion = oldVersion
        val schema = realm.schema

        if (oldVersion == 0L) {
            schema.create("TestingAgent")
                .addField("id",Int::class.java)
                .addField("agentName", String::class.java)
                .addField("agentID", String::class.java)

            oldVersion++
        }else if(oldVersion == 1L){
            schema.remove("TestingAgent")
            oldVersion++
        }

    }
}

/*
@RealmModule(classes = [Person::class])
class Module

var config = RealmConfiguration.Builder()
    .modules(Module())
    .schemaVersion(1) // Must be bumped when the schema changes
    .migration(Migration()) // Migration to run instead of throwing an exception
    .build()*/
