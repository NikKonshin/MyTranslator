package com.nikitakonshin.mytranslator.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nikitakonshin.mytranslator.R
import com.nikitakonshin.utils.viewById

class SearchDialogFragment : BottomSheetDialogFragment() {

    private var onSearchClickListener: OnSearchClickListener? = null
    private val searchEditText by viewById<EditText>(R.id.search_edit_text)
    private val searchButtonTextView by viewById<TextView>(R.id.search_button_textview)
    private val clearTextImageView by viewById<ImageView>(R.id.clear_text_imageview)
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (searchEditText.text != null && searchEditText.text.toString().isNotEmpty()) {
                searchButtonTextView.isEnabled = true
                clearTextImageView.visibility = View.VISIBLE
            } else {
                searchButtonTextView.isEnabled = false
                clearTextImageView.visibility = View.GONE
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private val onSearchButtonClickListener =
        View.OnClickListener {
            onSearchClickListener?.onClick(searchEditText.text.toString())
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
        searchButtonTextView.setOnClickListener(onSearchButtonClickListener)
        searchEditText.addTextChangedListener(textWatcher)
        addOnClearClickListener()
    }

    override fun onDestroyView() {
        onSearchClickListener = null
        super.onDestroyView()
    }

    interface OnSearchClickListener {

        fun onClick(searchWord: String)
    }

    private fun addOnClearClickListener() {
        clearTextImageView.setOnClickListener {
            searchEditText.setText("")
            searchButtonTextView.isEnabled = false
        }
    }

    companion object {
        fun newInstance(): SearchDialogFragment = SearchDialogFragment()
    }
}