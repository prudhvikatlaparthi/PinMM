package com.pru.pinmm.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.pru.pinmm.R
import com.pru.pinmm.adapters.HistoryListAdapter
import com.pru.pinmm.databinding.ActivityHistoryBinding
import com.pru.pinmm.databinding.SuccessAlertdialogLayoutBinding
import com.pru.pinmm.model.response.HistoryItem
import com.pru.pinmm.ui.authentication.BaseActivity
import com.pru.pinmm.utils.CommonUtils.showMyDialog

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
        adapter.submitList(historyList)

//        historyList.addAll(response.historydata)
        adapter.notifyDataSetChanged()
        binding.btnItem.setOnClickListener {
            val dbinding = DataBindingUtil.inflate<SuccessAlertdialogLayoutBinding>(
                layoutInflater,
                R.layout.success_alertdialog_layout, null, false
            )
            dbinding.title.text = "sdfsdfsdf"
            showMyDialog(dBinding = dbinding, isCancelable = true, afterInit = { dialog ->
                dialog.show()
                dbinding.ok.setOnClickListener {
                    dialog.dismiss()
                }
            })
        }
    }
}