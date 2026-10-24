package com.climatechampion.ui.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.climatechampion.databinding.ItemCarbonTipBinding
import com.climatechampion.model.CarbonTip

/**
 * RecyclerView adapter for the personalised carbon reduction tips list.
 */
class CarbonTipsAdapter :
    ListAdapter<CarbonTip, CarbonTipsAdapter.TipViewHolder>(TipDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val binding = ItemCarbonTipBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TipViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TipViewHolder(
        private val binding: ItemCarbonTipBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tip: CarbonTip) {
            binding.tvTipIcon.text        = tip.icon
            binding.tvTipCategory.text    = tip.category
            binding.tvTipTitle.text       = tip.title
            binding.tvTipDescription.text = tip.description
            binding.tvTipSaving.text      = tip.potentialSaving
        }
    }

    class TipDiffCallback : DiffUtil.ItemCallback<CarbonTip>() {
        override fun areItemsTheSame(old: CarbonTip, new: CarbonTip) =
            old.title == new.title
        override fun areContentsTheSame(old: CarbonTip, new: CarbonTip) =
            old == new
    }
}
