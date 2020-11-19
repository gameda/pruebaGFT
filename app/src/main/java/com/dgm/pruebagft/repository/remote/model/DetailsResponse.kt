package com.dgm.pruebagft.repository.remote.model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class DetailsResponse {

    @SerializedName("response")
    var cardList: ArrayList<Card> = ArrayList()

    inner class Card{

        @SerializedName("_id")
        var _id: String? = ""

        @SerializedName("name")
        var name: String? = ""

        @SerializedName("type")
        var type: String? = ""

        @SerializedName("userId")
        var userId: String? = ""

        @SerializedName("deposits")
        var deposits: Int? = null

        @SerializedName("withdrawals")
        var withdrawals: Int? = null

        @SerializedName("balance")
        var balance: Int? = null
    }
}