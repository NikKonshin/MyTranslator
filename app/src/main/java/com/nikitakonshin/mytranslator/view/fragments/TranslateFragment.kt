package com.nikitakonshin.mytranslator.view.fragments

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import com.nikitakonshin.mytranslator.MainActivity
import com.nikitakonshin.mytranslator.R
import com.nikitakonshin.mytranslator.model.entity.AppState
import com.nikitakonshin.mytranslator.model.entity.DataModel
import com.nikitakonshin.mytranslator.utils.convertMeaningsToString
import com.nikitakonshin.mytranslator.view.adapter.TranslateRVAdapter
import com.nikitakonshin.mytranslator.viewmodel.TranslateViewModel
import kotlinx.android.synthetic.main.fragment_translate.*
import org.koin.android.viewmodel.ext.android.viewModel

class TranslateFragment : BaseFragment<AppState>() {

    override val model by viewModel<TranslateViewModel>()
    private val observer = Observer<AppState> { renderData(it) }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }

    private val adapter: TranslateRVAdapter by lazy {
        TranslateRVAdapter(onListItemClickListener)
    }
    private val onListItemClickListener: TranslateRVAdapter.OnListItemClickListener =
        object : TranslateRVAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                fragmentManager?.beginTransaction()?.replace(
                    R.id.container, DescriptionFragment.newInstance(
                        data.text,
                        convertMeaningsToString(data.meanings!!),
                        data.meanings[0].imageUrl
                    )
                )?.addToBackStack(null)
                    ?.commit()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_translate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setActionbarHomeButtonAsUp()
        main_activity_recyclerview.adapter = adapter
        search_fab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    model.getLiveData(searchWord, true).observe(this@TranslateFragment, observer)
                }
            })
            searchDialogFragment.show(childFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.history_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.container, HistoryFragment())?.addToBackStack(null)?.commit()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun setActionbarHomeButtonAsUp() {
        val activity = activity as MainActivity
        activity.supportActionBar?.setHomeButtonEnabled(false)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}

