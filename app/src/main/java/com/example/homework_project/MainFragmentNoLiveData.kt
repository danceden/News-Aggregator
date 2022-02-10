//package com.example.homework_project
//
//import android.os.Bundle
//import android.view.View
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.RecyclerView
//import com.google.gson.Gson
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//class MainFragmentNoLiveData : Fragment(R.layout.`news_container.xml`) {
//
//    var newsRecycler: RecyclerView? = null
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        newsRecycler = view.findViewById(R.id.fragmentRecycler)
//        if (news != null) {
//            newsRecycler?.adapter = FragmentAdapter(news ?: listOf())
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        newsRecycler = null
//    }
//
//    var getItemsCall: Call<List<String>>? = null
//
//    var news : List<String>? = null
//
//    override fun onStart() {
//        super.onStart()
//        val retrofit = Retrofit.Builder()
//                .baseUrl("https://gist.githubusercontent.com")
//                .addConverterFactory(GsonConverterFactory.create(Gson()))
//                .build();
//
//        val api = retrofit.create(MainApi::class.java)
//
//
//
//        class FragmentCallback : Callback<List<String>> {
//            override fun onFailure(call: Call<List<String>>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
//                news = response.body()
//
//                newsRecycler?.adapter = FragmentAdapter(news?: listOf())
//            }
//        }
//        getItemsCall = api.getItems()
//        getItemsCall?.enqueue(FragmentCallback())
//    }
//
//    override fun onStop() {
//        super.onStop()
//        getItemsCall?.cancel()
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}