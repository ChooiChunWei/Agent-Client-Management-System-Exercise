package com.example.task3.Data

import com.example.task3.R
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Client(
    @PrimaryKey
    var id:Int = 0 ,

    var name: String = "",
    var NRIC: String = "",
    var Passport: String = "",
    var policyNo: String = "",
    var category: String = "",
    var process: String = "",
    var lifeAssured: String = "",
    var isSubmitted: Boolean = false,
    var agentName: String = "",
    var agentID: String = "",
    var profilePic:Int = 0
):Serializable,RealmObject(){}

//Create dummy data here
fun createClientList(): ArrayList<Client> {
    var clients = ArrayList<Client>()
    //adding some dummy data to the list
    clients.add(
        Client(
            0,
            "Chong Mah Xin",
            "931103-07-5543",
            "",
            "TL2036743847",
            "NB",
            "",
            "Nicholas Tze Yong",
            false,
            "Kee Li Li",
            "18476255",
            R.drawable.ic_profilepic1
        )
    )
    clients.add(
        Client(
            1,
            "Ahmad Bakri Abdullah...",
            "",
            "A8899466517",
            "TL2036098654",
            "NB",
            "",
            "Nicholas Tze Yong",
            false,
            "Joseph Lee Mook Weng",
            "18476253",
            R.drawable.ic_profilepic2
        )
    )
    clients.add(
        Client(
            2,
            "Leong Wenng Seonng",
            "921111-07-5533",
            "",
            "TL2036743848",
            "NB",
            "",
            "Nicholas Tze Yong",
            false,
            "Joseph Lee Mook Weng",
            "18476253",
            R.drawable.ic_profilepic3
        )
    )
    clients.add(
        Client(
            3,
            "Client 4",
            "921111-07-5553",
            "",
            "TL2036743849",
            "NB",
            "",
            " Nicholas Tze Yong",
            false,
            "Joseph Leong",
            "18676253",
            R.drawable.ic_profilepic1
        )
    )
    clients.add(
        Client(
            4,
            "Teh Wai Kong",
            "801203-14-8879",
            "",
            "TL2036123456",
            "PS",
            "Deposit Withdrawal",
            "Brenda Yeoh",
            false,
            "Joseph Chin",
            "18776253",
            R.drawable.ic_profilepic2
        )
    )
    clients.add(
        Client(
            5,
            "George R. Miller",
            "",
            "A7745478825",
            "TL2030086433",
            "PS",
            "Assignment",
            "Brenda Yeoh",
            false,
            "Ang Chin Lee",
            "18573954",
            R.drawable.ic_profilepic3
        )
    )
    clients.add(
        Client(
            6,
            "Chan Ying Ying",
            "",
            "A7745478826",
            "TL2030086432",
            "PS",
            "Deposit Withdrawal",
            "Brenda Yeoh",
            false,
            "Joseph Lee Mook Weng",
            "18476253",
            R.drawable.ic_profilepic1
        )
    )

    return clients
}

