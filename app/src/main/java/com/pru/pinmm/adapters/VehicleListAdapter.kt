package com.pru.pinmm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pru.pinmm.R
import com.pru.pinmm.databinding.VehicleListItemBinding
import com.pru.pinmm.model.response.VehicleItem


class VehicleListAdapter(
    private val vehicleList: ArrayList<VehicleItem>,
    private val listener: ((item: VehicleItem) -> Unit)? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    private var filterList: ArrayList<VehicleItem> = vehicleList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val viewHolder = VehicleListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vehicle_list_item,
                parent,
                false
            )
        )
        viewHolder.setListeners()
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is VehicleListViewHolder -> {
                holder.bind(filterList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val filterString = p0.toString()
                if (filterString.isEmpty()) {
                    filterList = vehicleList
                } else {
                    val resultList = ArrayList<VehicleItem>()
                    for (row in vehicleList) {
                        if (row.vehicleRegNo?.lowercase()
                                ?.contains(
                                    p0.toString().lowercase()
                                ) == true || row.vehicleType?.lowercase()
                                ?.contains(p0.toString().lowercase()) == true
                        ) {
                            resultList.add(row)
                        }
                    }
                    filterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults

            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                val result = p1?.values as ArrayList<VehicleItem>
                filterList = result
                notifyDataSetChanged()
            }

        }
    }

    inner class VehicleListViewHolder
    constructor(
        private val binding: VehicleListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setListeners() {
            binding.rbVehicle.setOnClickListener {
                listener?.invoke(filterList[adapterPosition])
            }
        }

        fun bind(item: VehicleItem) = with(binding) {
            rbVehicle.text = item.vehicleRegNo?.plus(" (${item.vehicleType})")
            rbVehicle.isChecked = item.isSelected
        }
    }
}

