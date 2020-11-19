package com.dgm.retrofit_corutines_example.utils

import com.dgm.retrofit_corutines_example.utils.Status.*


class Resource <out T>(
    val status: Status,
    val data: T?,
    val message: String?) {

    //The companion object is a singleton, and its members can be accessed directly via the name of the containing class
    //Companion objects and their members can only be accessed via the containing class name, not via instances of the containing class.
    companion object {

        fun <T> success(data: T): Resource<T> = Resource(status = SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> = Resource(status = ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = LOADING, data = data, message = null)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}