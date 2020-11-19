package com.dgm.pruebagft.repository.remote.model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class SuccesResponse {

    @SerializedName("token")
    var token: String = ""

    @SerializedName("success")
    var value: String = ""
}
