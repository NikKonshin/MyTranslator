package com.nikitakonshin.mytranslator.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.nikitakonshin.core.fragment.BaseFragment
import com.nikitakonshin.model.entity.AppState
import com.nikitakonshin.model.entity.DataModel
import com.nikitakonshin.mytranslator.MainActivity
import com.nikitakonshin.mytranslator.R
import com.nikitakonshin.mytranslator.view.adapter.HistoryRVAdapter
import com.nikitakonshin.mytranslator.viewmodel.HistoryViewModel
import kotlinx.android.synthetic.main.fragment_history.*
import org.koin.android.viewmodel.ext.android.viewModel

class HistoryFragment : BaseFragment<AppState>() {

    override val model by viewModel<HistoryViewModel>()
    private val adapter: HistoryRVAdapter by lazy { HistoryRVAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                fragmentManager?.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setActionbarHomeButtonAsUp()
        model.getLiveData("", false).observe(this, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        setHasOptionsMenu(true)
        history_activity_recyclerview.adapter = adapter
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun setActionbarHomeButtonAsUp() {
        val activity = activity as MainActivity
        activity.supportActionBar?.setHomeButtonEnabled(true)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}