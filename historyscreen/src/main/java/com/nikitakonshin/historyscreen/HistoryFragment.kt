package com.nikitakonshin.historyscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.nikitakonshin.core.fragment.BaseFragment
import com.nikitakonshin.historyscreen.di.injectDependencies
import com.nikitakonshin.model.entity.AppState
import com.nikitakonshin.model.entity.DataModel
import com.nikitakonshin.utils.viewById
import org.koin.android.scope.currentScope

class HistoryFragment : BaseFragment<AppState>() {

    override lateinit var model: HistoryViewModel
    private val adapter: HistoryRVAdapter by lazy { HistoryRVAdapter() }
    private val historyActivityRecyclerView by viewById<RecyclerView>(R.id.history_activity_recyclerview)

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
        model.getLiveData("", false).observe(this, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        setHasOptionsMenu(true)
        injectDependencies()
        val viewModel: HistoryViewModel by currentScope.inject()
        model = viewModel
        historyActivityRecyclerView.adapter = adapter
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }
}