package com.pru.pinmm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pru.pinmm.R
import com.pru.pinmm.databinding.HistoryItemBinding
import com.pru.pinmm.model.response.HistoryItem


class HistoryListAdapter(
    private val historyList: ArrayList<HistoryItem>,
    private val listener: ((item: HistoryItem) -> Unit)? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val viewHolder = HistoryListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.history_item,
                parent,
                false
            )
        )
        viewHolder.setListeners()
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HistoryListViewHolder -> {
                holder.bind(historyList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    private inner class HistoryListViewHolder
    constructor(
        private val binding: HistoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setListeners() {
            binding.hText.setOnClickListener {
                listener?.invoke(historyList[adapterPosition])
            }
        }

        fun bind(item: HistoryItem) = with(binding) {
            hText.text = item.name
        }
    }
}

