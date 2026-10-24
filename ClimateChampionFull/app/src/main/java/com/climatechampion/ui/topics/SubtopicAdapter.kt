package com.climatechampion.ui.topics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.climatechampion.R
import com.climatechampion.databinding.ItemSubtopicBinding
import com.climatechampion.model.Subtopic

/**
 * RecyclerView adapter for subtopics shown in the topic detail screen.
 * Each row is expandable to show full content or summary.
 */
class SubtopicAdapter : ListAdapter<Subtopic, SubtopicAdapter.SubtopicViewHolder>(DiffCallback()) {

    private val expandedPositions = mutableSetOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubtopicViewHolder {
        val binding = ItemSubtopicBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SubtopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubtopicViewHolder, position: Int) {
        holder.bind(getItem(position), expandedPositions.contains(position))
    }

    inner class SubtopicViewHolder(
        private val binding: ItemSubtopicBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(subtopic: Subtopic, isExpanded: Boolean) {
            binding.tvTitle.text = subtopic.title

            // Fallback display for read time if 0
            val readTimeText = if (subtopic.readTimeMinutes > 0) {
                binding.root.context.getString(R.string.read_time_format, subtopic.readTimeMinutes)
            } else {
                "Quick Read"
            }
            binding.tvReadTime.text = readTimeText

            // Use content if available, falling back to summary
            val displayContent = subtopic.content.ifEmpty { subtopic.summary }

            // Show/hide content based on expand state
            if (isExpanded) {
                binding.tvContent.visibility = View.VISIBLE
                binding.tvContent.text = displayContent
                binding.ivExpand.setImageResource(R.drawable.ic_expand_less)
            } else {
                binding.tvContent.visibility = View.GONE
                binding.ivExpand.setImageResource(R.drawable.ic_expand_more)
            }

            binding.root.setOnClickListener {
                val pos = bindingAdapterPosition
                if (pos == RecyclerView.NO_POSITION) return@setOnClickListener
                if (expandedPositions.contains(pos)) {
                    expandedPositions.remove(pos)
                } else {
                    expandedPositions.add(pos)
                }
                notifyItemChanged(pos)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Subtopic>() {
        override fun areItemsTheSame(old: Subtopic, new: Subtopic) =
            old.subtopicId == new.subtopicId || old.title == new.title

        override fun areContentsTheSame(old: Subtopic, new: Subtopic) = old == new
    }
}