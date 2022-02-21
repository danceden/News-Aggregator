package com.example.homework_project

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment(R.layout.news_container) {
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.editLiveData.observe(viewLifecycleOwner) { new ->
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
        val newsRecycler: RecyclerView = view.findViewById(R.id.newsRecycler)
        viewModel.newsLiveData.observe(viewLifecycleOwner) { news ->
            newsRecycler.adapter = FragmentAdapter(news, object : FragmentAdapter.Callback {
                override fun onDeleteClick(newID: Int) {
                    viewModel.onDeleteClick(newID)
                }

                override fun onEditClick(new: New) {
                    viewModel.onEditClick(new)
                }
            })

        }
        viewModel.addNewLiveData.observe(viewLifecycleOwner){
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    AddNewFragment()
                ) // добавить новость (создать класс add)
                .addToBackStack(null) // почитать документацию
                .commit()
        }
        val addNewButton: Button = view.findViewById(R.id.addNewButton)
        addNewButton.setOnClickListener {
            viewModel.onAddNewClick()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }


// архитектура
    // два варианта : cделать красиво
    // добавить кнопку "Редактировать новость" и в Callback


}