package com.nikitakonshin.mytranslator.view.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.nikitakonshin.core.fragment.BaseFragment
import com.nikitakonshin.model.entity.AppState
import com.nikitakonshin.model.entity.DataModel
import com.nikitakonshin.mytranslator.MainActivity
import com.nikitakonshin.mytranslator.R
import com.nikitakonshin.mytranslator.di.injectDependencies
import com.nikitakonshin.mytranslator.utils.convertMeaningsToString
import com.nikitakonshin.mytranslator.view.adapter.TranslateRVAdapter
import com.nikitakonshin.mytranslator.viewmodel.TranslateViewModel
import com.nikitakonshin.utils.viewById
import kotlinx.android.synthetic.main.fragment_translate.*
import org.koin.android.scope.currentScope

class TranslateFragment : BaseFragment<AppState>() {

    private lateinit var splitInstallManager: SplitInstallManager

    override lateinit var model: TranslateViewModel
    private val observer = Observer<AppState> { renderData(it) }
    private val recyclerView by viewById<RecyclerView>(R.id.main_activity_recyclerview)
    private val fab by viewById<FloatingActionButton>(R.id.search_fab)

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
        private const val HISTORY_FRAGMENT_PATH = "com.nikitakonshin.historyscreen.HistoryFragment"
        private const val HISTORY_FRAGMENT_FEATURE_NAME = "historyscreen"
        private const val TRANSLATE_FRAGMENT_SETTINGS_REQUEST_CODE = 120

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
                        data.meanings!![0].imageUrl
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
        injectDependencies()
        setActionbarHomeButtonAsUp(false)
        val viewModel: TranslateViewModel by currentScope.inject()
        model = viewModel
        recyclerView.adapter = adapter
        fab.setOnClickListener {
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
                startHistoryFragment()
                setActionbarHomeButtonAsUp(true)
                return true
            }
            R.id.menu_screen_settings ->{
                startActivityForResult(Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY), TRANSLATE_FRAGMENT_SETTINGS_REQUEST_CODE)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun startHistoryFragment() {
        val fragment = Class.forName(HISTORY_FRAGMENT_PATH).newInstance() as Fragment
        splitInstallManager = SplitInstallManagerFactory.create(this.context)
        val request = SplitInstallRequest
            .newBuilder()
            .addModule(HISTORY_FRAGMENT_FEATURE_NAME)
            .build()

        splitInstallManager
            .startInstall(request)
            .addOnSuccessListener {
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.container, fragment)?.addToBackStack(null)?.commit()
            }
            .addOnFailureListener {
                Toast.makeText(
                    this.context,
                    "Couldn't download feature: " + it.message,
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TRANSLATE_FRAGMENT_SETTINGS_REQUEST_CODE){
                Toast.makeText(this.context, "result_ok", Toast.LENGTH_SHORT).show()
        }
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun setActionbarHomeButtonAsUp(visible: Boolean) {
        val activity = activity as MainActivity
        activity.supportActionBar?.setHomeButtonEnabled(visible)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(visible)
    }
}

