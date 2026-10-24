package com.climatechampion.ui.leaderboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.climatechampion.R
import com.climatechampion.databinding.ItemLeaderboardBinding
import com.climatechampion.model.LeaderboardEntry
import com.climatechampion.utils.PointsCalculator
import com.climatechampion.utils.toFormattedPoints

/**
 * RecyclerView adapter for the leaderboard screen.
 * Highlights the current user's row and shows rank medals for top 3.
 */
class LeaderboardAdapter(
    private val currentUserId: String? = null
) : ListAdapter<LeaderboardEntry, LeaderboardAdapter.LeaderboardViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val binding = ItemLeaderboardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return LeaderboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        val entry = getItem(position)
        holder.bind(entry, entry.userId == currentUserId)
    }

    class LeaderboardViewHolder(
        private val binding: ItemLeaderboardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(entry: LeaderboardEntry, isCurrentUser: Boolean) {
            // Rank display: medal for top 3, number otherwise
            binding.tvRank.text = PointsCalculator.rankMedal(entry.rank)

            binding.tvName.text = entry.displayName
            binding.tvPoints.text = entry.totalPoints.toFormattedPoints()
            binding.tvLevel.text = entry.getLevelBadge()
            binding.tvLevelEmoji.text = PointsCalculator.levelEmoji(entry.level)

            if (entry.photoUrl.isNotBlank()) {
                Glide.with(binding.root.context)
                    .load(entry.photoUrl)
                    .circleCrop()
                    .placeholder(R.drawable.ic_profile_placeholder)
                    .error(R.drawable.ic_profile_placeholder)
                    .into(binding.ivAvatar)
            } else {
                binding.ivAvatar.setImageResource(R.drawable.ic_profile_placeholder)
            }

            // Highlight current user
            binding.root.setBackgroundColor(
                if (isCurrentUser) {
                    ContextCompat.getColor(binding.root.context, R.color.highlight_user)
                } else {
                    ContextCompat.getColor(binding.root.context, android.R.color.transparent)
                }
            )

            // Special styling for top 3
            val rankColor = when (entry.rank) {
                1 -> R.color.rank_gold
                2 -> R.color.rank_silver
                3 -> R.color.rank_bronze
                else -> android.R.color.black
            }
            binding.tvRank.setTextColor(
                ContextCompat.getColor(binding.root.context, rankColor)
            )
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<LeaderboardEntry>() {
        override fun areItemsTheSame(old: LeaderboardEntry, new: LeaderboardEntry) =
            old.userId == new.userId

        override fun areContentsTheSame(old: LeaderboardEntry, new: LeaderboardEntry) =
            old == new
    }
}