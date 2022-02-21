package com.example.homework_project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val newsLiveData = MutableLiveData<List<New>>()
    val editLiveData = SingleLiveEvent<New>()
    val addNewLiveData = SingleLiveEvent<Unit>()

    private var getItemsCall: Call<List<New>>? = null
    private val api = ServiceLocator.mainApi()

    fun onAddNewClick() {
        addNewLiveData.value = Unit
    }

    fun onDeleteClick(newID: Int) {
        val deleteNew = api.deleteItems(newID)
        deleteNew.enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                callNews()
            }
        })
    }

    fun onEditClick(new: New) {
        editLiveData.value = new
    }

    fun onResume() {
        callNews()
    }

    fun onStop() {
        getItemsCall?.cancel()
    }

    private fun callNews() {
        getItemsCall?.cancel()

        getItemsCall = api.getItems()
        getItemsCall?.enqueue(object : Callback<List<New>> {
            override fun onFailure(call: Call<List<New>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<New>>, response: Response<List<New>>) {
                val news = response.body()
                newsLiveData.value = news ?: listOf()
            }
        })
    }
}