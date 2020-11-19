package com.dgm.pruebagft.repository.remote.model

import com.google.gson.annotations.SerializedName

class CatalogResponse{

    @SerializedName("response")
    var response = CardResponse()

    inner class CardResponse {

        @SerializedName("_id")
        var _id :String = ""

        @SerializedName("type_cards")
        var type_cards : ArrayList<CardDetail> = ArrayList()

        inner class CardDetail{
            @SerializedName("type")
            var type: String = ""

            @SerializedName("name")
            var name: String = ""
        }
    }
}





