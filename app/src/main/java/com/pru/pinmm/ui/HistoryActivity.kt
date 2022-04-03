package com.pru.pinmm.ui

import android.os.Bundle
import com.pru.pinmm.adapters.HistoryListAdapter
import com.pru.pinmm.databinding.ActivityHistoryBinding
import com.pru.pinmm.model.response.HistoryItem
import com.pru.pinmm.ui.authentication.BaseActivity

class HistoryActivity : BaseActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private val historyList: ArrayList<HistoryItem> = arrayListOf()
    private val adapter: HistoryListAdapter by lazy {
        HistoryListAdapter(historyList = historyList, listener = {

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // API call
        // on response ->
        historyList.clear()
//        historyList.addAll(response.historydata)
        adapter.notifyDataSetChanged()
    }
}