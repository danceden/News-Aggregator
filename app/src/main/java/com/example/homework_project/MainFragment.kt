package com.example.homework_project

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment(R.layout.news_container) {

    val newsLiveData = MutableLiveData<List<New>>(listOf())

    val retrofit = Retrofit.Builder()
        .baseUrl("https://rusha-news-server.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build();

    val api = retrofit.create(MainApi::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsRecycler : RecyclerView = view.findViewById(R.id.newsRecycler)
        newsLiveData.observe(viewLifecycleOwner){news ->
            newsRecycler.adapter = FragmentAdapter(news, object: FragmentAdapter.Callback{
                override fun onDeleteClick(newID : Int) {
                    api.deleteItems(newID)

                    val deleteNew = api.deleteItems(newID)

                    deleteNew?.enqueue(object :Callback<Unit> {
                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            callNews()
                        }

                    } )

                }

                override fun onEditClick(new: New) {
                    val addNewFragment = AddNewFragment()
                    val bundle = Bundle()
                    bundle.putParcelable("new", new)
                    addNewFragment.arguments = bundle
                    requireActivity().supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, addNewFragment)
                            .addToBackStack(null)
                            .commit()
                }
            })

        }

        val newsButton : Button = view.findViewById(R.id.newsButton)
        newsButton.setOnClickListener{
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, AddNewFragment()) // добавить новость (создать класс add)
                .addToBackStack(null) // почитать документацию
                .commit()
        }

    }

    var getItemsCall: Call<List<New>>? = null

    override fun onResume() {
        super.onResume()
        callNews()

    }

    override fun onStop() {
        super.onStop()
        getItemsCall?.cancel()
    }

    fun callNews() {
        getItemsCall?.cancel()

        getItemsCall = api.getItems()

        getItemsCall?.enqueue(object :Callback<List<New>> {
            override fun onFailure(call: Call<List<New>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<New>>, response: Response<List<New>>) {
                val news = response.body()
                newsLiveData.value = news?: listOf()
            }
        })

    }


// архитектура
    // два варианта : cделать красиво
    // добавить кнопку "Редактировать новость" и в Callback


























}