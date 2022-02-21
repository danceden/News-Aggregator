package com.example.homework_project
// добавить в callback адаптер
// перемеиновать в EdiNewFragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class AddNewFragment : Fragment(R.layout.add_new_fragment) {
    private val viewModel by viewModels<AddNewViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editNewTitle = view.findViewById<EditText>(R.id.editNewTitle)
        val editNewText = view.findViewById<EditText>(R.id.editNewText)
        // observe - подписка на livedata
        // таким образом мы получаем данные хранящиеся во viewmodel
        // и viewModel ничего не знает о fragment
        // это для того чтобы не надо было читать fragment чтобы понять viewmodel
        viewModel.isClosedLiveData.observe(viewLifecycleOwner) { isClosed ->
            if (isClosed) {
                requireActivity().supportFragmentManager.popBackStack()
            }
        } // эта лямбда выполняется когда меняется value внутри isClosedLiveData

        viewModel.editableNewLiveData.observe(viewLifecycleOwner) { new ->
            if (new != null) {
                editNewText.setText(new.text)
                editNewTitle.setText(new.title)
            }
        }

        val new = arguments?.getParcelable<New>("new")
        viewModel.init(new)
        val addNewFragmentButton: Button = view.findViewById(R.id.addNewFragmentButton)
        // подписка на клик. Сообщает viewModel о событии addNewClicked
        addNewFragmentButton.setOnClickListener {
            viewModel.addNewClicked(
                title = editNewTitle.text.toString(),
                text = editNewText.text.toString()
            )
        }
    }
}