package com.dgm.pruebagft.repository.remote.model

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("firstname")
    var firstname: String,
    @SerializedName("lastname")
    var lastname: String
)

