package com.climatechampion.ui.topics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.climatechampion.R
import com.climatechampion.databinding.ItemTopicBinding
import com.climatechampion.model.Topic
import com.climatechampion.model.UserProgress

/**
 * RecyclerView adapter for the topic list screen.
 * Shows topic card with title, category, difficulty, progress, and thumbnail image.
 */
class TopicAdapter(
    private val onTopicClick: (Topic) -> Unit
) : ListAdapter<Topic, TopicAdapter.TopicViewHolder>(TopicDiffCallback()) {

    private val progressMap = mutableMapOf<String, UserProgress>()

    fun setProgress(progressList: List<UserProgress>) {
        progressMap.clear()
        progressList.forEach { progressMap[it.topicId] = it }
        notifyItemRangeChanged(0, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val binding = ItemTopicBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topic = getItem(position)
        holder.bind(topic, progressMap[topic.topicId])
    }

    inner class TopicViewHolder(
        private val binding: ItemTopicBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(topic: Topic, progress: UserProgress?) {
            val context = binding.root.context

            binding.tvTitle.text = topic.title
            binding.tvDescription.text = topic.description
            binding.tvCategory.text = topic.category
            binding.tvDifficulty.text = topic.difficulty

            // Safe String formatting for points
            binding.tvPoints.text = try {
                context.getString(R.string.points_reward_format, topic.pointsReward)
            } catch (_: Exception) {
                "+${topic.pointsReward} pts"
            }

            // Difficulty chip background color
            val diffColor = when (topic.difficulty.lowercase()) {
                "expert" -> R.color.difficulty_expert
                "intermediate" -> R.color.difficulty_intermediate
                else -> R.color.difficulty_beginner
            }
            binding.tvDifficulty.setBackgroundColor(
                ContextCompat.getColor(context, diffColor)
            )

            // Calculate & Display Progress
            val percent = progress?.percentComplete ?: 0
            binding.progressBar.progress = percent
            binding.tvProgress.text = when {
                progress?.completed == true -> "✓ Completed"
                percent > 0 -> "$percent%"
                else -> "Not started"
            }

            // Load Topic Image or Fallback Placeholder
            if (topic.thumbnailUrl.isNotEmpty()) {
                Glide.with(context)
                    .load(topic.thumbnailUrl)
                    .placeholder(R.drawable.ic_topic_placeholder)
                    .error(R.drawable.ic_topic_placeholder)
                    .centerCrop()
                    .into(binding.ivThumbnail)
            } else {
                binding.ivThumbnail.setImageResource(R.drawable.ic_topic_placeholder)
            }

            binding.root.setOnClickListener { onTopicClick(topic) }
        }
    }

    class TopicDiffCallback : DiffUtil.ItemCallback<Topic>() {
        override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean {
            return oldItem.topicId == newItem.topicId
        }

        override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean {
            return oldItem == newItem
        }
    }
}