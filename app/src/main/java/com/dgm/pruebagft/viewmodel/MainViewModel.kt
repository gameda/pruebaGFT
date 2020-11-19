package com.dgm.pruebagft.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dgm.pruebagft.repository.AppRepository
import com.dgm.pruebagft.repository.remote.model.CatalogResponse
import com.dgm.pruebagft.repository.remote.model.User
import com.dgm.retrofit_corutines_example.utils.Resource
import kotlinx.coroutines.Dispatchers


class MainViewModel(private val repository: AppRepository) : ViewModel() {


    fun onRegisterClick(name: String, lastname: String, email: String, password: String)
            = liveData(Dispatchers.IO) {

        val user = User(email, password, name, lastname)
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.registerAccess(user)))
        }
        catch (exception: Exception) {
            emit(
                Resource.error(data = null, message = exception.message ?: "Error Occurred!"
                )
            )
        }
    }


    fun onLoginClick(email: String, password: String)
            = liveData(Dispatchers.IO) {

        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.loginAccess(email, password)))
        }
        catch (exception: Exception) {
            emit(
                Resource.error(data = null, message = exception.message ?: "Error Occurred!"
                )
            )
        }
    }

    fun getCatalog() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getCatalog()))
        }
        catch (exception: Exception) {
            emit(
                Resource.error(data = null, message = exception.message ?: "Error Occurred!"
                )
            )
        }
    }

    fun addCard(card: CatalogResponse.CardResponse.CardDetail) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.addCard(card.type, card.name)))
        }
        catch (exception: Exception) {
            emit(
                Resource.error(data = null, message = exception.message ?: "Error Occurred!"
                )
            )
        }
    }

    fun getCardInfo() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getCardInfo()))
        }
        catch (exception: Exception) {
            emit(
                Resource.error(data = null, message = exception.message ?: "Error Occurred!"
                )
            )
        }
    }


}