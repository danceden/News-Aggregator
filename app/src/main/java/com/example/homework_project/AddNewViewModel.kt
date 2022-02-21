package com.example.homework_project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddNewViewModel : ViewModel() {
    val isClosedLiveData = SingleLiveEvent<Boolean>()
    val editableNewLiveData = MutableLiveData<New?>(null)
    private val api = ServiceLocator.mainApi()

    fun init(new: New?) {
        editableNewLiveData.value = new
    }

    fun addNewClicked(title: String, text: String) {
        val addNew = AddNew(title, text)
        val setItemsCall: Call<Unit>
        val new = editableNewLiveData.value
        if (new != null) {
            setItemsCall = api.editItems(new.id, addNew)
        } else {
            setItemsCall = api.postItems(addNew)
        }

        setItemsCall.enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                isClosedLiveData.value = true
            }
        })
    }
}