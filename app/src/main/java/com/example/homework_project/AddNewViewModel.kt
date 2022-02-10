package com.example.homework_project

import android.icu.text.CaseMap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddNewViewModel: ViewModel() {
    val isClosedLiveData = MutableLiveData<Boolean>(false)
    val editableNewLiveData = MutableLiveData<New?>(null)
    val api = ServiceLocator.mainApi()

    fun init(new: New?) {
        editableNewLiveData.value = new
    }
    fun addNewClicked(title: String, text: String){
        val addNew = AddNew(title, text)
        val setItemsCall: Call<Unit>
        val new = editableNewLiveData.value
        if(new != null){
            setItemsCall = api.editItems(new.id, addNew)
        } else {
            setItemsCall = api.postItems(addNew)
        }

        setItemsCall.enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                isClosedLiveData.value = true
            }
        })
    }
}