package com.nikitakonshin.mytranslator.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nikitakonshin.mytranslator.R
import kotlinx.android.synthetic.main.search_dialog_fragment.*

class SearchDialogFragment : BottomSheetDialogFragment() {

    private var onSearchClickListener: OnSearchClickListener? = null
    private val textWatcher = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (search_edit_text.text != null && search_edit_text.text.toString().isNotEmpty()){
                search_button_textview.isEnabled = true
                clear_text_imageview.visibility = View.VISIBLE
            } else{
                search_button_textview.isEnabled = false
                clear_text_imageview.visibility = View.GONE
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private val onSearchButtonClickListener =
        View.OnClickListener {
            onSearchClickListener?.onClick(search_edit_text.text.toString())
            dismiss()
        }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_button_textview.setOnClickListener(onSearchButtonClickListener)
        search_edit_text.addTextChangedListener(textWatcher)
        addOnClearClickListener()
    }

    override fun onDestroyView() {
        onSearchClickListener = null
        super.onDestroyView()
    }

    interface OnSearchClickListener {

        fun onClick(searchWord: String)
    }

    private fun addOnClearClickListener(){
        clear_text_imageview.setOnClickListener {
            search_edit_text.setText("")
            search_button_textview.isEnabled = false
        }
    }

    companion object {
        fun newInstance(): SearchDialogFragment = SearchDialogFragment()
    }
}